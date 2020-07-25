//子矩阵变形问题－动规 
#include <iostream>
#include <cstdlib> 
using namespace std;
int m,n,h,w;
int a[1001][1001],su[1001][1001];

int main()
{
  freopen("bombing.in","r",stdin);
  freopen("bombing.out","w",stdout);     
  int i,j,Ma=-INT_MAX;
  cin>>m>>n>>w>>h;
  for(i=1;i<=m;++i)
    for(j=1;j<=n;++j)
    {
      cin>>a[i][j]; 
      //求出i行j列的和
      su[i][j]=su[i][j-1]+su[i-1][j]-su[i-1][j-1]+a[i][j];   
    }
  for(i=1;i<=m-w+1;++i)
    for(j=1;j<=n-h+1;++j)//求出面积为W*H的矩形之和是否大于最大值
      Ma>?=su[i+w-1][j+h-1]-su[i-1][j+h-1]-su[i+w-1][j-1]+su[i-1][j-1];  
  cout<<Ma<<endl;
  return 0;
}
