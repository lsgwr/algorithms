//系统可靠性 
#include<iostream>
#include<stdlib.h>
using namespace std;

#define N 110

double f[N][N] ,value[N][N];
int d[N];
double M;

double max(double a,double b)
{
  return a>b?a:b; 
}

int main()
{
  freopen("Reliable.in","r",stdin);
  freopen("Reliable.out","w",stdout);  
  int i,j,k;
  int n,c;
  scanf("%d%d",&n,&c);
  for(i=1;i<=n;i++)
  {
    cin>>d[i];
    for(j=0;j*d[i]<=c;j++)
      scanf("%lf",&value[i][j]);
  }	
  for(i=0;i<=n;i++)
    for(j=0;j<=c;j++)
      f[i][j]=1;
  for(i=1;i<=n;i++)
    for(j=0;j<=c;j++)
    {
      M=0;
      for(k=0;k<=j;k++)
		M=max(M,f[i-1][k]*value[i][(j-k)/d[i]]);
      f[i][j]=M;
    }
  printf("%.4lf",f[n][c]);
  return 0;	
}



