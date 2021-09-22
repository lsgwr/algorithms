#include <iostream>
using namespace std;

void sub(int s[])
{
  static int t=0;
  s[t]++;
  t++;    
}

int main()
{
  int a[]={1,2,3,4},i;
  for(i=0;i<4;i++)
    sub(a);
  for(i=0;i<4;i++)
    cout<<a[i]<<" ";  
  cout<<endl;
  getchar();  
}
