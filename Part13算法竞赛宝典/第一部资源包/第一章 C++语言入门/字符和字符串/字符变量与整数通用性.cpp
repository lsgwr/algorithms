//字符变量与整数的通用性演示
# include <iostream>
using namespace std;
int main()
{
  char c1,c2;
  c1=65;//注意此处是数字 
  c2=66;//注意此处是数字 
  cout<<c1<<"  "<<c2<<endl;//因为c1,c2为字符类型，所以输出也是字符 
  cout<<int(c1)<<"  "<<int(c2)<<endl;//此处强制转化为数字输出 
  system("pause"); 
  return 0;
}
