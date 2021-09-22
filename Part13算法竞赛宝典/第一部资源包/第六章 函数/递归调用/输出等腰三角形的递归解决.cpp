//输出等腰三角形的递归解决 
#include <iostream>
using namespace std;

void trangle(char c,int n)
{  
  if(n>0)
  {
    cout<<c;
    trangle(c,n-1);
  }   
}

int main()
{
  int n,i;
  cin>>n;
  for(i=1;i<=n;++i)
  {
    trangle(' ',n-i);//输出空格 
    trangle('*',2*i-1);//输出星号 
    cout<<endl;
  }
  system("pause");
}
