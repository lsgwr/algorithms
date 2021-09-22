//两string值互换
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s1 = "aaaaaa"; 
  string s2 = "bbbbbb";
  s1.swap(s2);      //两string值互换 
  cout<<"s1 : "<<s1<<endl;
  cout<<"s2 : "<<s2<<endl;
  cin.get();
}
