//判断字符串是否为空
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s ; 
  if (s.empty())
    cout<<"s 为空."<<endl;  
  else 
    cout<<"s 不为空."<<endl;

  s = s + "abcdefg";
  if (s.empty())
    cout<<"s 为空."<<endl;
  else   
    cout<<"s 不为空."<<endl;
  cin.get();
}
