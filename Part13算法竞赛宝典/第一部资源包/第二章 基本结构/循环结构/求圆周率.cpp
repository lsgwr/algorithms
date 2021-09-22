//求圆周率
# include <iostream>
# include <math.h>
using namespace std;

int main()
{
  int s=1;
  float n=1.0,t=1,pi=0;
  while(fabs(t)>1e-6)  //1e-6是C语言中的指数表达形式
  {
    pi=pi+t;
    n=n+2; //分母加2
    s=-s;  //改变加减号
    t=s/n;  
  }
  pi=pi*4;
  cout<<pi<<endl;
  system("pause");
  return 0;
  
}
