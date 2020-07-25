//传纸条  －费用流，已超过NOIP难度 
#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <cstring>
using namespace std;
const int MAXL=51,MAXN=MAXL*MAXL*2,MAXM=MAXN*3,INF=~0U>>1;

struct Queue
{
  int Q[MAXN],head,tail,size;
  bool inq[MAXN];
  Queue()
  {
    memset(inq,0,sizeof(inq));
    head=size =0;
    tail=-1;
  }
  void ins(int p)
  {
    inq[p]=true;
    if (++tail >= MAXN) 
      tail = 0;
    Q[tail] = p;
    size ++;
  }
  int pop()
  {
    int p=Q[head];
    if (++head >= MAXN) 
      head = 0;
    inq[p]=false;
    size --;
    return p;
  }
}Q;

struct edge
{
  edge *next,*op;
  int t,c,v;
}ES[MAXM],*V[MAXN],*fe[MAXN];

int N,M,EC,S,T,CostFlow;
int dist[MAXN],ft[MAXN];

inline void addedge(int a,int b,int v)
{
  ES[++EC].next = V[a]; 
  V[a]=ES+EC; 
  V[a]->t = b; 
  V[a]->c=1; 
  V[a]->v = v;
  ES[++EC].next = V[b]; 
  V[b]=ES+EC; 
  V[b]->t = a; 
  V[b]->c=0; 
  V[b]->v = -v;
  V[a]->op = V[b]; 
  V[b]->op = V[a];
}

void init()
{
  int i,j,a,b,c;
  freopen("message.in","r",stdin);
  freopen("message.out","w",stdout);
  scanf("%d%d",&N,&M);
  for (i=1;i<=N;i++)
  {
    for (j=1;j<=M;j++)
    {
      a=(i-1)*M+j;b=a+a;a=b-1;
      scanf("%d",&c);
      addedge(a,b,c);
    }
  }
  ES[EC-1].c=ES[1].c=2;
  for (i=1;i<=N;i++)
  {
    for (j=1;j<=M;j++)
    {
      a=((i-1)*M+j)*2;
      if (j+1<=M)
      {
        b = ((i-1)*M+j+1)*2 - 1;
        addedge(a,b,0);
      }
      if (i+1<=N)
      {
        b = (i*M+j)*2 - 1;
        addedge(a,b,0);
      }
    }
  }    
  S=1; T = N * M * 2;
}

bool spfa()
{
  int i,j;
  for (i=S;i<=T;i++)
    dist[i]=-INF;
  dist[S]=0;
  Q.ins(S);
  while (Q.size)
  {
    i= Q.pop();
    for (edge *e=V[i];e;e=e->next)
    {
      j=e->t;
      if (e->c && dist[i] + e->v > dist[j])
      {
        dist[j] = dist[i] + e->v;
        ft[j] = i;
        fe[j] = e;
        if (!Q.inq[j])
          Q.ins(j);
      }
    }
  }
  return dist[T]!=-INF;
}

void aug()
{
  int i,delta=INF;
  for (i=T;i!=S;i=ft[i])
    if (fe[i]->c < delta)
      delta = fe[i]->c;
  for (i=T;i!=S;i=ft[i])
  {
    fe[i]->c -= delta;
    fe[i]->op->c +=delta;
    CostFlow += fe[i]->v * delta;
  }
}

void solve()
{
  while(spfa())
    aug();
}

int main()
{
  freopen("message.in","r",stdin);
  freopen("message.out","w",stdout);    
  init();
  solve();
  printf("%d\n",CostFlow);
  return 0;
}
