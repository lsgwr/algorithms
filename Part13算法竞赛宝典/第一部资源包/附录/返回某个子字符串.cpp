//返回某个子字符串
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s = "abcdefg1111"; 
  string str = s.substr(5,3);//从索引5开始3个字节 
  cout<<str<<endl; //输出 fg1 
  cin.get();
}
