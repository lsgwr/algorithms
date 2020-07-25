//shlqshËã·¨3
#include <iostream>
using namespace std;
int f[100000001],ans,n,m;
int main()
{
    cin>>n>>m;
    for(int i=1,j;j=i,i<=m;++i)
      while(j<=m)
         f[j]++,j+=i;
    for(int i=n;i<=m;i++)
       ans+=f[i];
    cout<<ans<<endl;
    return 0;
}
