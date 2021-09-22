#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <fstream>
#include <cmath>
#include <iomanip>
using namespace std;
#define NMAX 51
#define double long double 

ifstream fin("excision.in");
ofstream fout("excision.out");

int n;
int pt_x[NMAX], pt_y[NMAX];
double f[NMAX][NMAX][NMAX];
double dis[NMAX][NMAX];

double calc(int beg, int i, int j);

int main(int argc, char *argv[])
{
	fin >> n;
	for(int i = 0; i < n; i++)
		fin >> pt_x[i] >> pt_y[i];
	for(int i = 0; i <n; i++)
		for(int j = 0; j < n; j++)
		if(i == j)
			dis[i + 1][i + 1] = 0.0;
		else
		{
			int dx = pt_x[i] - pt_x[j],
				dy = pt_y[i] - pt_y[j];
			
			dis[i + 1][j + 1] = sqrt(dx * dx + dy * dy);
		}
	
    fout << setprecision(2) << setiosflags(ios::fixed) << calc(1, 2, n) << "\n";

    return 0;
}

//
double calc(int beg, int i, int j)
{
	if(f[beg][i][j] > 0.5)
		return f[beg][i][j];
	if(j - i == 1)
		return 0.0;
	
	double ret, t;
	//ret = calc(beg + 1, i + 1, j) + dis[i][j];
	ret = calc(i, i + 1, j) + dis[i][j];
	for(int k = i + 1; k < j; k++)
		if((t = calc(beg, i, k) + calc(beg, k, j) + dis[beg][k]) < ret)
			ret = t;
			
	f[beg][i][j] = ret;
	return ret;
}
