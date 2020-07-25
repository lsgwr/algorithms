//传纸条－三维算法2 
#include <stdio.h>
#include <stdlib.h>
int data[101][52][52];

int main()
{
  freopen("message.in","r",stdin);
  freopen("message.out","w",stdout);    
  int m,n,i,j,a[100][100],k,x1,x2,t=0,mm;
  scanf("%d%d",&m,&n);
  for(i=1;i<=m;i++)
    for(j=1;j<=n;j++)
      scanf("%d",&a[i][j]);
  mm=m<n?n:m;
  data[2][2][1]=a[1][2]+a[2][1];
  data[2][1][2]=data[2][2][1];
  for(k=3;k<=m+n-1;k++)
    for(x1=1;x1<=k;x1++)
      for(x2=1;x2<=k;x2++)
      {
        if(x1==x2) 
          break;
        if(k==n+m-1) 
          data[m+n-1][n][n]=data[m+n-1][n-1][n]<data[m+n-1][n][n-1]?data[m+n-1][n][n-1]:data[m+n-1][n-1][n];
        t=data[k-1][x1][x2]+a[k-x1+1][x1]+a[k-x2+1][x2];
        if(data[k-1][x1-1][x2]+a[k-x1+1][x1]+a[k-x2+1][x2]>t) 
          t=data[k-1][x1-1][x2]+a[k-x1+1][x1]+a[k-x2+1][x2];
        if(data[k-1][x1][x2-1]+a[k-x1+1][x1]+a[k-x2+1][x2]>t) 
          t=data[k-1][x1][x2-1]+a[k-x1+1][x1]+a[k-x2+1][x2];
        if(data[k-1][x1-1][x2-1]+a[k-x1+1][x1]+a[k-x2+1][x2]>t) 
          t=data[k-1][x1-1][x2-1]+a[k-x1+1][x1]+a[k-x2+1][x2];
        data[k][x1][x2]=t;
      }
  printf("%d",data[m+n-1][n][n]);
  return 0;
}
