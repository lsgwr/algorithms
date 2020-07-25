//shlqshËã·¨4
#include <iostream>
using namespace std;
int ans1,ans2,n,m;

int main()
{
   freopen("shlqsh.in","r",stdin);
   freopen("shlqsh.out","w",stdout);
   cin>>n>>m;
   for(int i=1;i<=n-1;i++)
      ans1+=(n-1)/i;
   for(int i=1;i<=m;i++)
      ans2+=m/i;
   cout<<ans2-ans1<<endl;
   return 0;
}
