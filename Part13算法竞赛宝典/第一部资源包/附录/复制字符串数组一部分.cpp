//复制字符数组的一部分
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  char chs[] = "hello"; 
  string s(chs,1,3);    //指定从chs的索引1开始,最后复制3个字节 
  cout<<s<<endl;  
  cin.get();  
}
