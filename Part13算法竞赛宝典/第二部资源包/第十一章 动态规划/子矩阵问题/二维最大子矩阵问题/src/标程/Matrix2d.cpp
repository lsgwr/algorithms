//二维最大子矩阵问题 
#include <iostream>
#include <cstdlib>
using namespace std;
int a[201][201],sum[201][201];
int temp[200+1];
int n,m;

void solve()
{
  long i,j,k,max=-INT_MAX,sumall;
  for(i=0;i<=n;i++)   
    for(j=i+1;j<=n;j++)   //从i行到j行
    {
      for(k=1;k<=m;k++)      //该循环求出i到j行第k列的和
        temp[k]=sum[j][k]-sum[i][k];
      sumall=0;
      for(k=1;k<=m;k++)   //与最大子序列和一样，找到最大值
      {
        sumall+=temp[k];
        if(sumall>max)   //记录最大值 
          max=sumall;
        if(sumall<0)
          sumall=0;
      }
    }
  cout<<max<<endl;;
}

void init()
{
  int i,j;
  cin>>n>>m;
  for(i=1;i<=n;i++)
    for(j=1;j<=m;j++)
      cin>>a[i][j];
  for(i=1;i<=n;i++)    // sum[i,j]表示矩阵第j列前i个元素的和，    
    for(j=1;j<=m;j++)
        sum[i][j]=sum[i-1][j]+a[i][j];//压缩数据 
}

int main()
{
  freopen("Matrix2d.in","r",stdin);
  freopen("Matrix2d.out","w",stdout);  
  init();
  solve();
  return 0;
}
