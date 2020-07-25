////³ªÆ¬Â¼ÖÆ£­·½·¨3
#include<iostream>
using namespace std;

int g[21],f[21],n,t,m,l[21],ans;

int main()
{
  freopen("record.in","r",stdin);
  freopen("record.out","w",stdout);    
  cin>>n>>m>>t;
  for(int i=1;i<=n;i++)
  {
    cin>>l[i];
    for(int j=1;j<=i;j++)
    {
      int a,b;
      b=f[j-1]%100000;
      a=f[j-1]/100000;
      if(l[i]<=t-b)
      {
        a=a;
        b=b+l[i];
        g[j]=min(a*100000+b,f[j]);
      }
      else if(l[i]>t-b)
      {
        a=a+1;
        b=l[i];
        g[j]=min(f[j],a*100000+b);
      }
    }
    for(int j=i+1;j<=n;j++)
      g[j]=INT_MAX;
    for(int j=1;j<=n;j++)
      f[j]=g[j];
  }    
 for(int i=1;i<=n;i++)
    {
      int a,b;
      b=g[i]%100000;
      a=g[i]/100000;
      if(a*t+b<=m*t&&i>ans)
        ans=i;
    }  
  cout<<ans<<endl;
  return 0; 
}
