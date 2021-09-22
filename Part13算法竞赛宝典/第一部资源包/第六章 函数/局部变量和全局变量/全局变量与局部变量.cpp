//全局变量与局部变量
# include <iostream>
using namespace std;

int num1=5;//全局变量，所有函数都可使用它

int fun1()
{
  int a=0;//局部变量,只能在本函数中使用  
  num1++;
}

int num2=4;//全局变量,可以被下面的fun2函数和main函数使用 
int fun2()
{
   num2++; 
}

int main()
{
  int sum=5;//局部变量，只在main函数中有效
  fun1();
  fun2();
  sum+=num1+num2;
  cout<<sum<<endl;
  system("pause");
  return 0;
}
