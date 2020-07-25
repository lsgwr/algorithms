//最长公共子串问题 -算法1 
#include <iostream>
#include <cstdlib>
#define M 500
using namespace std;
int n1,n2;
char s1[M+1],s2[M+1];
int b[M+1],a[M+1];

void dp()
{
  int i,j,top,ans=0;
  for(i=1;i<=n1;i++)
  {
    top=b[0];
    for(j=1;j<=n2;j++)
    {
      if(s1[i]==s2[j])
        a[j]=top+1;
      if(top < b[j])
        top=b[j];//每次都拿前j-1个与第j个比较，记录最大值。
    }
    memcpy(b,a,sizeof(a));
  }
  for(i=1;i<=n2;i++)
    if(ans<a[i])
      ans=a[i];
  cout<<ans<<endl;
}

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

int main()
{
  freopen("LCS.in","r",stdin);
  freopen("LCS.out","w",stdout);
  init();
  dp();
  return 0;
}
