//牛记数3
#include <iostream>
using namespace std;
int s,t,c;

int dfs(int i,int c,int x)//递归，i表示位数，c表示1的个数，x表示该数 
{
  if(i>24)//最多24位二进制数 
    if(x>=s && x<=t)//判断是否在区间内 
      return 1;
    else
      return 0;
   int ans=dfs(i+1,c,x);
   if(c<4)//当1的个数小于4时 
     ans+=dfs(i+1,c+1,x+(1<<i));//1<<i表示二进制1的位数逐渐左移 
   return ans;          
}

int main()
{
  freopen("cow.in","r",stdin);
  freopen("cow.out","w",stdout);
  cin>>s>>t;
  cout<<dfs(0,0,0)<<endl; 
  system("pause");
  return 0;
}
