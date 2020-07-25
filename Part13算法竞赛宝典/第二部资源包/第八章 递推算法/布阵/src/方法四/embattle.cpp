//布阵－方法4
#include <iostream>
#include <cstdlib>
using namespace std;

int main()
{
  freopen("embattle.in","r",stdin);
  freopen("embattle.out","w",stdout); 
  long long n,i,f[2]={1,3};//用到的只有前两个数字，故采用滚动数组
  cin>>n;
  for(i=2;i<=n;i++)
    f[i%2]=2*f[(i-1)%2]+f[(i-2)%2];
  cout<<f[n%2]<<'\n';
  return 0;
}
