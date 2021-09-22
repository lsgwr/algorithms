//递归数字转换为字符 
#include <iostream>
using namespace std;

void convert(int n)
{
   int i;
   if((i=n/10)!=0) /*限制条件*/
       convert(i); /*483第一次分解为48,第二次为4*/
   putchar(n%10+'0'); /*'0'为48,48+n的数,等于n的ASCII码值*/
}

int main()
{  
   int number;  /*输入的数字定义,如483*/
   cin>>number;
   if(number<0)
   {
      putchar('-');  /*当为负数时的处理*/
      number=-number;
   }
   convert(number);
   system("pause");
}   
