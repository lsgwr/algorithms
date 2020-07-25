//格式练习 
#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
  cout<<setw(8)<<left<<"张三";
  cout<<setw(10)<<setfill('-')<<"345.5"<<"   ";
  cout<<right<<setfill('*')<<setw(10)<<"34345.4"<<endl;
  cout<<setw(8)<<setfill(' ')<<left<<"李四";
  cout<<setw(10)<<setfill('-')<<"342.2"<<"   ";
  cout<<right<<setfill('*')<<setw(10)<<"123.5"<<endl;
  cout<<setw(8)<<left<<setfill(' ')<<"刘强";
  cout<<setw(10)<<setfill('-')<<"556.6"<<"   ";
  cout<<right<<setfill('*')<<setw(10)<<"343434.55"<<endl;
  system("pause");
  return 0;
}
