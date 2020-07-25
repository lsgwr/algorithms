//求两数中的最大数
#include <iostream>
using namespace std;

int Max(int a,int b) //Max为一个子函数
{
  if(a>b)
    return a; //返回值为a
  else 
    return b; //返回值为b
}

int main()
{
  int x,y;
  cin>>x>>y;
  cout<<Max(x,y)<<endl;//此处调用了max这个函数，并将x,y值传递给了Max函数。
  system("pause");
} 
