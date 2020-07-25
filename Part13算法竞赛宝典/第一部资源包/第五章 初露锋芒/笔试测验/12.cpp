#include <iostream>
using namespace std;

int main()
{
  char s1[]="this book",s2[]="this hook";
  int i;
  for(i=0;s1[1]!='\0' && s2[i]!='\0';i++)
    if(s1[i]==s2[i]) 
      cout<<s1[i];
  cout<<endl;
  getchar();  
}
