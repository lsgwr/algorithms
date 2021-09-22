//奇妙的相遇－动规１ 
#include<stdio.h>

int main()
{
  freopen("encounter.in","r",stdin);
  freopen("encounter.out","w",stdout);  
  int Dir[301][301];//存储该点有几个扩展方向 
  int t,i,j,k,n;
  float p[301][301]={0},q[301][301]={0};//互为滚动数组.存储概率 
  float ans=0;
  scanf("%d",&n);
  k=(n+1)/2;  //计算出公主初始位置
  q[k][k]=1;  //公主初始位置概率为1,时间为0

  for(i=0;i<=n+1;i++) //计算Dir[x,y] 此处扩大了一圈以防越界 
    for(j=0;j<=n+1;j++)
    {
      if((i==1&&j==1)||(i==1&&j==n)||(i==n&&j==1)||(i==n&&j==n))
        Dir[i][j]=2;  //当该城镇位于四个角时,其概率为1/2 
      else if(i==1||j==1||i==n||j==n)
        Dir[i][j]=3;  //当该城镇位于四条边时,其概率为1/3 
      else Dir[i][j]=4;//否则.其概率为1/4 
    }
   
  for(t=1;t<=n;t++)//遍历时间t,所以可变三维数组为二维数组 
  {
    for(i=1;i<=n;i++)
      for(j=1;j<=n;j++)
        p[i][j]=0;  //刷新数组p 
        
    for(i=1;i<=n;i++)
      for(j=1;j<=n;j++)
      { 
        p[i][j]+=q[i][j-1]/Dir[i][j-1];//q为上一时间的概率 
        p[i][j]+=q[i][j+1]/Dir[i][j+1];
        p[i][j]+=q[i+1][j]/Dir[i+1][j];
        p[i][j]+=q[i-1][j]/Dir[i-1][j];
      }
    
    ans+=p[k][t];
    p[k][t]=0;  //避免重复 
    for(i=1;i<=n;i++)
      for(j=1;j<=n;j++)
        q[i][j]=p[i][j];//滚动数组 
  }
  if(ans<=0.00001)
    printf("0\n");
  else  
    printf("%.4f\n",ans);
  return 0;  
}
