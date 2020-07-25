//标识符定义常量
#include <iostream>
#define PRICE 34  //定义了一个符号常量
using namespace std;

int main()
{
  int number=10;
  int total;          
  total=number*PRICE;                 
  cout<<"total="<<total<<endl; 
  system("pause");
  return 0;
}
