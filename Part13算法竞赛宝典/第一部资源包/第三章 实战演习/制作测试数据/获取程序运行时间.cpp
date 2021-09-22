/*
程序名称：获取程序运行时间 
程序说明：应用函数clock() 
*/ 
#include <iostream>
#include <windows.h>
using namespace std;

int main()
{
  clock_t time;//如果仅仅判断该程序运行全部时间，则无需设time， 
  time=clock();//在末行直接输出clock()的值即可 
  cout<<time<<endl;
  Sleep(1000);//休眠1秒，Sleep首字母应大写 
  cout<<clock()-time<<"毫秒"<<endl;
  system("pause");
  return 0;      
}
