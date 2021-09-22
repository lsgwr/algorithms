//求一元二次方程的根
#include <iostream>
#include <math.h>
using namespace std;

int main()
{
  float a,b,c,disc,p,q;
  cin>>a>>b>>c;
  disc=b*b-4*a*c;
  p=-b/(2*a);
  q=sqrt(disc)/(2*a);
  cout<<"x1="<<p+q<<endl;
  cout<<"x2="<<p-q<<endl;
  system("pause");
  return 0; 
}
