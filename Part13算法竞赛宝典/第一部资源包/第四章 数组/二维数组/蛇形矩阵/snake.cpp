#include<cstdio>
#include<cstdlib>
#include<cstring>
#include<iostream>
using namespace std;
int a[100][100];
int main()
{
  //freopen("snake.in","r",stdin);
  //freopen("snake.out","w",stdout);
  int bj_a,bj_b,num=1,n,sx,i,j,sum=0;
  cin>>n;
  bj_a=n/2;
  bj_b=n/2;
  a[bj_a][bj_b]=num;
  sx=1;
  while(num<n*n)
  {
    num++;
    bj_b++;
    a[bj_a][bj_b]=num;
    while(bj_a>n/2-sx)
    {
      bj_a--;
      num++;
      a[bj_a][bj_b]=num;
    }
    while(bj_b>n/2-sx)
    {
      bj_b--;
      num++;
      a[bj_a][bj_b]=num;
    }
    while(bj_a<n/2+sx)
    {
      bj_a++;
      num++;
      a[bj_a][bj_b]=num;
    }
    while(bj_b<n/2+sx)
    {
      bj_b++;
      num++;
      a[bj_a][bj_b]=num;
    }
    sx++;
  }
  bj_b++;
  num++;
  a[bj_a][bj_b]=num;
  for(i=0; i<n; i++)
    for(j=0; j<n; j++)
    {
      if(i==j||i+j==n)
        sum+=a[i][j];
    }
  for(i=0; i<n; i++)
  {
    for(j=0; j<n-1; j++)
      printf("%d ",a[i][j]);
    printf("%d",a[i][j]);
    printf("\n");
  }
  printf("%d\n",sum);
  system("pause");
  return 0;
}
