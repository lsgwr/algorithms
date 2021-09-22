//子矩阵变形问题－搜索 
#include <iostream>
#include <cstdlib>
using namespace std;
int m,n,w,h;

int a[5001][5001];
void init()
{
  freopen("bombing.in","r",stdin);
  freopen("bombing.out","w",stdout);   
  int i,j;
  cin>>m>>n;
  cin>>w>>h;
  for(i=1;i<=m;++i)
    for(j=1;j<=n;++j)
      cin>>a[i][j];     
}

int main()
{
  init();    
  int i,j,k,l,Max=-INT_MAX,sum=0;
  for(i=1;i<=m-w+1;++i)
    for(j=1;j<=n-h+1;++j)
    {
      sum=0;
      for(k=i;k<=i+w-1;++k)
        for(l=j;l<=j+h-1;++l)
          sum+=a[k][l];
      if(sum>Max)  
        Max=sum;
    }
  cout<<Max<<endl;
  return 0;
}
