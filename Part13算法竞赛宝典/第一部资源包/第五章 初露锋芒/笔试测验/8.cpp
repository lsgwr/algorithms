#include <iostream>
using namespace std;

int main()
{
  int m=9;
  do
  {
    cout<<(m-=2)<<" ";
  }while(--m);  
  cout<<endl;
}
