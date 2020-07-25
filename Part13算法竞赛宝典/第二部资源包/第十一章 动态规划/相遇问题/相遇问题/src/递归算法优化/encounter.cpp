//奇妙的相遇－递归算法优化 
#include<iostream>
#include<iomanip>
using namespace std;

int n;
const int MAX=312; 
float way[MAX+1][MAX+1][MAX+1];  

float go(int day,int x,int y,float m)
{
  if(way[day][x][y]==-1)//如果被标记过不能遇到王子,返回 
    return 0;
  if(day>n||way[MAX][x][y]==0)//如果超时或者超范围（即不等于2，3，4）,标记并退出 
  {
    way[day][x][y]=-1;
    return 0;
  }
  if(way[day][x][y]!=0)//如果已经求过从此时刻此位置到王子的几率 ,返回几率 
    return way[day][x][y];
  if(day==x && y==(n>>1)+1)//如果公主与王子相遇,返回几率 
  {
    way[day][x][y]=m;
    return m;
  }
  float tem=0;
  tem=go(day+1,x+1,y,m)+go(day+1,x-1,y,m)+go(day+1,x,y+1,m)+go(day+1,x,y-1,m); 
  if(tem==0) //如果没有遇到王子,标记并退出 
  {
    way[day][x][y]=-1;
    return 0;
  }
  way[day][x][y]=tem/way[MAX][x][y];//否则,求出从此时此刻碰到王子的几率 
  return way[day][x][y];
}

int main()
{
  freopen("encounter.in","r",stdin);
  freopen("encounter.out","w",stdout);  
  float ans;
  cin>>n;
  if(n%4==1)
    cout<<0<<endl;
  else
  {
    for(int i=2;i<=n-1;i++)//将way[MAX][i][j]初始化为路径数，因为MAX>n 
    {
      for(int j=2;j<=n-1;j++)
        way[MAX][i][j]=4;
      way[MAX][1][i]=way[MAX][i][1]=way[MAX][n][i]=way[MAX][i][n]=3;
    }
    way[MAX][1][1]=way[MAX][1][n]=way[MAX][n][1]=way[MAX][n][n]=2;
    ans=go(0,(n/2)+1,((n/2)+1),1);//从0时刻起公主在((n/2)+1,(n/2)+1)遇到王子的几率
    cout<<setprecision(4)<<ans<<endl; 
  }
  return 0;
}
