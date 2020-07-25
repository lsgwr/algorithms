//猛兽军团1－高精度 
#include <iostream>
#include <cstdlib>
using namespace std;
#define MAXN 3000 //这里n就不再只限于10，产生的状态个数很多
#define MAXK 110

int f[2][MAXN][MAXK][100],Ans[100];
int N,K;
int s[MAXN],num[MAXN];
int next[MAXN*10],h[MAXN*10],st[MAXN],flag;

inline int getcan(int x)
{
  if( (x<<1) & x )    
    return 0;
  return 1;
}

inline int getnum(int x)
{
  int t=0;
  while(x>0)
  {
    x=x&(x-1);
    t++;
  }
  return t;
}

void add(int one,int two)
{
  next[++flag]=st[one];
  h[flag]=two;
  st[one]=flag;
}

void ad(int a[],int b[])//高精度加法
{
  int len=max(a[0],b[0]);
  for(int i=1;i<=len;i++)
    a[i]+=b[i];
        
  for(int i=1;i<100;i++)
    if(a[i]>=10)
    {
      a[i+1]+=a[i]/10;
      a[i]=a[i]%10;
    }
    
  int i=99;
  while(a[i]==0 && i>=1)
    i--;
  a[0]=i;
}

void init()
{
  cin>>N>>K;
  for(int i=0;i<(1<<N);i++)
    if(getcan(i))
    {
      ++s[0];
      s[ s[0] ]=i;
      num[ s[0] ]=getnum(i);
    }
  add(1,1);
  int s1,s2;
  for(int i=1;i<=s[0];i++)
    for(int j=i+1;j<=s[0];j++)
    {
      s1=s[i],s2=s[j];
      if( ( (s2<<1) & s1) || ( (s2>>1) & s1) || (s1&s2) )
        continue;
      add(i,j);add(j,i);
    }
}

void Dp()
{
  f[0][1][0][0]=f[0][1][0][1]=1;
  int s1,s2,m=1,dm;
  for(int i=1;i<=N;i++)
  {
    dm=m^1;
    for(int a=0;a<=s[0];a++)
      for(int b=0;b<=K;b++) 
        for(int c=0;c<100;c++)
          f[m][a][b][c]=0;
    for(int a=1;a<=s[0];a++)
      for(int k=0;k<=K-num[a];k++)
        for(int b=st[a];b!=0;b=next[b])
        {
          s2=h[b];
          ad(f[m][a][k+num[a]],f[dm][s2][k]);
        }
    m=dm;
  }
  m=m^1;
  for(int i=1;i<=s[0];i++)
    ad(Ans,f[m][i][K]);
  for(int i=Ans[0];i>=1;i--)
    cout<<Ans[i];
  if(Ans[0]==0) 
    cout<<0<<endl;
}

int main()
{
  freopen("embattle.in","r",stdin);
  freopen("embattle.out","w",stdout);     
  init();
  Dp();
  return 0;
}
