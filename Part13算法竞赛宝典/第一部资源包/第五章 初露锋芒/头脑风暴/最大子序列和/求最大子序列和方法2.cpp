//最大子序列和 方法2
#include <iostream>
using namespace std;
#define MAX 10000000
int n,i,j,maxn,s[MAX+1],a[MAX+1];

int main()
{
  freopen("sum.in","r",stdin);
  freopen("sum.out","w",stdout);  
  cin>>n;
  s[0]=0;
  for(int i=1;i<=n;i++)
  {
    cin>>a[i];
    s[i]=s[i-1]+a[i];
  } 
  maxn=a[1];
  for(i=1;i<=n;i++)
    for(j=i;j<=n;j++)
      if(s[j]-s[i-1]>maxn)
        maxn=s[j]-s[i-1];
  cout<<maxn<<endl;  
  return 0;
}
