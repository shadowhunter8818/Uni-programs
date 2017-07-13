#include <iostream>
#include <math.h>
#include <chrono>
#include <cstdlib>
#include <fstream>
#include <unistd.h>
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

double BruteForceMedian (int A[])
{
    int k = ceil(n/2.0);
    for (int i = 0; i < N; i++)
    {
        int numsmaller = 0;
        int numequal = 0;
        for (int j = 0; j < N; j++)
        {
            if (A[j] < A[i])
            {
                numsmaller++;
            }
            if (A[j] == A[i])
            {
                numequal++;
            }
        }
        if (numsmaller < k && k <= (numsmaller + numequal))
        {
           return A[i];
        }
    }
}

double BruteForceMedian_operations (int A[])
{
    int k = ceil(n/2.0);
    int Numbasic = 0;
    for (int i = 0; i < N; i++)
    {
        int numsmaller = 0;
        int numequal = 0;
        Numbasic++;
        for (int j = 0; j < N; j++)
        {
            if (A[j] < A[i])
            {
                numsmaller++;
                Numbasic++;
            }
            if (A[j] == A[i])
            {
                numequal++;
                Numbasic++;
            }
        }
        if (numsmaller < k && k <= (numsmaller + numequal))
        {
           Numbasic++;
           return Numbasic;
        }
    }
}

int main()
{
    using namespace std;
    ofstream myfile;
    myfile.open ("time.txt");
    ofstream mytest;
    mytest.open ("basicoperations.txt");
    INIT_TIMER
    n = 100.0;
    N = (int) n;
    for (int j = 0; j < 501; j++) {
    int testarray[N];
    for (int i = 0; i < N; i++){
            testarray[i] = N-i + rand () % 10;
    }
    START_TIMER
    double result = BruteForceMedian(testarray);
    STOP_TIMER(N)
    double excecutions = BruteForceMedian_operations(testarray);
    mytest << excecutions << endl;
    n +=100.0;
    N +=100;
    }
    return 0;
}
