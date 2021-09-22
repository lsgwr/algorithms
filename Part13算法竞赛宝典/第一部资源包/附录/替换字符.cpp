//替换字符
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s = "abcdefg";
  s.replace(2,3,"123456789");//从索引2开始3个字节的字符替换成"123456789"  
  cout<<s<<endl;  //输出为ab123456789fg
  cin.get();
}
