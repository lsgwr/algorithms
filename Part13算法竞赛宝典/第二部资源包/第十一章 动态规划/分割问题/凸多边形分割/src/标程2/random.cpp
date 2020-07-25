#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <windows.h>
#include <ctime>
#include <fstream>
using namespace std;

int main(int argc,char *argv[])
{
    ofstream fout("excision.in");
    
    srand(GetTickCount());
    int n = rand() % 47 + 3;
    fout << n << "\n";
    for(int i = 0; i < n; i++)
    	fout << rand() % 100 - 50 << " "
    	     << rand() % 100 - 50 << "\n";
    
    return 0;
}
