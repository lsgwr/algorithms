//±È½Ï×Ö·û´®
#include <string> 
#include <iostream>
using namespace std;

int main()  
{ 
  string s1 = "abcdefg";
  string s2 = "abcdefg"; 
  if (s1==s2)
cout<<"s1 == s2"<<endl;
  else 
cout<<"s1 != s2"<<endl;

  if (s1!=s2)
cout<<"s1 != s2"<<endl; 
  else 
cout<<"s1 == s2"<<endl; 

  if (s1>s2)
cout<<"s1 > s2"<<endl;
  else 
cout<<"s1 <= s2"<<endl; 

  if (s1<=s2)
cout<<"s1 <= s2"<<endl;
  else 
cout<<"s1 > s2"<<endl;
  cin.get();
}
