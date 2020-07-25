//第Ｋ小数3 
#include<stdio.h>
#include<iostream>
#include<stdlib.h>
using namespace std;

long long a[109999];
long long b[109999];
long long n,m;

long long cmp(long long a,long long b)
{
  return a<b;
}

long long cal(long long v)
{
  long long ll,rr,mid,i,add=0;
  long long min,max;
  for(i=1;i<=n;i++)
  {
    min=a[i]+b[1];//上界 
    max=a[i]+b[m];//下界 
    if(v<min)
      break;
    if(v>=max)
    {
      add+=m;continue;
    }
    ll=1,rr=m;
    while(ll<=rr)//二分 
    {
      mid=(ll+rr)/2;
      if(v<(a[i]+b[mid])) 
        rr=mid-1;
      else 
        ll=mid+1;
    }
    if(v!=(a[i]+b[ll]))
      ll--;
    add+=ll;
  }
  return add;
}

long long find(long long ll,long long rr,long long k)
{
  long long mid,i;
  while(ll<=rr)
  {
    mid=(ll+rr)/2;
    if(k<=cal(mid)) 
      rr=mid-1;
    else 
      ll=mid+1;
  }
  return ll;
}

int main()
{
  freopen("k3.in","r",stdin);
  freopen("k3.out","w",stdout); 
  long long k,ll,rr;
  while(scanf("%lld%lld%lld",&n,&m,&k)!=EOF)
  {
    long long i;
    for(i=1;i<=n;i++)
      scanf("%lld",&a[i]);
    for(i=1;i<=m;i++)
      scanf("%lld",&b[i]);
    sort(&a[1],&a[n+1],cmp);        
    sort(&b[1],&b[1+m],cmp);

    ll=a[1]+b[1];
    rr=a[n]+b[m];
    printf("%lld\n",find(ll,rr,k));
  }
  return 0;
}
