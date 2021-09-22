//背包问题-递归解法 
#include <iostream>
#include <cstdlib>
using namespace std;
#define MAXN 40

int W[MAXN];//各物品重量

int knapsack(int s,int n)//s为剩余重量，n为剩余可选物品数
{
  if(s==0)// 如果正好装满 
    return 1;
  if(s<0||(s> 0 && n<1))//如s<0或n<1则不能完成
    return 0;
  if(knapsack(s-W[n],n-1))//从后往前装，装上W[n]后，若剩余物品仍有解
  {
    cout<<W[n]<<" ";//则装进第n个包，并输出
    return 1;
  }
  return knapsack(s,n-1);//如装了第n个包后，导致无解，则删除该包，尝试第n-1个
}

int main()
{
  freopen("Backpack.in","r",stdin);
  freopen("Backpack.out","w",stdout);    
  int S,N;  
  cin>>S>>N; 
  for(int i=1;i<=N;++i)
    cin>>W[i]; 
  if(knapsack(S,N))
    cout<<"\n";
  else 
    cout<<"Failed!\n";
  return 0;
}
