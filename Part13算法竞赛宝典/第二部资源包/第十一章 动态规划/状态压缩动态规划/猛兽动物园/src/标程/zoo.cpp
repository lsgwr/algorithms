//ÃÍÊŞ¶¯ÎïÔ° 
#include<iostream>
#include<cstdio>
#include<cstring>
using namespace std;
 
const int MaxN=100010;
int Hate,Love,N,C,ans=0,E;
int F[MaxN][32]={0},num[MaxN][32];

void DFS(int x,int dep,int status)
{
  if (dep>4)
  {
    if((Love&status)||(Hate&status)!=Hate)
      num[E][status]++;
    return;
  }
  DFS(x,dep+1,status);
  DFS(x,dep+1,status|(1<<dep));
}

void Init()
{
  int i,H,L,x;
  scanf("%d%d",&N,&C);
  for (i=1;i<=C;i++)
  {
    scanf("%d%d%d",&E,&H,&L);
    Hate=Love=0;
    while (H--)
    {
      scanf("%d",&x);
      Hate=Hate|(1<<(x-E+N)%N);
    }
    while (L--)
    {
      scanf("%d",&x);
      Love=Love|(1<<(x-E+N)%N);
    }
    DFS(i,0,0);
  }
}

int Max(int a,int b)
{
  return a>b?a:b;
}

void DP(int st)
{
  int i,j,k,r,p,q,w1,w2;
  for (j=0;j<32;j++)
    F[0][j]=0;
  for (i=1;i<=N;i++)
    if (i<5)
      for (j=0;j<32;j++)
      {
        q=st>>(i-1);
        r=(1<<(5-i))-1;
        if ((j&r)==q)
        {
          w1=(j<<1)&31;w2=w1+1;
          F[i][j]=Max(F[i-1][w1],F[i-1][w2])+num[i][j];
        }
        else 
          F[i][j]=0;
      }
    else if(i+3<N)
      for (j=0;j<32;j++)
      {
        w1=(j<<1)&31;w2=w1+1;
        F[i][j]=Max(F[i-1][w1],F[i-1][w2])+num[i][j];
      }
    else
    {
      q=st&((1<<(i+4-N))-1);
      for (j=0;j<32;j++)
        if ((j>>(N-i+1))==q)
        {
          w1=(j<<1)&31;w2=w1+1;
          F[i][j]=Max(F[i-1][w1],F[i-1][w2])+num[i][j];
        }
        else 
          F[i][j]=0;
    }
    for (j=0;j<32;j++)
      if(ans<F[N][j])
        ans=F[N][j];
}

void Solve()
{
  for (int st=0;st<16;st++)
    DP(st);
  printf("%d\n",ans);
}

int main()
{
  freopen("zoo.in","r",stdin);
  freopen("zoo.out","w",stdout);    
  Init();
  Solve();
  return 0;
}
