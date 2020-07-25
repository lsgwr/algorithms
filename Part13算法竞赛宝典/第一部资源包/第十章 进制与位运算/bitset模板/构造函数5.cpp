//构造函数5
#include <iostream>
#include <bitset>
using namespace std;

int main()
{
  string temp="1010111101";
  bitset<10> bin(temp,4,5);//从第4个元素开始往后取5个元素，其余填作0 
  for(int i=0;i<=9;i++)
    cout<<bin[i];//打印出0111100000，注意，是从左向右依次填入的 
  system("pause");
}
