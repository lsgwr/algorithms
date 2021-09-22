//奇妙的相遇－动规优化１ 
#include<stdio.h>
int n;
int Dir(int x,int y)
{
  if((x==0&&y==0)||(x==n-1&&y==n-1)||(x==0&&y==n-1)||(x==n-1&&y==0))
    return 2;
  else if(x==0||y==0||x==n-1||y==n-1)
    return 3;
  else
    return 4;
}
int main()
{
  freopen("encounter.in","r",stdin);
  freopen("encounter.out","w",stdout);
  scanf("%d",&n);
  int i,j,k;
  double p[2][n][n],x=0;
  for(i=0;i<n;i++)  //初始化f 
    for(j=0;j<n;j++) //p[0][i][j]表示公主当天到地图上横坐标为i,纵坐标为j的格子的概率 
      p[0][i][j]=0,p[1][i][j]=0;//p[1][i][j]表示公主第二天将到地图上i,j的格子的概率 
  p[0][n/2][n/2]=1;   //第一天公主在n/2，n/2的格子上，它的概率为1 
  int w=0;  //w的作用：可节约掉滚动数组相互复制所需的时间  0表示当天的情况,1表示下一天的情况。 
  for(k=0;k<n;k++,w=1-w)  //w=1-w 可实现1到0的切换, 
  {
    for(i=0;i<n;i++)
      for(j=0;j<n;j++)
        p[1-w][i][j]=0;
    for(i=0;i<n;i++)
      for(j=0;j<n;j++)
      {
        if(i-1>=0)//防止越界
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
  if(x<1e-4)
    printf("%.0f\n",x);
  else
    printf("%.4f\n",x);
  return 0;
}
