//求N的阶乘的递归转非递归算法，使用数组模拟系统堆栈
#include <iostream>
using namespace std;
#define Num 50  //Ｎ不可超过20 
#define MaxSize Num
unsigned long long int stack[Num+1],top;//top是数组指针

unsigned long long int push(int value)//入栈操作 
{ 
  if(top<MaxSize)//当栈未满时 
  { 
    top++; //压入一个元素，则栈顶指示器加一 
    stack[top]=value;//获得栈顶元素 
  }
}

unsigned long long int pop()//出栈操作 
{ 
  unsigned long long int temp;//使用临时变量暂存栈顶元素 
  if(top>=1)//如果栈未空 
  {
    temp=stack[top];
    stack[top]=0;//弹出一个元素，即设值为０ 
    top--;//弹出一个元素，则栈顶指示器减一
  }
  return temp;//返回出栈的元素值 
}

unsigned long long int Factorial(int n)
{
   unsigned long long int t;//临时变量
   while(n>=1)//依次压进栈 
   {
     push(n);
     n--;
   }
   t=1;
   while(top>=1)//依次弹出栈并乘t 
     t=t*pop();
   return t;
} 

int main()
{
  FILE *in=fopen("factorial.in","r");
  FILE *out=fopen("factorial.out","w");
  int N;
  fscanf(in,"%d",&N);  
  top=0;//初始时，栈顶指示器top为０ 
  fprintf(out,"%d!=%I64u",N,Factorial(N)); 
  fprintf(out,"\n");
  fclose(in);
  fclose(out);
  return 0;  
}
