//普通背包问题-枚举算法 
#include <iostream>
#include <cstdlib>
using namespace std;
int N,S;
int W[40];// 初始化每个物品的重量
int flag[40]={0};// 标记数组

void Print()// 打印结果
{
  for(int i=0;i!=N;i++)
  if (1 == flag[i])
    cout<<W[i]<<" ";
  cout<<endl;
  exit(0);
}

int main()
{
  freopen("Backpack.in","r",stdin);
  freopen("Backpack.out","w",stdout);  
  int sum,all_count=1;  
  cin>>S>>N;
  for(int i=0;i<N;i++)
    cin>>W[i];
  for (int i=0;i!=N;i++)// 计算所有可能性次数,即2的n次方
    all_count*=2;
    
  for(int num=0;num<=all_count;++num)
  {
    for (int i=0;i!=N;i++)//列举所有flag数组可能
      if ( flag[i]==0 )
      {
        flag[i]=1;
        continue;
      }
      else 
      {
        flag[i]=0;
        break;
      }
      sum=0;//本次重量初始化为0
      for (int i=0;i!=N;i++)//按标记计算所有选中物品重量和
        if (flag[i]==1)
          sum+=W[i];
      if (sum == S)//打印方案 
        Print();        
  } 
  cout<<"Failed!\n";  
  return 0;
}
