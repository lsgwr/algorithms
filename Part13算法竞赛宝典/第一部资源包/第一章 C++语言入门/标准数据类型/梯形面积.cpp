//梯形面积 
#include <iostream>
using namespace std;

int main()
{
  float area,h,up=15,down=30;
  h=2*160/up;//求高
  area=(up+down)*h/2;
  cout<<area<<endl; 
  system("pause");
  return 0;   
}
