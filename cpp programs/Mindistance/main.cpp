#include <iostream>
#include <math.h>
#include <chrono>
#include <cstdlib>
#include <fstream>
#include <unistd.h>
#include <limits>
#define TIMING

#ifdef TIMING
#define INIT_TIMER auto start = std::chrono::high_resolution_clock::now();
#define START_TIMER  start = std::chrono::high_resolution_clock::now();
#define STOP_TIMER(name)  myfile << \
    std::chrono::duration_cast<std::chrono::microseconds>( \
            std::chrono::high_resolution_clock::now()-start \
    ).count() << std::endl;
#else
#define INIT_TIMER
#define START_TIMER
#define STOP_TIMER(name)
#endif


int N = 0;
double n = 0;
using namespace std;

double Mindistance (int A[])
{
    int dmin = std::numeric_limits<int>::max();
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (A[j] != A[i] && fabs(A[i] - A[j]) < dmin)
            {
                dmin = fabs(A[i] - A[j]);
            }
        }
    }
    return dmin;
}

double Mindistance_operations (int A[])
{
    int dmin = std::numeric_limits<int>::max();
    int Numbasic = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            Numbasic++;
            if (A[j] != A[i] && fabs(A[i] - A[j]) < dmin)
            {
                Numbasic++;
                dmin = fabs(A[i] - A[j]);
            }
        }
    }
    Numbasic++;
    return Numbasic;
}


int main()
{
   using namespace std;
    ofstream myfile;
    myfile.open ("time.txt");
    ofstream mytest;
    mytest.open ("basicoperations.txt");
    INIT_TIMER
    n = 15;
    N = (int) n;
    /*
    for (int j = 0; j < 517; j++) {
    int testarray[N];
    for (int i = 0; i <= N; i++){
            testarray[i] = 2000000 - rand() % 1800000000;
    }*/

    int testarray[15] = {-10 , 20 , 50, 1000, -500, -30 , 60, 90, 120 , -8, 27, -40 , 15 , -90 , -200};

    START_TIMER
    double result = Mindistance(testarray);
    STOP_TIMER(N)
    double excecutions = Mindistance_operations(testarray);
    mytest << excecutions << endl;

    //Screen output for correctness tests

    cout << "testarray[15] = {-10 , 20 , 50, 1000, -500, -30 , 60, 90, 120 , -8, 27, -40 , 15 , -90 , -200}" << endl;
    cout << "Expected distance 2, expected operations 229" << endl;
    cout << "The Min distance is " << result << endl;
    cout << "This took " << excecutions << " basic operations" << endl;

    n +=100.0;
    N +=100;

    return 0;
}
