//cin.getline读字符串
#include <iostream>
using namespace std;
main()
{
  char a[80]; //注意与上一个程序数据类型的区别。
  cin.getline(a,80);
  cout<<a;
  system("pause");
  return 0;
}
