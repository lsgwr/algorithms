#include <iostream>
using namespace std;

int main()
{
  int a[3][3],*p,i;
  p=&a[0][0];
  for(i=0;i<9;i++)
    p[i]=i+1;
  cout<<a[1][2]<<endl;
  getchar();
}
