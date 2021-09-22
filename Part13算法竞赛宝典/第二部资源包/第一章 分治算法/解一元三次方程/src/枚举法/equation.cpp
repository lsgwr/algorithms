//一元三次方程简单枚举法 
#include <cstdio> 
#include <cstdlib>
#include <iostream> 
using namespace std; 

int main() 
{   
  double a,b,c,d,x,fx;     
  int i;     
  freopen("equation.in","r",stdin);     
  freopen("equation.out","w",stdout);     
  cin>>a>>b>>c>>d; 
  cout.precision(2);//设精度，即小数点位数 
  cout.setf(ios::fixed);// 以定点形式显示浮点数  
  for (i=-10000;i<=10000;x=(++i)/100.0)     
  {         
    fx=a*x*x*x+b*x*x+c*x+d;         
    if (fx>=-0.01 && fx<=0.01)               
       cout<<x<<' ';   
  } 
  cout<<'\n';     
  return 0; 
}
