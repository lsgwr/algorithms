//组合问题-位运算 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;
int next_combination(int combin,int n)//根据前一组合枚举下一组合
{
  int next;//下一个组合 
  int first1 = combin & -combin;//找到右起第一个出现1的位置
  int t=(combin+first1);//在first1的位置上加1 
  next=(((t^combin)>>2)/first1) | t;
  if ((1 << n) < next)//如果越界，则退出 
    return 0;
  return next;
}

int main()
{
  freopen("combination.in","r",stdin);
  freopen("combination.out","w",stdout);  
  int M,N;
  cin>>M>>N;
  if(N==0)
  { cout<<endl;
    exit(0);
  }
  int combin=(1<<N)-1; //初始化为10000...000-1=1111...1111，即N个1 
  do
  {
    for (int i = 0; i<M; ++i)// 输出组合
      if ((combin & (1 << i))>0)//如果第i位为1,则表示组合中有第i个数 
        cout<<i+1;//因为下标从0开始，所以输出i+1 
    cout<<endl;
  }while(combin=next_combination(combin,M));
  return 0;
}
