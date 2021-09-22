//查找子串
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s = "abcdefg1111"; 
  string pattern = "fg";
  int pos = s.find(pattern,0);      //从索引0开始,查找符合字符串"f"的头索引 
  cout<<pos<<endl; //输出5 
  string str = s.substr(pos,pattern.size());  //子串 
  cout<<str<<endl;//输出fg 
  cin.get();
}
