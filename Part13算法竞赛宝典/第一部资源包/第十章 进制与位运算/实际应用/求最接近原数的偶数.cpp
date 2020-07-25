//求最接近原数的偶数
#include <iostream>
using namespace std;

int main()
{
  int x;
  cin>>x;
  if(x&1==1)
    x=(x|1)-1;//注意位运算的优先级很低，所以必须加括号 
  cout<<x<<"是最接近原数的偶数"<<endl;
  system("pause");
}
