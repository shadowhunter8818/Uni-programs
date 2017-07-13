#include <stdarg.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdio.h>
#include <math.h>

int create_hist( double x[], int count, int hist[] ) {
	for (int i = 0; i <= 26; i++) {
		hist[i] = 0;
	}
	for (int t = 0; t < count; t++) {
		for (int y = 0; y <= 26; y++) {
			if (floor(x[t]) == y) hist[y]++;
		}
	}
	return hist[26+1];
}

void call_function( const char * label, double x[], int count ) {
	int hist[26 + 1];
	create_hist( x, count, hist );
	printf( "%s\n", label );
	printf( "\tInput data:\n" );

	for ( int i = 0; i < count; i++ ) {
		printf( "\t%d\t%f\n", i, x[i] );
	}

	printf( "\tHistogram:\n" );

	for ( int i = 0; i <= 26; i++ ) {
		printf( "\t%d\t%d\n", i, hist[i] );
	}

	printf( "\n" );
}

int main( void ) {
	srand( time( NULL ) );

	double x1[] = { 0 };
	call_function( "Count == 0", x1, 0 );

	double x2[] = { 0, 0, 0 };
	call_function( "Three equal values", x2, 3 );

	double x3[26 + 1];
	for ( int i = 0; i <= 26; i++ ) {
		x3[i] = i;
	}
	call_function( "One value in each bucket", x3, 26 + 1 );

	double x4[26 * 2 + 1];
	for ( int i = 0; i <= 26 * 2; i++ ) {
		x4[i] = (26+1) * ( double ) rand() / RAND_MAX;
	}
	call_function( "Random values", x4, 26 * 2 + 1 );

	return 0;
}