//最长公共子串问题 -算法４
#include <iostream>
#include <cstdlib>
#define M 500
using namespace std;

char s1[M+1],s2[M+1];
int a[M+1];
int n1,n2;

void dp()
{
  int i,j;
  for(i=1;i<=n1;++i)
  {
    for(j=n2;j>=1;--j)
      if(s1[i]==s2[j])
        a[j]=a[j-1]+1;
    for(j=1;j<=n2;++j)
      a[j]>?=a[j-1];
  }
  cout<<a[n2]<<endl;
}

void init()
{
  int i;
  cin>>n1;
  for(i=1;i<=n1;++i)
    cin>>s1[i];
  cin>>n2;
  for(i=1;i<=n2;++i)
    cin>>s2[i];
}

int main()
{
  freopen("LCS.in","r",stdin);
  freopen("LCS.out","w",stdout);     
  init();
  dp();
  return 0;
}
