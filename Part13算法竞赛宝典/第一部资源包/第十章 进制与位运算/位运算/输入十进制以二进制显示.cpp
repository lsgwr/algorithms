//输入十进制以二进制显示 
#include <iostream>
using namespace std;

int main()
{
  int m,number,s[32];  
  cin>>number;
  for(int i=1;i<=32;i++)  //32位的编译器 
    s[i-1]=number>>(i-1)&1;//将每一位移到最右端与1进行与运算 
  for(int i=31;i>=0;i--)
    cout<<s[i]<<' ';
  system("pause");
}
