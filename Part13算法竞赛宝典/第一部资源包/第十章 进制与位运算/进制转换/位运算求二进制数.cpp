//位运算求二进制数
#include <iostream>
using namespace std;

int main()
{
  int number,i,j,m,ok=0;
  int s[50];
  memset(s,0,sizeof(s));//将数组所有元素初始化为0
  cin>>number;
  for(i=1;i<=32;i++)  //32位的编译器 
  {
    m=number>>(i-1)&1;//将每一位移到最右端与1进行与运算 
    s[i]=m;
  }
  for(i=49;i>=1;i--)//倒序输出 
  {
    if(ok==0 && s[i]==1)//忽略前面的0 
      ok=1;
    if(ok==1)  
      cout<<s[i]; 
  }     
  system("pause");     
}
