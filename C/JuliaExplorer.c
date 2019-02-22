#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main( int argc, char* argv[] ) {
	// Declaring att pointers and variables I will use in the programme. 
	char* file_path;
	char* ptr;
	float a;
	float b;
	char filedata[200];
	int counterA;
	counterA = 0;
    int counterB;
	counterB = 0;
	// Making sure that 3 arguments are entered. Checking for argc 4 because a.out is considered as the first argument.
	if (argc != 4) {
		printf("Error: 3 arguments are required.\n");
		return -1;
	}
	// Assigning the different arguments to their respective requirements. 
	file_path = argv[1];
	a = atof(argv[2]);
	b = atof(argv[3]);
	// Using if statements to check whether a valid float argument is input.
	if( a == 0.0 ) {
	printf("Error: bad float arg\n");
	return -1;
	}
	if( b == 0.0 ) {
	printf("Error: bad float arg\n");
	return -1;
	}
	// Reading the file and checking if it contains the required tags.
	FILE* fp = fopen( file_path, "r");
	if( fp == NULL ){
		// 
		printf("Error: bad file");
		return -1;
	}
	// Incrementing the counter whenever the required tags are found in the file.
	while( fgets( filedata, 200, fp ) ){
		if ( strstr(filedata, "#A#") != NULL ){
		  counterA++;
		  continue;
		}
		if ( strstr(filedata, "#B#") != NULL ){
		  counterB++;
		  continue;
		}
      }
	  // If the counter values are 0, it means that the tags have not been found and a bad file error message is output.
	  	if ( counterA == 0 ){
          printf("Error: bad file\n");
		  return -1;
		}
		if ( counterB == 0 ){
			printf("Error: bad file\n");
			return -1;
		}
		// Rewinding so that the new while loop starts from the beginning of the file.
	  rewind(fp);
	  while( fgets( filedata, 200, fp ) ){
		  if ( strstr(filedata, "#A#") != NULL ){
			  // Replacing the character with the 6 decimal points float value.
          printf("a=%.6f\n", a);
		  continue;
		}
		if ( strstr(filedata, "#B#") != NULL ){
          printf("b=%.6f\n", b);
		  continue;
		}
		// Printing out the entire file.
		  printf("%s", filedata);
	  }
	    fclose(fp);
}