#include <stdarg.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdio.h>
#include <math.h>

double array_product( double x[], int count ) {
	double val = NAN;
	int check = 0;
	int i = 0;
	do {
		int skip = 0;
		if (x[i] != x[i]) skip = 1;
		if (x[i] == INFINITY || x[i] == -INFINITY) skip = 1;
		if (check == 1 && skip == 0) val = val * x[i];
		if (check == 0 && skip == 0) {
			val = x[i];
			check = 1;
		} 
		i++;
	} while (i < count);

	if (check == 0 || val == 0) {
		return NAN;
	} else {
	return val;
	}
}

void call_function( const char * label, double x[], int count ) {
	double prod = array_product( x, count );
	printf( "%s\n", label );
	printf( "\tInput data:\n" );

	for ( int i = 0; i < count; i++ ) {
		printf( "\t%d\t%f\n", i, x[i] );
	}

	printf( "\tProduct = %f\n\n", prod );
}

int main( void ) {
	double x1[] = {0};
	call_function( "Count == 0", x1, 0 );
	
	double x2[] = { NAN, +INFINITY, -INFINITY };
	call_function( "No finite values", x2, 3 );

	double x3[] = { 1, 2, 3, 4, 5, 6, 7 };
	call_function( "Several finite values", x3, 7 );

	double x4[] = { 2, M_PI, NAN, 3, INFINITY, 4 };
	call_function( "A mix of finite values and infinities", x4, 6 );
	
	return 0;
}