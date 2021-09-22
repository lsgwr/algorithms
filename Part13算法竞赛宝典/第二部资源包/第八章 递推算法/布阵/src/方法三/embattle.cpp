//≤º’Û£≠∑Ω∑®3
#include <iostream>
using namespace std;
int i,j,n;
long long f1[30+1],f2[30+1];

int main()
{
  freopen("embattle.in","r",stdin);
  freopen("embattle.out","w",stdout);    
  cin>>n;
  f1[0]=1;f2[0]=1;
  for(i=1;i<=n+1;i++)
  {
   f1[i]=f1[i-1]+2*f2[i-1];
   f2[i]=f1[i-1]+f2[i-1];
  }
  cout<<f1[n]<<'\n';
  return 0;
}
