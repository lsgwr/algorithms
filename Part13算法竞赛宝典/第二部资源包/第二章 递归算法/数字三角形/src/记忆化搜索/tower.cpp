//数字三角形－记忆化搜索 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#define maxn 100
using namespace std;

int a[maxn][maxn],N,total,did[maxn][maxn];

int f(int i,int j)
{
	if (i==N-1) 
		return a[i][j];
	if (did[i][j])
		return did[i][j];
	did[i][j]=max(f(i+1,j),f(i+1,j+1))+a[i][j]; 
	return did[i][j];
}

int main()
{
 	FILE *in =fopen("tower.in","r");
 	FILE *out =fopen("tower.out","w");
 	int i,j;
 	fscanf(in,"%d",&N);
 	for (i=0;i<N;i++)
 		for (j=0;j<=i;j++)
 			fscanf(in,"%d",&a[i][j]);
 	fprintf(out,"%d\n",f(0,0));
 	return 0;
}
