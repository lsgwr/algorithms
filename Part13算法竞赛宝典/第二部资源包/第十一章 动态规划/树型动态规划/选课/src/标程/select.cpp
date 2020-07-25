//选课 
#include <iostream>
#include <cstdlib>
using namespace std;
const int MAXN=301;
typedef struct
{
  int l,r,c;
}tree;
tree node[MAXN];
int f[MAXN][MAXN];
int M,N;

inline int MAX(int a,int b)
{
  return a>b?a:b;
}

inline void mody(int i,int j)//将节点插入二叉树 
{
  node[i].r=node[j].l;
  node[j].l=i;
  return;
}

void init()
{
  freopen("select.in","r",stdin);
  freopen("select.out","w",stdout);
  scanf("%d %d\n",&N,&M);
  int i,a,b;
  memset(node,-1,sizeof(node));
  memset(f,-1,sizeof(f));
  for(i=1;i<=N;i++)
  {
    scanf("%d %d\n",&a,&b);
    mody(i,a);//多叉树转二叉树 
    node[i].c=b;
  }
  return;
}

int DFS(int x,int y)
{
  if(!y || x<0)
    return 0;
  if(!x)
    return DFS(node[x].l,y); //根节点是没有兄弟的
  int i;
  if(f[x][y]>=0)
    return f[x][y];
  f[x][y]=DFS(node[x].r,y);
  for(i=1;i<=y;i++)
    f[x][y]=MAX(f[x][y],DFS(node[x].l,i-1)+node[x].c+DFS(node[x].r,y-i));
  return f[x][y];
}

int main()
{ 
  init();
  printf("%d",DFS(0,M));
  return 0;
}
