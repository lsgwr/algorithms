//¶àÖØ±³°ü£­£Ä£Ğ 
#include <iostream>
#include <cstdlib>
using namespace std;

int num[101],val[101],weig[101];
int dp[101];

int main()
{
  freopen("Mbag.in","r",stdin);
  freopen("Mbag.out","w",stdout);  
  int V,n;
  cin>>V>>n;
  for(int i=0; i<n; i++) 
    cin>>weig[i]>>val[i]>>num[i];
  int ans=-1;
  for(int i=0; i<n; i++)
    for(int j=0; j<num[i]; j++)
      for(int k=V; k>=weig[i]; k--)
      {
        dp[k]=max(dp[k],dp[k-weig[i]]+val[i]);
        if(dp[k]>ans) 
          ans=dp[k];
      }
  cout<<ans<<endl;
  return 0;
}
