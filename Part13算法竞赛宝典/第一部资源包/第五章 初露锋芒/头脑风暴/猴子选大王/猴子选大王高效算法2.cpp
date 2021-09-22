//µ¹ÍÆËã·¨
#include <iostream>
using namespace std;
unsigned long i,m,k,ans;
int main()
{
  cin>>m>>k;
  ans=0;
  for(i=2;i<=m;i++)
    ans=(ans+k)%i;
  cout<<ans+1<<endl;
  return 0;
}    
