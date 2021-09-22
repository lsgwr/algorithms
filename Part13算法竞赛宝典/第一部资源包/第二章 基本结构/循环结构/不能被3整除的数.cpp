//把100-200之间不能被3整除的数输出。
#include <iostream>
using namespace std;
int main()
{
  int n;
  for(n=100;n<=200;n++)
  {
    if(n%3==0)
      continue; //跳出当前循环体，执行循环体下面的语句
    cout<<n<<' ';
  }
  system("pause");
  return 0;
}
