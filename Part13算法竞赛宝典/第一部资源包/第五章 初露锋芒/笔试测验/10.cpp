#include <iostream>
using namespace std;

int main()
{
  int i=20,n=0;
  do
  {
    n++;
    switch(i%4)
    {
      case 0:i=i-7;
        break;
      case 1:
      case 2:
      case 3:i++;
        break;         
    }
  }while(i>=0);
  cout<<"n="<<n<<endl;
}
