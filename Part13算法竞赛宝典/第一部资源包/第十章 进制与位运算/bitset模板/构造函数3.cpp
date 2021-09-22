//构造函数3
#include <iostream>
#include <bitset>
using namespace std;

int main()
{
  string temp="1010111100";
  bitset<10> bin(temp);
  for(int i=0;i<=9;i++)
    cout<<bin[i];//打印出0011110101，注意，是从左向右依次填入的 
  system("pause");
}
