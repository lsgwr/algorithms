//十进制转N进制 
#include<iostream>
#define MAXN 1000//栈能容纳的最多元素个数
using namespace std;
char c[36]={'0','1','2','3','4','5','6','7','8','9','A','B',
            'C','D','E','F','G','H','I','J','K','L','M','N',
            'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
int stack[MAXN];
int top = -1;//初始化栈顶指针为-1 

int StackEmpty()
{
  if(top<0)
    return 1;
  else
    return 0;  
}

char pop()//栈顶元素出栈并获取出栈的元素值 
{
  int temp=stack[top--];
  return c[temp];
}

void push(int value)
{ 
  stack[++top]=value;
}

void conversion(int N,int d)
{
  while(N)
  {
    push(N%d);
    N=N/d;
  }
  while(!StackEmpty())
    cout<<pop();
}

int main()
{
  int number,d;
  cin>>number>>d;
  conversion(number,d);
  cout<<endl;
  system("pause");
  return 0;
}
