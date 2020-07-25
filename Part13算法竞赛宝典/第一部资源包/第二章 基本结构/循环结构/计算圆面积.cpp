//计算r=1到r=10时的圆面积，直到面积area大于100为止
# include <iostream>
using namespace std;
int main()
{	
  float area;int r;
  for(r=1;r<=10;r++)
  {
    area=3.14*r*r;
    if(area>100)
      break; //跳出当前循环体，执行循环体下面的语句
    cout<<r<<":area="<<area<<endl;
  }
  system("pause");
  return 0;
}
