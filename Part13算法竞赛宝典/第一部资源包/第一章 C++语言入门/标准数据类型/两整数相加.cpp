/*
  这是一个加法程序 
*/
#include <iostream>
using namespace std;

int main()
{
   int a,b,c;      //定义变量 a,b,c 
   cout <<"请输入a,b的值:";  //显示提示信息 
   cin >> a >>b;   //从键盘输入a和b的值 
   c=a+b;          //计算a和b的和，并把结果放在c中 
   cout <<a<<"+"<<b<<"="<<c<<endl;//显示结果 
   system("pause");   
   return 0;
}
