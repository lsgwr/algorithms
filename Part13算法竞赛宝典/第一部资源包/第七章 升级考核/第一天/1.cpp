#include <iostream>
using namespace std;

int f(int b[],int m,int n)
{
  int i,s=0;
  for(i=m;i<n;i=i+2)
    s=s+b[i];
  return s;      
}

int main()
{
  int x,a[]={1,2,3,4,5,6,7,8,9};
  x=f(a,3,7);
  cout<<x<<endl;
  getchar();  
}
