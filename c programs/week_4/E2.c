//gcc E3.c -o E3
//gcc E3.c -std=gnu99 -I../ZDK -L../ZDK -lzdk -lncurses -o E3
#include <stdio.h>

double get_extreme(double x, double y, double z){
//  (a) Begin the definition a function called get_extreme that returns the
//      minimum of its three double precision floating point 
//      arguments.
//      The arguments may be called anything, you like, but something simple
//      like x, y, and z will suffice. 
double min;
    //  (c) Implement the logic required to calculate the minimum
    //      of the three input values, and return the result.
if (x<= y && x<=z) {
	min = x;
} else {if (y<= x && y<=z){
	min = y;
} else {if (z<= x && z <=y) {
	min = z;
}	}	}

return min;
//  (b) End the definition of get_extreme.
}

void run_test(double x, double y, double z, char * label);

int main(void) {
    run_test(1.00200000000038, 1.0030000000004, 1.00400000000005, "All items equal");
    run_test(1, 2, 3, "Ascending");
    run_test(3, 2, 1, "Descending");
    run_test(1, 1, 3, "Two in a row, then ascending");
    run_test(3, 3, 1, "Two in a row, then descending");
    run_test(1, 4, 4, "Two in a row, then ascending");
    run_test(5, 1, 1, "Two in a row, then descending");
    run_test(3, 1, 5, "Minimum in the middle");
    run_test(1, 5, 3, "Maximum in the middle");
    return 0;
}

void run_test(double x, double y, double z, char * label) {
    printf("\nRunning test: %s\nData = %0.17f, %0.17f, %0.17f\n", label, x, y, z);
    double extreme = get_extreme(x, y, z);
    printf("The extreme value is %0.17f\n", extreme);
}