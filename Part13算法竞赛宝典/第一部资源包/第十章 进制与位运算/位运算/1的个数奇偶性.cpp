//最朴素程序
#include <iostream>
using namespace std;

int main()
{
  int i,x,c=0;
  cin>>x;
  for (i=1;i<=32;i++)
  {
      c=c + (x & 1);//判断最末位是0还是1 
      x=x >> 1;//去掉最右边一位 
  }
  cout<<( c & 1 );//判断奇偶
  system("pause");
  return 0;
}
