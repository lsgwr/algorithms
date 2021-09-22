//出租车 
#include <iostream>
using namespace std;

struct P
{
  int ti;
  int a,b,c,d;
};

P A[505];
bool used[505],map[505][505];
int Link[505],n;

int dfs(int x)
{
  int i,j;
  for(i=0;i<n;i++)
    if(map[x][i] && !used[i])
    {
      used[i]=1;
      j=Link[i];
      Link[i]=x;
      if(j==-1 || dfs(j)) 
        return true;
      Link[i]=j;
    }
  return false;
}

int fun()
{
  int i,num=0;
  memset(Link,-1,sizeof(Link));
  for(i=0;i<n;i++)
  {
    memset(used,0,sizeof(used));
    if(dfs(i)) 
      num++;
  }
  return n-num;
}

bool pd(int x,int y)
{
  int temp;
  if(x==y) 
    return 0;
  //做a任务所用的时间+a任务开始的时间  
  temp=abs(A[x].a-A[x].c)+abs(A[x].b-A[x].d)+A[x].ti;
  //a任务做完后再做b任务的时间
  temp+=abs(A[x].c-A[y].a)+abs(A[x].d-A[y].b);				
  if(temp<A[y].ti) 
    return 1;
  return 0;
}

int main()
{
  int h,m,N;
  cin>>N;
  while(N--)
  {
    cin>>n;
    for(int i=0;i<n;i++)
    {
	  cin>>h>>m;
      A[i].ti=h*60+m;
      cin>>A[i].a>>A[i].b>>A[i].c>>A[i].d;
    }
    for(int i=0;i<n;i++)
    {
      for(int j=0;j<n;j++)
        map[i][j]=pd(i,j);
    }
    cout<<fun()<<endl;
  }
  return 0;
}
