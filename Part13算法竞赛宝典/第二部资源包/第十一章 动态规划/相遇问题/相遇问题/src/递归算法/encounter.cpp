//奇妙的相遇－递归算法 
#include<iostream>
#include<iomanip>
using namespace std;

int n,k,Dir[101][101];
double ans;
bool encounter=1;

void init()
{
  cin>>n;
  k=(n+1)/2;
  if(k%2==1)//永远不可能相遇的情况 
  {
    encounter=0;
    return;
  }
  for(int i=0;i<=n+1;++i) //统计(x,y)的路径数 
    for(int j=0;j<=n+1;++j)
    {
      if((i==1&&j==1)||(i==1&&j==n)||(i==n&&j==1)||(i==n&&j==n))    
        Dir[i][j]=2;
      else if(i==1||i==n||j==1||j==n)
        Dir[i][j]=3;
      else 
        Dir[i][j]=4;
    }       
}

void fun(int t,int x,int y,double lv)//第t天公主在坐标x,y的概率为lv 
{
  if(x<1||x>n||y<1||y>n||t>n)//若(x,y)越界或者时间超过，则返回 
    return;
  if(x-(abs(y-k))<t) //若公主(x,y)无论如何都超不过王子(t,k)则剪枝 
    return; 
  if(t==x && y==k)  //若王子与公主相遇 
  {
    ans+=lv;//概率累加    
    return;
  }
  fun(t+1,x+1,y,lv/Dir[x][y]);   //向右尝试 
  fun(t+1,x-1,y,lv/Dir[x][y]);   //向左尝试 
  fun(t+1,x,y+1,lv/Dir[x][y]);   //向上尝试 
  fun(t+1,x,y-1,lv/Dir[x][y]);   //向下尝试 
  return;
}

int main()
{
  freopen("encounter.in","r",stdin);
  freopen("encounter.out","w",stdout);  
  init();
  if(encounter) //如果有相遇的可能，则递归求解 
    fun(0,k,k,1);//时间为0时公主在(k,k)坐标处概率为1 
  cout<<setprecision(4)<<ans<<endl;
  return 0;    
}
