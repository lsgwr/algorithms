//É¾³ýÈ«²¿×Ö·û´®
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s = "abcdefg";
  cout<<s.length()<<endl;
  s.clear();
  cout<<s.length()<<endl;
  s = "dkjfd";
  cout<<s.length()<<endl;
  s.erase(0,s.length());
  cout<<s.length()<<endl;
  cin.get();
}
