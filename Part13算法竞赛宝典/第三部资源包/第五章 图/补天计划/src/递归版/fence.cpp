/*
程序名称：补天计划（递归版）
程序说明：这是典型的欧拉回路问题，找到奇点最小的开始，
如均为偶点，从1开始。如该点无相连的点，就将该点加入路径后返回，
如该点有相连的点，则深度搜索。处理一个点就删除相应的边。
递归可能引起系统堆栈崩溃，应自建堆栈处理。 
*/ 
#include <iostream>
using namespace std;
int n,i,j,s,t,m;
int net[500+1][500+1];//存储网络图 
int d[500+1];//计算度 
int path[1025+1];//保存路径 

void search(int s)//深度搜索 
{
  int i;
  for(i=1;i<=m;i++)
    if (net[s][i]>0)//如果与其他点有边，则继续搜索 
    {
      net[s][i]--;
      net[i][s]--;
      search(i);              
    }     
  j++;//如果没有边了，则将该点放进路径中 
  path[j]=s;  
}

void init()
{
  freopen("fence.in","r",stdin);
  freopen("fence.out","w",stdout);
  cin>>n;
  for(i=1;i<=n;i++)
  {
    cin>>s>>t;
    net[s][t]++;
    net[t][s]++;
    d[s]++;
    d[t]++;
    if(s>m)
      m=s;
    if(t>m)
      m=t;    
  }
  s=1;//找到起始点，先假设从1开始 
  for(i=1;i<=m;i++)//如果有奇点，则从奇点开始 
  {
    if(d[i]%2!=0)
      s=i;
    break;
  }
}

int main()
{
  init();
  j=0;//路径下标初始为0 
  search(s);
  
  for(i=n+1;i>=1;i--)//打印 
    cout<<path[i]<<endl;
  return 0;  
}
