//局部静态变量演示
# include <iostream>
using namespace std;

int fun()
{
  static int n=1;
  n++;
  return n;
}

int main()
{
  int i;
  for(i=0;i<3;i++)
    cout<<fun()<<' ';
  system("pause");
  return 0;  
}
