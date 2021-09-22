//复制string类的一部分
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s1("hello",2,3);
  string s2(s1); 
  cout<<s2<<endl;
  cin.get();
}
