//求水仙花数
#include <iostream>
using namespace std;

int main()
{
  int i,j,k,n;
  for(n=100;n<1000;n++)
  {
     i=n/100;       //取百位数
     j=n/10-i*10;    //取十位数
     k=n%10;        //取个位数
     if(i*100+j*10+k==i*i*i+j*j*j+k*k*k)
       cout<<n<<' ';                  
  }              
  system("pause");
  return 0;        
}
