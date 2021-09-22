//输入二进制以十进制显示 
#include <iostream>
using namespace std;

int show10(char *c)
{
  int num=0;   
  for(int i=0;i<=31;i++) 
    num=(num<<1)+c[i]; 
  return num;      
}

int main()
{
   int x;
   char c[32];
   for(int i=0;i<=31;i++)//以字符形式输入32位的二进制数
     cin>>c[i]; 
   for(int i=0;i<=31;i++)//字符转换为数字
     c[i]=c[i]-'0';    
   cout<<show10(c)<<endl;
   system("pause");
   return 0;
}
