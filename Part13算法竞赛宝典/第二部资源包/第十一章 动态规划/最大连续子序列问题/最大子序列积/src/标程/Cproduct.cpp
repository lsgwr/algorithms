//最大子序列积 
#include <iostream>
using namespace std;
long long a[1001],g[1001],f[1001];

int main()
{
  freopen("Cproduct.in","r",stdin);
  freopen("Cproduct.out","w",stdout);  
  int n;
  cin>>n;
  for(int i=1;i<=n;i++)
    cin>>a[i];
  f[1]=a[1];
  g[1]=a[1];
  for(int i=2;i<=n;i++)
  {
    f[i]=a[i];
    f[i]>?=f[i-1]*a[i];
    f[i]>?=g[i-1]*a[i];
        
    g[i]=a[i];
    g[i]<?=f[i-1]*a[i];
    g[i]<?=g[i-1]*a[i]; 
  }
  long long maxx=-9223372036854775800LL,minn=9223372036854775800ll;
  for(int i=1;i<=n;i++)
    {
       if(f[i]>maxx) maxx=f[i];
       if(g[i]<minn) minn=g[i];    
    }
  cout<<maxx<<endl;
  cout<<minn<<endl;
  return 0;
}
