//高低进制数 1
#include <iostream>
using namespace std;

int main()
{
   freopen("sum.in","r",stdin);
   freopen("sum.out","w",stdout);
   long long n,s=0,i=31,b[32]={0};
   long long a[]={32768,     16384,     8192,       4096,
             2048,      1024,      512,        256,
             128,       64,        32,         16,
             8,         4,         2,          1,
             2147483648ll,1073741824ll,536870912ll,  268435456,
             134217728, 67108864,  33554432,   16777216,
             8388608,   4194304,   2097152,    1048576,
             524288,    262144,    131072,     65536   };
  cin>>n;
  while(n!=0)
  {
    b[i--]=n%2;
    n=n/2;
  }
  
  for(i=0;i<32;i++)
    if(b[i]==1)
      s+=a[i];
  cout<<s<<endl;
  return 0;    
}
