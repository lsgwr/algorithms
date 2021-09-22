/*
bitset模板其他函数的用法总汇 
*/ 
#include <iostream>
#include <bitset>
using namespace std;
string temp="1010111101";
bitset<10> bin(temp);

void out()
{
  for(int i=0;i<=9;i++)
    cout<<bin[i];
  cout<<endl;  
}

int main()
{
  out();
  cout<<endl<<bin.any()<<endl;//是否存在置为1的位数
  cout<<bin.none()<<endl;//是否全部为0，与any()相反
  cout<<bin.count()<<endl;//二进制为1的个数
  cout<<bin.size()<<endl;//总长度
  bin.flip();//二进制位依次取反 
  out(); 
  bin.flip(0);//第零位取反 
  out();
  cout<<bin.operator[](2)<<endl;//获取第2位的值
  bin.set();//将所有位全置为1 
  out();   
  bin.reset();//将所有位全置为0 
  out();
  bin.set(0);//将第0位置为1
  out();
  bin.reset(0);//将第0位置为0 
  out();
  cout<<bin.test(0)<<endl;//判断第0位是否为1
  bin.set(0);
  int x=bin.to_ulong();//转为整数1。注意从右向左取值及长度勿超过long型范围 
  cout<<x<<endl;
  system("pause");
}
