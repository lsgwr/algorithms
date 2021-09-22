//防护罩
#include <iostream>
#include <math.h> //须加此头文件 
using namespace std;

int main()
{
  int N=0,i,temp;
  double r,l;
  cin>>r;
  l=floor(r);//获得组数 
  for(i=1;i<=l;i++)
  {
    temp=sqrt(r*r-i*i);
    N=N+temp;                
  }
  cout<<N*4;
  system("pause");
  return 0;
}
