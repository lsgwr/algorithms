//布阵－方法二 
#include <iostream>
using namespace std;
int i,j,n;
long long f[20+1];
int main()
{
  freopen("embattle.in","r",stdin);
  freopen("embattle.out","w",stdout);     
  cin>>n;
  f[0]=1;f[1]=1;
  for(i=2;i<=n+1;i++)
  {
   f[i]=f[i-1];
   for(j=1;j<=i;j++)
     f[i]+=2*f[i-j-1];
  }
  cout<<f[n+1]<<endl;
  return 0;
}
