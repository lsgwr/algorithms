//逆序字符
#include <iostream>
using namespace std;

void reverse(int n)
{
  char next;
  if(n<=1)     //递归的结束条件
  {
    next=getchar();//从键盘获得一个字符
    printf("\n");
    putchar(next);//因为只剩最后一个字符了，所以这里直接打印即可
  }
  else
  {
    next=getchar();//从键盘获得一个字符
    reverse(n-1);  //递归调用输入的下一个字符
    putchar(next);//只有等到n-1个字符都被打印后，该字符才被打印
  }
}

int main()
{
  int i=5;
  reverse(i);
  system("pause");
}
