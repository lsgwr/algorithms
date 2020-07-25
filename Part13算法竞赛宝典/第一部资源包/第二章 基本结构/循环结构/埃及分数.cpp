//埃及分数
#include<iostream>
using namespace std;

int main()
{
  long int a,b,c;
  cin>>a>>b;  //输入分子a和分母b
  while(1)
  {
    if(b%a!=0)//若分子不能整除分母
      c=b/a+1;//则分解出一个分母为b/a+1的埃及分数
    else//否则，输出化简后的真分数(埃及分数)
    { 
      c=b/a; 
      a=1;
    }   

    if(a==1)//若分子已经为1了 
    {
      cout<<"1/"<<c;
      break;  //则结束
    }
    else
      cout<<"1/"<<c<<"+";

    a=a*c-b;  //求出余数的分子
    b=b*c;    //求出余数的分母

    if(a==3) //若余数为3，输出最后两个埃及分数如3/14=1/7+1/14
    {   
       cout<<"1/"<<b/2<<"+"<<"1/"<<b;
       break;
    }
  } 
  system("pause");
  return 0;
}   
