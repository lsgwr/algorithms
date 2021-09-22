//freopen方式读写文件 
#include <iostream>
#include <cstdlib>//此行必加，否则linux下可能出问题 
using namespace std;
int main()
{
  int a,b;  
  freopen("sum.in","r",stdin); //从sum.in中读取数据
  freopen("sum.out","w",stdout);//输出到sum.out文件
  cin>>a>>b;
  cout<<a+b<<endl;
  //system("pause"); 一定要将此句注释掉 
  return 0;
}
