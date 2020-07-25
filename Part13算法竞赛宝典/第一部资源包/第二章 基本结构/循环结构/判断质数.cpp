//判断质数
# include <iostream>
# include <math.h>
using namespace std;

int main()
{
  int number,i,k;
  cin>>number;
  k=sqrt(number); //k为输入数的平方根,想一下为什么？ 
  for(i=2;i<=k;i++) //将输入数用从2至k的一个一个数进行整除
    if(number%i==0) 
      break;  //只要能被整除，即跳出for循环
  if(i>k)  
    cout<<number<<"是一个素数\n";
  else
    cout<<number<<"不是一个素数\n";
  system("pause");
  return 0;  
}
