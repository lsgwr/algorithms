//凸多边形分割 
#include <iostream>
#include <math.h>
#include<iomanip.h>
using namespace std;
int i,j,s,k,n;
double x[50],y[50],d1[50][50],c[50][50]; //x[i]存放横坐标，y[i]存放纵坐标，d1[i][j]存距离，c[i][j]表示从j号定点开始的i边形的Snp

double D(int i,int j) //获取两点间的距离
{
  double d;     
  d=sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]));      
  return d;
}

int main()
{
  freopen("excision.in","r",stdin);
  freopen("excision.out","w",stdout);
  cin>>n;  
  for(i=0;i<=n-1;i++) 
    cin>>x[i]>>y[i];
  for(i=0;i<=n-1;i++)
    for(j=0;j<=n-1;j++)
      if((fabs(i-j)==1)||(fabs(i-j)==n-1)) //如果两点相邻
        d1[i][j]=0;
      else
        d1[i][j]=D(i,j);
        
  for(s=1;s<=3;s++)
    for(j=1;j<=n-1;j++) //三角形或构不成图形为0
      c[i][j]=0;
  for (s=4;s<=n;s++) //其余初始化为无穷
    for (i=0;i<=n-1;i++)
      c[s][i]=1e300;
  for(s=4;s<=n;s++)
    for(i=0;i<=n-1;i++)
      for(k=1;k<=s-2;k++)
        if(c[s][i]>c[k+1][i]+c[s-k][i+k]+d1[i%n][(i+k)%n]+d1[(i+k)%n][(i+s-1)%n]) 
        //动态规划，%n处理超过n的情况
          c[s][i]=c[k+1][i]+c[s-k][i+k]+d1[i%n][(i+k)%n]+d1[(i+k)%n][(i+s-1)%n];
  cout<<setiosflags(ios::fixed)<<setprecision(2)<<c[n][0]; 
  return 0;
}
