#include <iostream>
using namespace std;

int main()
{
  int i=0,s=0;
  for(;;)
  {
    i+=2;
    if(i>6)
    {
      cout<<"s="<<s<<endl;
      break;
    }
    if(i==6) continue;
    s+=i;
  }
}
