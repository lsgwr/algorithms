//1.1 你好，C++ 
//sizeof的用法 
#include <iostream>
using namespace std;

int main()  
{ 
  cout<<"int的字节长度为"<<sizeof(int)<<endl;
  cout<<"short的字节长度为"<<sizeof(short)<<endl;
  cout<<"long的字节长度为"<<sizeof(long)<<endl;
  cout<<"long long的字节长度为"<<sizeof(long long)<<endl;
  cout<<"bool的字节长度为"<<sizeof(bool)<<endl;
  cout<<"char的字节长度为"<<sizeof(char)<<endl;
  cout<<"float的字节长度为"<<sizeof(float)<<endl;
  cout<<"double的字节长度为"<<sizeof(double)<<endl;
  cout<<"long double的字节长度为"<<sizeof(long double)<<endl;
  system("pause");
  return 0;
}
