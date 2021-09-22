//在尾部添加字符串
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s = "abc";
  s += "def";  //串尾添加字符串def 
  cout<<s<<endl;
  s.append("1234");    //append()方法可以在末尾添加字符串 
  cout<<s<<endl;
  s.push_back('z');    //push_back()方法只能在末尾添加一个字符
  cout<<s<<endl;
  cin.get();
}
