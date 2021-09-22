#include <iostream>
using namespace std;

int f(int b[][4])
{
  int i,j,s=0;
  for(j=0;j<4;j++)
  {
    i=j;
    if(i>2)i=3-j;
    s+=b[i][j];                
  }    
  return s;
}

int main()
{
  int a[4][4]={{1,2,3,4},{0,2,4,5},{3,6,9,12},{3,2,1,0}};
  cout<<f(a)<<endl;
  getchar();  
}
