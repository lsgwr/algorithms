//递归求年龄
# include <iostream>
using namespace std;

int age(int n)
{
  int c;
  if(n==1)  //递归结束的条件
    return 10;
  else 
     c=age(n-1)+2; //调用自身
  return c;
}

int main()
{
  cout<<age(5)<<' ';
  system("pause");
  return 0; 
}
