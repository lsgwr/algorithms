//shlqshËã·¨1
#include <iostream>
using namespace std;
int n,m,ans;
int main()
{
  cin>>n>>m;
  for(int i=n;i<=m;i++)
    for(int j=1;j<=i;j++)
      if(i%j==0)
        ans++;
  cout<<ans<<endl;
  return 0;
}
