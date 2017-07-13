#include <stdio.h>

void list_integers( void ) {
	printf("Count down from 365, by steps of 7, finishing before lower bound (92) is reached.\n");
	int I = 365;
	do {
		printf("%d\n",I);
		I=I-7;
	} while (I > 92);
}

int main( void ) {
	list_integers();
	return 0;
}