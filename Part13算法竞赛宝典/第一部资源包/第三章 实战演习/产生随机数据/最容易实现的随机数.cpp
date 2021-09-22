//最容易实现的随机数 
#include <iostream>
#include <stdlib.h>//C语言中常用的头文件，包含了常用的系统函数
#include <time.h>//必须使用time类的头文件
using namespace std;

int main()
{
 int i,j;
 int t=time(0)%10;//以时间作为随机函数

 cout<<"     *** 趣味摇奖机 ***  \n\n";
 cout<<"请任选一个数字(0-9):  ";

 cin>>j;

 if(j<0 || j>9)
    return 0;
   
 if(j==t)
   cout<<"\n哇,特等奖!你真厉害!";
 else if(abs(j-t)<=1)
   cout<<"\n一等奖!很不错呀!";
 else if(abs(j-t)<=2)
   cout<<"\n二等奖!也可以啦...";
 else if(abs(j-t)<=3)
   cout<<"\n三等奖!还要努力哦...";
 else
   cout<<"\n真可惜!什么都没有..."; 
 system("pause");
 return 0;   
}
