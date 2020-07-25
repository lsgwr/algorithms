//求素数
# include <iostream>
#include <math.h>
using namespace std;

int main()
{
  int i,j,a[10000+1];
  for(i=2;i<10001;i++) //赋初值
     a[i]=i;
  for(i=2;i<sqrt(10001);i++)   
    for(j=i+1;j<10001;j++)
    {
      if(a[i]!=0 && a[j]!=0)
         if(a[j]%a[i]==0) //如果能被整除，则该数不为素数
            a[j]=0;
     }   
  for(i=2;i<10001;i++)  //将素数打印出来
    if(a[i]!=0)  
      cout<<a[i]<<' ';
  getchar();
  return 0;
}
