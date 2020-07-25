//求三角形面积
#include <iostream>
#include <math.h>//调用平方根函数需要数学库头文件
using namespace std;

int main()
{
  float a,b,c,p,area;
  cin>>a>>b>>c;
  p=1/2*(a+b+c);
  area=sqrt(p*(p-a)*(p-b)*(p-c));//sqrt为求平方根函数
  cout<<"a="<<a<<endl;
  cout<<"b="<<b<<endl;
  cout<<"c="<<c<<endl;
  cout<<"p="<<p<<endl;
  cout<<"area="<<area<<endl;
  system("pause"); 
}
