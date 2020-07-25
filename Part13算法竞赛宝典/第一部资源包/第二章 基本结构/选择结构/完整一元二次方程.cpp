//解一元二次方程
#include <iostream>
#include <math.h>
using namespace std;

int main()
{
  float a,b,c,disc,x1,x2,realpart,imagpart;
  cin>>a>>b>>c;
  if(fabs(a)<=1e-6)
    cout<<"不是一个合法的一元二次方程"<<endl;
  else
  {
    disc=b*b-4*a*c;
    if(fabs(disc)<=1e-6)
      cout<<"有两个相等的实根："<<-b/(2*a)<<endl;
    else if(disc>1e-6)
    {
      x1=(-b+sqrt(disc))/(2*a);
      x2=(-b-sqrt(disc))/(2*a);
      cout<<"有两个不同的实根："<<x1<<' '<<x2;
    }
    else
    {
      realpart=-b/(2*a);
      imagpart=sqrt(-disc)/(2*a);
      cout<<"两个共轭复根:"<<realpart<<'+'<<imagpart<<"i, "<<realpart<<'-'<<imagpart<<"i\n";
    }
  }
  system("pause");
  return 0;
} 
