#include <iostream>
using namespace std;

int strlen1(char a[])
{
  char *p=a;
  int i=0;
  while(a[i++]!='\0')
    p++;
  return p-a;   
}

int main()
{
  char s[]="abcd";
  char *p;
  int n=strlen1(s);
  for(p=s+n-1;p+1!=s;p--)
    cout<<*p;
  cout<<endl;      
  getchar();     
}
