//逆转未来 
#include<iostream>
#include <cstdlib>
using namespace std;
const int MAX_N = 208;

struct  Tree
{ int lastSon;//最后一个孩子 
  int left;//儿子(左） 
  int right;//兄弟（右） 
  int value;//权重
}tree[MAX_N];

int idx,N,M;
int dp[MAX_N][MAX_N];

inline int MAX(int a,int b)
{ 
  return a>b?a:b;
}

void Init()
{ 
  int i,j; 
  for(i=0;i<=N;i++) //初始化
  {  
    tree[i].lastSon=-1;  
    tree[i].right=-1;  
    tree[i].left=-1;  
    tree[i].value=0;  
    for(j=0;j<=N;j++)     
      dp[i][j]=-1;  
  }
}

void BuildTree(int son,int parent)//建树 
{ 
  if(tree[parent].lastSon==-1)//左孩子 
  {  
    tree[parent].left=son; 
  } 
  else //右兄弟
  {  
    tree[tree[parent].lastSon].right=son; 
  } 
  tree[parent].lastSon=son;
}

bool Read()//读入数据 
{ 
  int p,i; 
  if(scanf("%d%d",&N,&M)&&!N&&!M) 
    return false; 
 
  Init(); 
  for(M++,i=1;i<=N;i++)
  {  
    scanf("%d%d",&p,&tree[i].value);  
    BuildTree(i,p); 
  } 
  return true;
}

int DP(int node,int k)
{ 
  if(node==-1||k==0) //如果node或者k==0
  {  
    return 0; 
  } 
  if(dp[node][k]!=-1)//如果已经求出值了  
  { 
    return dp[node][k];//则直接返回 
  } 
  int i,tmp; 
  dp[node][k]=DP(tree[node].right,k); 
  for(i=1;i<=k;i++) 
  {  
    tmp=DP(tree[node].left,i-1)+tree[node].value+DP(tree[node].right,k-i);  
    dp[node][k]=MAX(dp[node][k],tmp); 
  } 
  return dp[node][k];
}

int main()
{ 
  freopen("reverse.in","r",stdin);
  freopen("reverse.ans","w",stdout);  
  while(Read())
  {  
    DP(0,M); 
    printf("%d\n",dp[0][M]);
  }
  return 0;
}
