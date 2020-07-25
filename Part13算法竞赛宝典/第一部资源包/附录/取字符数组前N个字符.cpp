//取字符数组的前N个字符
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  char chs[] = "hello";
  string s(chs,3);    //将chs前3个字符作为初值构造
  cout<<s<<endl;
  cin.get();
}
