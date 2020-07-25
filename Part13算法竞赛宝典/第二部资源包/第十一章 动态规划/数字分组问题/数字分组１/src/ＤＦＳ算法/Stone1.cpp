//Êý×Ö·Ö×é£±-DFS 
#include<iostream>
#include<stdlib.h>
#define maxn 27
#define INF 20000007
using namespace std;

long n,sum,ans,w[maxn];

void dfs(long dep,long now)
{
  if(dep>n)
  {
    if(ans>abs(now-(sum-now))) ans=abs(now-(sum-now));
      return;
  }
  dfs(dep+1,now);
  dfs(dep+1,now+w[dep]);
}

int main()
{
  freopen("Stone1.in","r",stdin);  
  freopen("Stone1.out","w",stdout);     
  cin>>n;
  sum=0;
  for(long i=1;i<=n;i++)
  {
    cin>>w[i];
    sum+=w[i];
  }
  ans=INF;
  dfs(1,0);
  cout<<ans<<endl;
  return 0;
}
