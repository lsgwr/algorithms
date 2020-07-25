//数字三角形－动规 
#include <iostream>
#include <cstdlib>
using namespace std;

const int M=1001;
int n;
int a[M][M];

int func()
{
  int i,j;
  for(i=n-1;i>=1;i--)
    for(j=1;j<=i;j++)
    {
      if(a[i+1][j]>a[i+1][j+1]) 
        a[i][j]+=a[i+1][j];
      else a[i][j]+=a[i+1][j+1]; 
    }
  return a[1][1];
}

int main()
{  
  freopen("tower.in","r",stdin);
  freopen("tower.out","w",stdout);
  int i,j,max;
  cin>>n;
  for(i=1;i<=n;i++)
    for(j=1;j<=i;j++)
      cin>>a[i][j];
  cout<<func()<<endl;
  return 0;
}
