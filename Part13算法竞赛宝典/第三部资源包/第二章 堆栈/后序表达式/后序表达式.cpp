//求表达式值
#include <iostream>
using namespace std;
#define MAX 100
char exp[MAX];   /*存储转换成的后缀表达式*/

void trans()     /*将算术表达式转换成后缀表达式*/
{
  char str[MAX];   /*存储原算术表达式*/
  char stack[MAX]; /*作为栈使用*/
  char ch;
  int sum,i,j,t,top=0;
  /*t作为exp的下标,top作为stack的下标,i作为str的下标*/
  printf("***************************************************************\n");
  printf("* 输入一个求值的表达式,以#结束。只能包含+,-,*,/运算符和正整数 *\n");
  printf("***************************************************************\n");
  printf("算术表达式:");
  i=0;   /*获取用户输入的表达式*/
  do
  {
    i++;
    scanf("%c",&str[i]);
  }
  while (str[i]!='#' && i!=MAX);
  sum=i; /*记录输入表达式总的字符个数*/
  t=1;
  i=1;
  ch=str[i];
  i++;
  while (ch!='#')
  {
    switch(ch)
    {
      case '(':  /*判定为左括号*/
        top++;
        stack[top]=ch;
        break;
      case ')':  /*判定为右括号*/
        while (stack[top]!='(')
        {
          exp[t]=stack[top];
          top--;
          t++;
        }
        top--;
        break;
      case '+':   /*判定为加减号*/
      case '-':
        while (top!=0 && stack[top]!='(')
        {
          exp[t]=stack[top];
          top--;
          t++;
        }
        top++;
        stack[top]=ch;
        break;
      case '*':  /*判定为'*'或'/'号*/
      case '/':
        while (stack[top]=='*' || stack[top]=='/')
        {
          exp[t]=stack[top];
          top--;
          t++;
        }
        top++;
        stack[top]=ch;
        break;
      case ' ':
        break;
      default:
        while (ch>='0' && ch<='9') /*判定为数字*/
        {
          exp[t]=ch;
          t++;
          ch=str[i];
          i++;
        }
        i--;
        exp[t]='#';
        t++;
    }
    ch=str[i];
    i++;
  }
  while (top!=0)
  {
    exp[t]=stack[top];
    t++;
    top--;
  }
  exp[t]='#';
  printf("\n\t原来表达式:");
  for (j=1; j<sum; j++) printf("%c",str[j]);
  printf("\n\t后缀表达式:",exp);
  for (j=1; j<t; j++)
    printf("%c",exp[j]);
}

void compvalue()  /*计算后缀表达式的值*/
{
  float stack[MAX],d; /*作为栈使用*/
  char ch;
  int t=1,top=0;  /*t作为exp的下标,top作为stack的下标*/
  ch=exp[t];
  t++;
  while (ch!='#')
  {
    switch (ch)
    {
      case '+':
        stack[top-1]=stack[top-1]+stack[top];
        top--;
        break;
      case '-':
        stack[top-1]=stack[top-1]-stack[top];
        top--;
        break;
      case '*':
        stack[top-1]=stack[top-1]*stack[top];
        top--;
        break;
      case '/':
        if (stack[top]!=0)
          stack[top-1]=stack[top-1]/stack[top];
        else
        {
          printf("\n\t除零错误!\n");
          exit(0);/*异常退出*/
        }
        top--;
        break;
      default:
        d=0;
        while (ch>='0' && ch<='9')   /*判定为数字字符*/
        {
          d=10*d+ch-'0';  /*将数字字符转换成对应的数值*/
          ch=exp[t];
          t++;
        }
        top++;
        stack[top]=d;
    }
    ch=exp[t];
    t++;
  }
  printf("\n\t计算结果:%g\n",stack[top]);
}

int main()
{
  trans();
  compvalue();
  system("pause");
}
