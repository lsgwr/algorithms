//传球游戏-递归搜索 
#include <iostream>
#include <cstdlib>
using namespace std;
int n,m,sum;

void pp(int k,int step)
{
  if(step==m)//如果传了m步 
  {
    if(k==1)//恰好传到本人手里，总数加1 
      sum++;
    return;
  }
  if(k==n)//如果球在第n个人手里， 
  {
    pp(1,step+1);//他或者传给第一个人 
    pp(k-1,step+1);//他或者传给n-1人 
  }
  if(k==1)//如果球在第一人手里 
  {
    pp(n,step+1);//或者传给第n人 
    pp(k+1,step+1);//或者传给第2人 
  }
  if(k>1 && k<n)//如果在1和n之间的任何一人 
  {
    pp(k+1,step+1);//或者传给右边 
    pp(k-1,step+1);//或者传给左边 
  }
}

int main()
{
  freopen("ball.in","r",stdin);
  freopen("ball.out","w",stdout);  
  cin>>n>>m;
  pp(1,0);
  cout<<sum<<endl;
  return 0;
}
