#include "sort_helpers.h"



sem_t *semaphores[27];
FILE *temp_fp;
char* temp_filename = "temp.txt";

void read_all( char *filename ){
    
    FILE *fp = fopen( filename, "r" );
    int curr_line = 0;
	
    while( curr_line < MAX_NUMBER_LINES && 
           fgets( text_array[curr_line], MAX_LINE_LENGTH, fp ) )
    {
        curr_line++;
    }
	
    text_array[curr_line][0] = '\0';
    fclose(fp);
}


void read_by_letter( char *filename, char first_letter ){

    FILE *fp = fopen( filename, "r" );
    int curr_line = 0;
    text_array[curr_line][0] = '\0';
	
    while( fgets( text_array[curr_line], MAX_LINE_LENGTH, fp ) ){
        if( text_array[curr_line][0] == first_letter ){
            curr_line++;
        }

        if( curr_line == MAX_NUMBER_LINES ){
            sprintf( buf, "ERROR: Attempted to read too many lines from file.\n" );
            write( 1, buf, strlen(buf) );
            break;
        }
    }
	
    text_array[curr_line][0] = '\0';
    fclose(fp);
}


void sort_words( ){
    char temp[MAX_LINE_LENGTH];
	for(int i = 0; i < MAX_NUMBER_LINES && text_array[i][0] != '\0'; i++) {
	for(int j = i+1; j < MAX_NUMBER_LINES && text_array[j][0] != '\0'; j++) {	
		if(strcmp(text_array[i],text_array[j]) > 0) {
	    strcpy(temp, text_array[i]);
		strcpy(text_array[i], text_array[j]);
		strcpy(text_array[j], temp);
		i = i-1;
		break;
	}
	}
	}
}


int initialize( ){
	char sem_name[10];
	
   for(int i = 0; i < 27; i++) {
   sprintf(sem_name, "ali_%c", 'a' + i);
   sem_unlink(sem_name);
   if(i == 0) {
   semaphores[i] = sem_open(sem_name, O_CREAT, 0666, 1);
   }
   else {
	 semaphores[i] = sem_open(sem_name, O_CREAT, 0666, 0);  
   }
   }
   return 0;
}
 


int process_by_letter( char* input_filename, char first_letter ){
	 
     read_by_letter( input_filename, first_letter );
     sort_words( );
	 //Semaphore operations begin here.
	 sem_wait(semaphores[first_letter - 'a']);
	 sprintf( buf, "This process will sort the letter %c.\n",  first_letter );
     //write(1,buf,strlen(buf));
	if(first_letter == 'a') {
		// For the first letter, we have to create the file.
    temp_fp = fopen(temp_filename, "w");
	}
	else {
		// For the other letters, we need to append the file created above.
		temp_fp = fopen(temp_filename, "a");
	}
	if(temp_fp == NULL) {
	printf("There was an error in sorting.\n");
	return -1;
	}
	for(int i = 0; i < MAX_NUMBER_LINES && text_array[i][0] != '\0'; i++) {
    fprintf(temp_fp, "%s", text_array[i]);
	}
	fclose(temp_fp);
	sem_post(semaphores[1 + first_letter - 'a']);
    return 0;
}


int finalize( ){

	sem_wait(semaphores[26]);
	temp_fp = fopen(temp_filename, "r");
	if(temp_fp == NULL) {
	printf("There was an error in sorting.\n");
	return -1;
	}
	char filedata[MAX_LINE_LENGTH];
	while( fgets( filedata, MAX_LINE_LENGTH, temp_fp ) ){
	write( 1, filedata, strlen(filedata) );
	}
	fclose(temp_fp);
	sem_post(semaphores[26]);
    sprintf( buf, "Sorting complete!\n" );
    write( 1, buf, strlen(buf) );

    return 0;
}