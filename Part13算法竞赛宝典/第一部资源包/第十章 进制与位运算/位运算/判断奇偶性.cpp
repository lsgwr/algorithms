//判断奇偶性
#include <iostream>
using namespace std;

int main()
{
  int x;
  cin>>x;
  if((x&1)==0)//注意位运算的优先级很低，所以必须加括号 
    cout<<x<<"是偶数"<<endl;
  else
    cout<<x<<"是奇数"<<endl;  
  system("pause");
}
