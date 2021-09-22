//最长公共子串问题 -算法3
#include <iostream>
#include <cstdlib>
#define M 500
using namespace std;

int n1,n2;
char s1[M+1],s2[M+1];
int b[M+1],a[M+1];

void init()
{
  int i;
  cin>>n1;
  for(i=1;i<=n1;i++)
    cin>>s1[i];
  cin>>n2;
  for(i=1;i<=n2;i++)
    cin>>s2[i];
}

void dp()
{
  int i,j,top,ans=0;
  for(i=1;i<=n1;++i)
  {
    for(j=1;j<=n2;++j)
    {
      if(s1[i]==s2[j]) 
        a[j]=b[j-1]+1;
      else 
        a[j]=max(a[j-1],b[j]);
    }
    memcpy(b,a,sizeof(a));
  }
  cout<<a[n2]<<endl;
}

int main()
{
  freopen("LCS.in","r",stdin);
  freopen("LCS.out","w",stdout);    
  init();
  dp();
  return 0;
}
