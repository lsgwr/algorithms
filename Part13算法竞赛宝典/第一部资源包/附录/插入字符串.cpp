//插入字符串
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s = "abc";
  s.insert(0,"头部");            //在头部插入
  s.insert(s.size(),"尾部");    //在尾部插入
  s.insert(s.size()/2,"中间");//在中间插入
  cout<<s<<endl;
  cin.get();
}
