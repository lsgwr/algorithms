//构造函数2
#include <iostream>
#include <bitset>//
using namespace std;

int main()
{
  bitset<5> bin(5);//从左向右赋值 
  for(int i=0;i<=4;i++)
    cout<<bin[i];//打印出10100 
  system("pause");
}
