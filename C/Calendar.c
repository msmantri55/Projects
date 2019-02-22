#include <stdio.h>
#include <stdlib.h>
#include <string.h>



int horizontalline(int DAYSIZE) {
	int visiblesymbols;
    visiblesymbols = (DAYSIZE+3)*7-1;
// The next for loop prints horizontal lines.
printf("\n|");
for(int j = 0; j < visiblesymbols; j++) {
	printf("-");
}
printf("|\n");
return 0;
}
// This function prints all the spaces.
int printspaces(int spaces) {
  for (int i = 0; i < spaces; i++) {
   printf(" ");
   }
}

// Printing out the month including all the spaces.
int writemonth(char *monthname, int DAYSIZE) {
	int visiblesymbols;
	int leftspaces;
	int rightspaces;
	visiblesymbols = (DAYSIZE+3)*7-1;
	leftspaces = (visiblesymbols-strlen(monthname))/2;
	if((visiblesymbols-strlen(monthname))%2 == 1) {
	rightspaces = leftspaces + 1;
	}
	else{
	rightspaces = leftspaces;
	}
horizontalline(DAYSIZE);

printf("|");	
	
printspaces(leftspaces);
printf("%s", monthname);
printspaces(rightspaces);
printf("|");
horizontalline(DAYSIZE);
}

// Printing out all the weekdays with the required spacing. 
int weekdays(int DAYSIZE) {
	
	char shortMonday[10];
    memset(shortMonday, '\0', 10);
    char shortTuesday[10];
    memset(shortTuesday, '\0', 10);
    char shortWednesday[10];
    memset(shortWednesday, '\0', 10);
    char shortThursday[10];
    memset(shortThursday, '\0', 10);
    char shortFriday[10];
    memset(shortFriday, '\0', 10);
    char shortSaturday[10];
    memset(shortSaturday, '\0', 10);
    char shortSunday[10];
    memset(shortSunday, '\0', 10);
    // Copying the string from the initial character size to the one that considers the DAYSIZE.
    strncpy(shortMonday, "Monday", DAYSIZE);
    strncpy(shortTuesday, "Tuesday", DAYSIZE);
    strncpy(shortWednesday, "Wednesday", DAYSIZE);
    strncpy(shortThursday, "Thursday", DAYSIZE);
    strncpy(shortFriday, "Friday", DAYSIZE);
    strncpy(shortSaturday, "Saturday", DAYSIZE);
    strncpy(shortSunday, "Sunday", DAYSIZE);
	
    printf("|");
	printf(" %-*s ", DAYSIZE, shortSunday);
	printf("|");
	printf(" %-*s ", DAYSIZE, shortMonday);
	printf("|");
	printf(" %-*s ", DAYSIZE, shortTuesday);
	printf("|");
	printf(" %-*s ", DAYSIZE, shortWednesday);
	printf("|");
	printf(" %-*s ", DAYSIZE, shortThursday);
	printf("|");
	printf(" %-*s ", DAYSIZE, shortFriday);
	printf("|");	
	printf(" %-*s ", DAYSIZE, shortSaturday);
	printf("|");

	
}

// Printing the entire month will all the dates.
int printmonth(int DAYSIZE, int FIRSTDAY) {
	int count;
	int visiblesymbols;
	int i;
    visiblesymbols = (DAYSIZE+3)*7-1;
	// Line and for loop below print the blank columns.
    printf("|");
     for(int i = 0; i < FIRSTDAY-1; i++) {
      printspaces(DAYSIZE+2);
      printf("|");
     }
	 
// Prints first number line of calendar.	 		   
	for(count = 1; count <= (7-FIRSTDAY)+1; count++) {
		printf(" ");
    printf("%-*d", DAYSIZE+1, count);
    printf("|");
    }
    printf("\n");
	
    for(i = 0; i < 7; i++) {
		printf("|");
		printf(" ");
    printf("%-*d",DAYSIZE+1, count);
	count++;
    }
	printf("|");
    printf("\n");
    for(i = 0; i < 7; i++) {
		printf("|");
		printf(" ");
    printf("%-*d",DAYSIZE+1, count);
	count++;
    }
	printf("|");
    printf("\n");
    for(i = 0; i < 7; i++) {
		printf("|");
		printf(" ");
    printf("%-*d",DAYSIZE+1, count);
	count++;
    }
	printf("|");
	printf("\n");
	printf("|");
    for(i = 0; (i < 7) && (count <= 30) ; i++) {
	printf(" ");
   printf("%-*d",DAYSIZE+1, count);
     printf("|");
	 count++;
    }
	if(count == 30) {
	printf("\n");
	printf("|");
	printf(" ");
	printf("%-*d",DAYSIZE+1, count);
	printf("|");
	}
    if(FIRSTDAY != 7) {
	 for(int i = 0; i < 6-FIRSTDAY; i++) {
      printspaces(DAYSIZE+2);
      printf("|");
     }	 
    }
	else {
	for(int i = 0; i < 6; i++) {
      printspaces(DAYSIZE+2);
      printf("|");
     }	 
	}
}

	 




int main (int argc, char* argv[]) {
int DAYSIZE;
int FIRSTDAY;
DAYSIZE = atoi(argv[1]);
FIRSTDAY = atoi(argv[2]);
int visiblesymbols;
visiblesymbols = (DAYSIZE+3)*7+1;

// Using if statements to check that the DAYSIZE and FIRSTDAY values are appropriate. 
if(DAYSIZE < 2) {
	printf("Error: The day size must be greater than or equal to 2.\n");
	return -1;
}
if(FIRSTDAY < 1 || FIRSTDAY > 7) {
	printf("Error: Only integers between 1 and 7 are accepted for the days of the week.\n");
	return -1;
}

// If DAYSIZE and FIRSTDAY values are appropriate, the entire calendar is printed as required.
if(DAYSIZE >= 2 && (FIRSTDAY >= 1 || FIRSTDAY <= 7)) {
writemonth("January", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
printmonth(DAYSIZE, FIRSTDAY);


writemonth("February", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("March", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("April", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("May", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("June", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("July", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("August", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("September", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("October", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("November", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);


writemonth("December", DAYSIZE);
weekdays(DAYSIZE);
horizontalline(DAYSIZE);
FIRSTDAY = FIRSTDAY + 2;
if(FIRSTDAY > 7) {
FIRSTDAY = FIRSTDAY - 7;
}
printmonth(DAYSIZE, FIRSTDAY);
horizontalline(DAYSIZE);
}
}


