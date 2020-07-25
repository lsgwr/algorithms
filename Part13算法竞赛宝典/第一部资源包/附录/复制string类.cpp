//复制string类
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s1("hehe");  
  string s2(s1); //将s1复制到s2
  cout<<s2<<endl;  
  cin.get(); 
}
