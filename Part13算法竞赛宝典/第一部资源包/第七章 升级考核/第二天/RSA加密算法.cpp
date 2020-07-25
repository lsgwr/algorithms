//RSA加密算法
#include <iostream>
using namespace std;

int main()
{
 int m,i=2;   
 cin>>m; 
 cout<<m<<"=";   
 while (m!=1)
 {
   while (m%i==0) //从2开始作除数，若能整除，输出因子 
   {              //并继续试能否获得同一因子 
     m=m/i;  
     if(m==1)
      cout<<i;
     else
      cout<<i<<'*'; //思考为什么得到的都是质因子？
   }   
   i++;             //如不能整除，则将除数加1再试 
 }
 system("pause");
 return 0;
}
