//shlqshËã·¨2
#include <iostream>
#define maxn 4000
using namespace std;

struct node
{
  int val,n;
}d[51];

int n,m,ans,sum;
int p[4001],last;
bool use[4001];

void pre()
{
    for(int i=2;i<=maxn;i++)
    {
        if(use[i])
          continue;
        p[++last]=i;
        int k=i+i;
        while(k<=maxn)  
          use[k]=1,k+=i;
    }
}

int divide(int x)
{
    int sum=0,rt=1;
    for(int i=1;i<=last && x!=1;i++,sum=0)
    {
        while(x%p[i]==0)
           x/=p[i],sum++;
        rt*=sum+1;
    }
    if(x>1)
      rt*=2;
    return rt;
}

int main()
{
    freopen("shlqsh.in","r",stdin);
    freopen("shlqsh.out","w",stdout);
    cin>>n>>m;
    pre();
    for(int i=n;i<=m;i++)
        ans+=divide(i);
    cout<<ans<<endl;
    return 0;
}
