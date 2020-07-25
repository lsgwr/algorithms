#include <iostream>
using namespace std;

int main()
{
  char *p="abcdefghijklmnopq";
  while(*p++!='e');
    cout <<p<<endl;
  getchar();     
}
