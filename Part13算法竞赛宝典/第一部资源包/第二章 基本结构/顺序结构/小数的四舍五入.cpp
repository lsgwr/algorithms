//小数的四舍五入 
#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
  double x;
  cout<<"请输入一个双精度数";
  cin>>x;
  x=(int)(x*100+0.5);
  x/=100;
  cout<<setprecision(2)<<fixed<<x<<endl;
  system("pause");
  return 0;
}
