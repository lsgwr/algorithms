//奇妙的相遇－动规优化３ 
#include<stdio.h>
int n;
int Dir(int x,int y)
{
  if((x==1&&y==1)||(x==n&&y==n)||(x==1&&y==n)||(x==n&&y==1))
    return 2;
  else if(x==1||y==1||x==n||y==n)
    return 3;
  else
    return 4;
}
int abs(int x)
{
  return (x>=0?x:-x);
}
int main()
{
  freopen("encounter.in","r",stdin);
  freopen("encounter.out","w",stdout);    
  scanf("%d",&n);
  if(!((n/2)&1))
  {
    puts("0");
    return 0;
  }
  int i,j,k;
  double p[2][n][n],x=0;
  for(i=0;i<n;i++)
    for(j=0;j<n;j++)
      p[0][i][j]=0,p[1][i][j]=0;
  p[0][n/2][n/2]=1;
  int w=0;
  for(k=0;k<n;k++,w=1-w)
  {
    for(i=0;i<n;i++)
      for(j=0;j<n;j++)
        p[1-w][i][j]=0;
    for(i=0;i<n;i++)
      for(j=k;j<n;j++)//k为当前天数，也可当作王子的横坐标，当公主在王子左边时，是不会相遇的 
        if((abs(j-k-1)+abs(i-n/2-1))%2!=0)//判断公主与王子相距的横坐标与纵坐标之和是否为偶数 
        {
          if(i-1>=0)
            p[1-w][i-1][j]+=p[w][i][j]/Dir(i,j);
          if(j-1>=0)
            p[1-w][i][j-1]+=p[w][i][j]/Dir(i,j);
          if(i+1<n)
            p[1-w][i+1][j]+=p[w][i][j]/Dir(i,j);
          if(j+1<n)
            p[1-w][i][j+1]+=p[w][i][j]/Dir(i,j);
        }
    x+=p[1-w][n/2][k],p[1-w][n/2][k]=0;
    }
    printf("%.4f\n",x);
    return 0;
}
