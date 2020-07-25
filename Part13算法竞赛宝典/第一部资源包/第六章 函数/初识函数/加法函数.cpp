//加法函数
#include <iostream>
using namespace std;

float add(float x,float y) //返回类型为float,有两个形参float x,float y
{
  float z; //注意，定义了z
  z=x+y;
  return z; //返回值z 为float型，与返回值类型匹配
}

int main()
{
  float x,y,z;  //注意：定义了x,y,z
  cin>>x>>y;
  z=add(x,y);//调用add函数后返回一个float类型的值,并将该值赋给z
  cout<<z<<endl;
  system("pause");
}
