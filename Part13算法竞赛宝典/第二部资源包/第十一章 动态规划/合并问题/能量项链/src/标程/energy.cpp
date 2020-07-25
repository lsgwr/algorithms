//能量项链 
#include <stdio.h>
#include <stdlib.h>

int m[200][200];
int n,temp,p[200];

void matrixch(int p[],int n) //核心算法
{
  int i,j,r,k;
  for(r=2;r<=n;r++)
    for(i=1;i<=n;i++)
    {
      j=i+r-1;
      m[i][j]=0;//初始为0 
      for(k=i;k<j;k++)//寻找K 
	  {
        if(k+1>n)//复制 
	      m[k+1][j]=m[k+1-n][j-n];
        temp=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
        if(temp>m[i][j])
	      m[i][j]=temp;
	  }
   }
}

int main()
{
  int i;  
  freopen("energy.in","r",stdin);
  freopen("energy.out","w",stdout);

  scanf("%d",&n); 
  for(i=0;i<=n-1;i++)//从0开始 
    scanf("%d",&p[i]);
  for(i=n;i<2*n;i++)//复制一遍数组 
    p[i]=p[i-n];
  matrixch(p,n);//调用核心算法 
  
  for(i=1;i<=n;i++)
    if(m[i][i+n-1]>temp) 
      temp=m[i][i+n-1];
  printf("%d\n",temp);
  return 0;
}
