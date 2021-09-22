//…ÃµÍπ∫ŒÔ 
#include<iostream>
#include<cstring>
#include <cstdlib>
using namespace std;
#define MAXN 10

typedef struct
{
  int c;
  int m;
}NODE;

NODE s[MAXN];
long f[MAXN][MAXN][MAXN][MAXN][MAXN];
int sum[105][1005];
int ans,t;

long min(long x,long y)
{
  return x<y?x:y;
}

int main()
{
  freopen("shopping2.in","r",stdin);
  freopen("shopping2.out","w",stdout);
  int i,j,n,l,a1,a2,a3,a4,a5,b1,b2,b3,b4,b5;
  cin>>ans;
  memset(sum,0,sizeof(sum));
  for(i=1;i<=ans;i++)
  {
    cin>>n;
    for(j=1;j<=n;j++)
    {
      cin>>l;
      cin>>sum[i][l];
    }
    cin>>sum[i][1001];
  }
  cin>>t;
  for(i=1;i<=t;i++)
  {
    cin>>s[i].c>>s[i].m>>sum[++ans][1001];
    sum[ans][s[i].c]=1;
  }
  memset(f,1,sizeof(f));
  f[0][0][0][0][0]=0;
  for(i=1;i<=ans;i++)
  {
    a1=sum[i][s[1].c];
    a2=sum[i][s[2].c];
    a3=sum[i][s[3].c];
    a4=sum[i][s[4].c];
    a5=sum[i][s[5].c];
    for(b1=a1;b1<=s[1].m;b1++)
      for(b2=a2;b2<=s[2].m;b2++)
        for(b3=a3;b3<=s[3].m;b3++)
          for(b4=a4;b4<=s[4].m;b4++)
            for(b5=a5;b5<=s[5].m;b5++)
    f[b1][b2][b3][b4][b5]=
    min(f[b1][b2][b3][b4][b5],f[b1-a1][b2-a2][b3-a3][b4-a4][b5-a5]+sum[i][1001]);
  }
  cout<<f[s[1].m][s[2].m][s[3].m][s[4].m][s[5].m]<<endl;
  return 0;
}
