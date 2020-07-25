//构造函数4
#include <iostream>
#include <bitset>
using namespace std;

int main()
{
  string temp="1010111101";
  bitset<10> bin(temp,4);//从第4位开始取值，包括第0位
  for(int i=0;i<=9;i++)
    cout<<bin[i];//打印出1011110000，注意，是从左向右依次填入的 
  system("pause");
}
