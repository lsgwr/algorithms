//三角形最大面积 
#include<iostream>
#include<fstream>
#define M 1000
#define MAX 99999999
#define M 1000
using namespace std;

ifstream fin("TriangleArea.in");
ofstream fout("TriangleArea.out");

bool s[M+1][M+1];
int a[M+1][M+1];
int n;

void dp()
{
  int i,j,k,maxn=0;
  for(i=1;i<=n;++i)
    for(j=i;j<=n+n-i;j+=2) 
      if(s[i][j])
      {
        if(s[i-1][j-1]&&s[i-1][j]&&s[i-1][j+1])
          a[i][j]=min(a[i-1][j-1],a[i-1][j+1])+1;
        else
          a[i][j]=1;
      }

  for(i=n-1;i>=1;--i)
  {
    for(j=i+1;j<=n+n-i;j+=2)
      if(s[i][j])
      {
        if(s[i+1][j-1]&&s[i+1][j]&&s[i+1][j+1])
          a[i][j]=min(a[i+1][j-1],a[i+1][j+1])+1;
        else
          a[i][j]=1;
      }
  }
  for(i=1;i<=n;++i)
    for(j=i;j<=n+n-i;++j)
      if(maxn<a[i][j])
        maxn=a[i][j];
  fout<<maxn*maxn<<endl;
}

void init()
{
  int i,j;
  char c;
  fin>>n;
  for(i=1;i<=n;++i)
     for(j=i;j<=n+n-i;++j)
     {
       fin>>c;
       s[i][j]=(c=='-');
     }

}

int main()
{
  init();
  dp();
    
  fin.close();
  fout.close();
  return 0;
}
