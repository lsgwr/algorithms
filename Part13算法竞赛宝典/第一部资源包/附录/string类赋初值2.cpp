// string类赋初值
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s(10,'k');    //分配10个字符,初值都是'k'  
  cout<<s<<endl; 
  s = "hehehehe";  //重新赋值,s值为hehehehe 
  cout<<s<<endl; 
  s.assign("kdje");//赋新值,s值为kdje 
  cout<<s<<endl;
  s.assign("fkdhfkdfd",5);//重新分配指定字符串的前5的元素内容,即s值为fkdhf 
  cout<<s<<endl; 
  cin.get();
}
