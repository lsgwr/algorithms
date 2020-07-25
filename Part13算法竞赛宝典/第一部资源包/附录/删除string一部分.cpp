//删除string中的一部分
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s = "abcdefg";
  s.erase(0,1);    //从索引0开始删，删一个字符,即删除掉了'a' 
  cout<<s<<endl;
  s.replace(2,3,"");//用replace方法来执行删除,即将指定范围内的字符替换成""
  cout<<s<<endl; 
  cin.get();
}
