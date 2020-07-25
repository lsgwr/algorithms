//回家 
#include<iostream>
#include<vector>
#include<cmath>
using namespace std;
const int MAXN = 110;
const int inf = -0x3ffffff;
struct node
{
  int pos_x,pos_y;
  int node_num;
  node()
  {
    pos_x=0,pos_y=0;
    node_num=0;
  }
};
int bin_map[MAXN][MAXN];
char ch[MAXN][MAXN];
bool vis_x[MAXN],vis_y[MAXN];
int lx[MAXN],ly[MAXN];
int result[MAXN];

bool find(int a,int n)//判断第a能否找到房子
{
  vis_x[a] = true;
  for(int i=1;i!=n;i++)
  {
    if(!vis_y[i]&&bin_map[a][i]==lx[a]+ly[i])
    {
      vis_y[i] = true;
      if(result[i]==-1||find(result[i],n))
      {
		result[i] = a;
		return true;
      }
    }
  }
  return false;
}
int best_match(int n)//开始匹配
{
  memset(ly,0,sizeof(ly));
  for(int i=1;i!=n;i++)
  {
    lx[i] = inf;
    for(int j=1;j!=n;j++)
    {
      if(lx[i]<bin_map[i][j])
		lx[i] = bin_map[i][j];
	}
  }
  memset(result,-1,sizeof(result));
  for(int u=1;u!=n;u++)
  {
	while(1)
	{
	  memset(vis_x,false,sizeof(vis_x));
	  memset(vis_y,false,sizeof(vis_y));
	  if(find(u,n))//
		break;
	  int dx = -inf;
	  for(int i=1;i!=n;i++)
	  {
		if(vis_x[i])
		{
		  for(int j=1;j!=n;j++)
		  {
			if(!vis_y[j])
		  	  dx = min(dx,lx[i]+ly[j]-bin_map[i][j]);
		  }
		}
	  }
	  for(int i=1;i!=n+1;i++)
	  {
		if(vis_x[i])
	 	 lx[i]-=dx;
		if(vis_y[i])
		 ly[i]+=dx;
      }
	}
  }
  int sum = 0;
  for(int i=1;i!=n;i++)
  {
	sum-=bin_map[result[i]][i];
  }
  return sum;
}

int main()
{
  int m,n;
  while(cin>>m>>n&&(m!=0&&n!=0))
  {
    vector<node> men;
    vector<node> house;
    int count_m(0),count_h(0);
    for(int i=1;i!=m+1;i++)
    {
      for(int j=1;j!=n+1;j++)
	  {
		cin>>ch[i][j];
		if(ch[i][j]=='m')
		{
		  count_m++;
		  node N;N.pos_x = j;N.pos_y = i;N.node_num = count_m;
		  men.push_back(N);
		}
		if(ch[i][j]=='H')
		{
		  count_h++;
		  node N;N.pos_x = j;N.pos_y = i;N.node_num = count_h;
		  house.push_back(N);
		}
      }
    }
    memset(bin_map,0,sizeof(bin_map));
	for(vector<node> ::size_type t=0;t!=house.size();t++)//构造二分图
	{
	  node N1 = men[t]; 
	  for(vector<node> ::size_type i=0;i!=house.size();i++)
	  {
		int x2 = house[i].pos_x;int y2 = house[i].pos_y;
		int x1 = N1.pos_x;int y1 = N1.pos_y;
		int num = -(abs(x1-x2)+abs(y1-y2));//值变为负数转化为，求最大匹配
		bin_map[N1.node_num][house[i].node_num] = num; 
	  }
	}
	int cost = best_match(count_m+1);
	cout<<cost<<endl;
  }system("pause");
  return 0;
}
