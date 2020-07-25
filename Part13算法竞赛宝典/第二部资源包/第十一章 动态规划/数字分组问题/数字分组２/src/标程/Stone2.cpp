//Êý×Ö·Ö×é£² 
#include <iostream>
#include <cstdlib>
using namespace std;
int lim,a[100],sum,f[100000];

int main()
{
  freopen("Stone2.in","r",stdin);
  freopen("Stone2.out","w",stdout);  
  int n;
  cin>>n;
  f[0]=true;
  for (int i=1;i<=n;++i)
  {
    cin>>a[i];
    lim=max(a[i],lim);
    sum+=a[i];
  }
  for (int j=1;j<=n;++j)
    for (int i=a[j];i<=sum/2;++i)
      if (!f[i])
        f[i]=f[i-a[j]];
  for (int i=(sum-lim+1)/2;i<=sum/2;++i)
    if (f[i])
    {
      cout<<sum-2*i<<'\n';
      return 0;
    }
}
