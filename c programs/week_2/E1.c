#include <stdio.h>

void list_integers( void ) {
	printf("The integer subrange from 81 to 344...\n");
	int I = 81;
	do {
		printf("%d\n",I);
		I++;
	} while (I <= 344);
}

int main( void ) {
	list_integers();
	return 0;
}