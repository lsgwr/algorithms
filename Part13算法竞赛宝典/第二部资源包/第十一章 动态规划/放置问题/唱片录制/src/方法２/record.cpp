//³ªÆ¬Â¼ÖÆ£­·½·¨£² 
#include<iostream>
using namespace std;
int g[21][21],l[21],ans=INT_MIN;

int main()
{
  freopen("record.in","r",stdin);
  freopen("record.out","w",stdout);  
  int n,m,t;
  cin>>n>>m>>t;
  for(int i=1;i<=n;i++)
    cin>>l[i];
  for(int i=1;i<=n;i++)
    for(int j=i+1;j<=n;j++)
        g[i][j]=INT_MAX;
  for(int i=1;i<=n;i++)
    for(int j=1;j<=i;j++)
    {
      int a,b;
      b=g[i-1][j-1]%100000;
      a=g[i-1][j-1]/100000;
      if(l[i]<=t-b)
      {
        a=a;
        b=b+l[i];
        g[i][j]=min(a*100000+b,g[i-1][j]);    
      }      
      else if(l[i]>t-b)
      {
        a=a+1;
        b=l[i];
        g[i][j]=min(g[i-1][j],a*100000+b);
      }
    }
   for(int i=1;i<=n;i++)
    {
      int a,b;
      b=g[n][i]%100000;
      a=g[n][i]/100000;
      if(a*t+b<=m*t&&i>ans)
        ans=i;
    }   
  cout<<ans<<endl;
  return 0;
}
