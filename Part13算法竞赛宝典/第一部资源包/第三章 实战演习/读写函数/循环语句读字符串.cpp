//循环语句读字符串
#include <iostream>
using namespace std;
main()
{
  char c;
  while((c=getchar())!='\n') //此句前面必要加括号，而且不能在freopen下使用。
    cout<<c;
  system("pause");
  return 0;  
}
