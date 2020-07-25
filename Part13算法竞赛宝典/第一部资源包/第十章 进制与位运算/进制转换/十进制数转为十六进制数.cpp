//十进制数转为十六进制数
#include <iostream>
#include <string>
using namespace std;

int main()
{
  int number,m,i;
  string s;
  cin>>number;
  for(i=0;i<=7;i++)
  {
    m=number>>(i*4) &15;//与15进行与运算后，再右移四位 
    if(m<10) 
      s=char(m+48)+s;//处理数字，注意相加的前后顺序 
    else  
      s=char(55+m)+s;//处理字母 
   }
   while(s[0]=='0')
     s.erase(0,1);  //删除前导0 
   cout<<s;    
   system("pause");
}
