//输入两个正整数m和n,求其最大公约数和最小公倍数
# include <iostream>
using namespace std;

int main()
{
  int a,b,m,n,temp;
  cin>>m>>n;
  if(m<n) //第一个数如果比第二个数小
  {
    temp=m;  m=n;  n=temp;  //通过临时变量temp，将m,n的值互换
  }
  a=m,b=n;
  while(b!=0)
  {
    temp=a%b;
    a=b;
    b=temp;
  }
  cout<<"最大公约数为"<<a<<endl;
  cout<<"最小公倍数为"<<m*n/a<<endl;
  system("pause");
  return 0;
}
