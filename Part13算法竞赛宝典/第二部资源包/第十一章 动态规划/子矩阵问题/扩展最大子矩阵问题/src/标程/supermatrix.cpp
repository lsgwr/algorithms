//扩展最大子矩阵问题 
#include <iostream>
#include <cstdlib>
using namespace std;
int n,m;
int sum[1001][1001];
int temp[1001];
int a[1001][1001];

int dp()
{
  int i,j,k,t,sum1,sum2,max1,max2,max3=-INT_MAX;
  for(t=1;t<n;++t)//把矩形横切 
  {
    for(i=1;i<=t;++i)//上部分最大 
    {
      max1=-INT_MAX; 
      for(j=i-1;j>=0;--j)
      {
        for(k=1;k<=m;++k)
          temp[k]=sum[i][k]-sum[j][k];
        sum1=0;
        for(k=1;k<=m;k++)   //与最大子序列和一样，找到最大值
        {
          sum1+=temp[k];
          if(sum1>max1)
            max1=sum1;
          if(sum1<0)
            sum1=0;
        }
      }
    }
    for(i=t+1;i<=n;++i)//下部分最大 
    {
      max2=-INT_MAX;
      for(j=i-1;j>=t;--j)
      {
        for(k=1;k<=m;++k)
          temp[k]=sum[i][k]-sum[j][k];
        sum2=0;
        for(k=1;k<=m;++k)
        {
          sum2+=temp[k];
          if(sum2>max2)
            max2=sum2;
          if(sum2<0)
            sum2=0;
        }
      }
    }
    if(max2+max1>max3)
      max3=max2+max1;   
  }
    return max3;
}

void init()
{
  int i,j,maxx1,maxx2;
  cin>>n>>m; 

  for(i=1;i<=n;++i) //横切 
    for(j=1;j<=m;++j)
    {  
      cin>>a[i][j];
      sum[i][j]=sum[i-1][j]+a[i][j];   // sum[i,j]表示矩阵第j列前i个元素的和， 
    } 
  maxx1=dp();
    
  //纵切    
  m=m^n;  //交换m,n 
  n=n^m;
  m=m^n;
  memcpy(sum,a,sizeof(a));//行列交换 ，转变矩阵，旋转90度 
  for(i=1;i<=n;++i)
    for(j=1;j<=m;++j)
      a[i][j]=sum[j][i];
  for(i=1;i<=n;++i)
    for(j=1;j<=m;++j)
      sum[i][j]=sum[i-1][j]+a[i][j];
  maxx2=dp();
    
  if(maxx1>maxx2) //判断横切大，还是纵切大 
    cout<<maxx1<<endl;
  else
    cout<<maxx2<<endl;
}

int main()
{
  freopen("supermatrix.in","r",stdin);
  freopen("supermatrix.out","w",stdout);  
  init();
  return 0;
}
