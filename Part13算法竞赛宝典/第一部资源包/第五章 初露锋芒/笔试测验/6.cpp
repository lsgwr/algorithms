#include <iostream>
using namespace std;

int main()
{
  int i=0,s=0;
  do
  {
     if(i%2)
     {
       i++;
       continue;
     }
     i++;
     s+=i;
  }while(i<7);
  cout<<s<<endl;
}
