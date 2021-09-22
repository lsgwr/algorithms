//数的计数 -递推 
#include <iostream>
#include <cstdlib>
using namespace std;

int n,i,j;
long double f[1000+1];
int main()
{
  freopen("people.in","r",stdin);
  freopen("people.out","w",stdout);
  cin>>n;
  f[1]=1;
  for(i=2;i<=n;i++)
  {
    f[i]=f[i-1];
    if(i%2==0)
      f[i]=f[i]+f[i/2];
  }
  cout<<f[n]<<endl;
  return 0;
}
