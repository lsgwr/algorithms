//免子永生
#include <iostream>
using namespace std;

int main()
{
  int f1=1,f2=1,i;
  for(i=1;i<=20;i++)
  {
    cout<<f1<<' '<<f2; 
    if(i%2==0)  //当一行输出两次f1和f2（即4个数字）的值后另起一行
      cout<<'\n';
    f1=f1+f2;
    f2=f2+f1;
  }
  system("pause");
  return 0;
}
