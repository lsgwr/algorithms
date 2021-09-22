//求13的13次方的最后三位数
#include<iostream>
using namespace std;

int main()
{
  int i,x,y,last=1;    /*变量last保存乘积的后三位*/
  cin>>x>>y;
  for(i=1;i<=y;i++)    /*X自乘Y次*/
    last=last*x%1000;  /*将last乘X后对1000取模，即求积的后三位*/
  cout<<last%1000<<endl; /*打印结果*/
  system("pause");
  return 0;
}
