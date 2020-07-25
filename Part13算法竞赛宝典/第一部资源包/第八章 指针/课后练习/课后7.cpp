#include <iostream>
using namespace std;

int main()
{
  int a[9]={1,2,3,4,5,6,7,8,9},*p;
  p=a;
  cout<<*p<<","<<*(p+5)<<endl;
  getchar();     
}
