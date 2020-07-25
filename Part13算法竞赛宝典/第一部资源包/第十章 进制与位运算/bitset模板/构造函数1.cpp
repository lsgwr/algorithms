//构造函数1
#include <iostream>
#include <bitset>//必须引入该头文件 
using namespace std;//全局位数组512M内存环境下数组大小可开到5000万以上

int main()
{
  bitset<5000000> bin;//bin为位数组，初始为0，一般512M内存上限约为500万
  bin[0]=1;//设为1 
  bin[1]=true; 
  bin[2]=0;//设为0 
  bin[3]=false;
  cout<<bin[0]<<bin[1]<<bin[2]<<bin[3]<<bin[4];//打印出11000 
  system("pause");
}
