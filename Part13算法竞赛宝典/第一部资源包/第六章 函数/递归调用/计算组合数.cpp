//combination.cpp求组合问题c(m,n)的递归解决 
#include <iostream>
using namespace std;

int combin(int m,int n)
{
  int com;
  if (n<2*m)
    m=n-m;
  if (m==0) 			/*终止递归调用的条件*/
    com=1;
  else if (m==1)
    com=n;
  else
    com=combin(m,n-1)+combin(m-1,n-1);	/*递归调用*/
  return com;							/*返回值为com*/
}

int main() 
{ 
  int m,n;
  cin>>m>>n;//输入4 7 结果为35 
  cout<<combin(m,n)<<endl;	
  system("pause");
} 
