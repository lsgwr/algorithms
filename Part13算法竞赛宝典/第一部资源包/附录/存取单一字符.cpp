//´æÈ¡µ¥Ò»×Ö·û
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s = "abcdefg1111";  
  cout<<"use []:"<<endl;
  for(int i=0; i<s.length(); i++) 
  {
      cout<<s<<endl; 
  } 
  cout<<endl;

  cout<<"use at():"<<endl; 
  for(int i=0; i<s.length(); i++)
  { 
    cout<<s.at(i)<<endl; 
  } 
  cout<<endl;
  cin.get();
}
