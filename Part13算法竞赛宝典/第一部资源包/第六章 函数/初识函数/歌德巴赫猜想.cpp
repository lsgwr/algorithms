//歌德巴赫猜想
#include<iostream>
#include<math.h>
using namespace std;

int fflag(int i)           /*判断是否为素数*/
{
  int j;
  if(i<=1)
    return 0;
  if(i==2)
    return 1;
  if(!(i%2))//如果是偶数 
    return 0;     
  for(j=3;j<=(int)(sqrt((double)i)+1);j+=2)
    if(!(i%j))
      return 0;
  return 1;      /*如果是素数,return 1*/
}

int main()
{
  int i,n;
  for(i=4;i<=2000;i+=2)
  {
    for(n=2;n<i;n++)   /*将偶数i分解为两个整数*/
      if(fflag(n))     /*分别判断两个整数是否均为素数*/
        if(fflag(i-n))
        {
          cout<<i<<"="<<n<<"+"<<i-n<<endl;/*若均是素数则输出*/
          break;
        }
      if(n==i)  
        cout<<"error %d\n";
  }
  system("pause");
  return 0;
}
