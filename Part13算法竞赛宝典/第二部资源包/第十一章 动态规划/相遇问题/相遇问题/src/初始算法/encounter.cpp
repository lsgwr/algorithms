//奇妙的相遇 
#include<stdio.h>
int n;
float f[100][100][100];

int Dir(int x,int y)
{
  if((x==1&&y==1)||(x==n&&y==n)||(x==1&&y==n)||(x==n&&y==1))
    return 2;//四个角
  else if(x==1||y==1||x==n||y==n)
    return 3;//四条边
  else
    return 4;
}

int main()
{
  float ans=0; 
  int i,j,t,k;
  scanf("%d",&n);
  k=(n+1)/2,f[0][k][k]=1; 
  for(t=1;t<=n;t++)
  {
    for(i=1;i<=n;i++)
      for(j=1;j<=n;j++)
      {
        f[t][i][j]+=f[t-1][i-1][j]/Dir(i-1,j);
        f[t][i][j]+=f[t-1][i][j-1]/Dir(i,j-1);
        f[t][i][j]+=f[t-1][i+1][j]/Dir(i+1,j);
        f[t][i][j]+=f[t-1][i][j+1]/Dir(i,j+1);
      }
      ans+=f[t][k][t];
    }
    printf("%.4f",ans);
    return 0; 
}
