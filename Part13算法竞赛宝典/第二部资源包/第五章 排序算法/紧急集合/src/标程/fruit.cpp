//紧急集合 
#include <stdio.h>
#include <string.h>
#include <iostream>
#include <stdlib.h>
using namespace std;

int number[20000+1];//存放下标对应值的数的个数 
int BigNumber[15000+1];//顺序存放大于2万的群数据 
int n;//为人数 
int power;//消耗体力值 

void solve()
{
  int i,p,q;
  long total=0;
  p=1;//存放number数组没合并前的最小指针数 
  q=1;//存放BigNumber数组没合并前的最小指针数 
  i=0;//存放每次合并时已合并的群数 
  while(n>1)//如还没合并完，则继续合并 
  {
    if(p<=20000)//如有个数小于2万的群没有合并 
    {
      if(number[p]>0)//如果number下标对应群存在，则合并 
      {
        i++;
        total+=p;
        number[p]--;
      }
      else
        p++;//没有个数为p的值则往下找 
    }
    else//所有群的个数都大于2万，则顺序从number数据中取出群合并 
    {
      i++;
      total+=BigNumber[q];
      q++;
    }
    
    if((total<=20000)&&(i==2))//一次合并后（i=2)，且合并后的个数不超过2万 
    {
      number[total]++;//将合并的值个数放入小数组中 
      n--;
      i=0;
      power+=total;
      total=0;
    }
    else
      if(i==2)//一次合并完，且合并后的个数大于2万 
      {
        BigNumber[0]++;//哨兵，表示下一次取值从大数组的何位置取 
        BigNumber[BigNumber[0]]=total;//存入大数组 
        n--;
        power+=total;
        i=0;
        total=0;
      }
  }
  printf("%d\n",power);
}

void init()
{
  int i,k;
  scanf("%d",&n);
  for(i=1;i<=n;i++)
  {
    scanf("%d",&k);
    number[k]++;
  }
}

int main()
{
  freopen("fruit.in","r",stdin);
  freopen("fruit.out","w",stdout);
  power=0;
  init();//处理输入数据 
  solve();
  return 0;
}
