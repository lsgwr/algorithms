//利用不等式优化算法
#include <iostream>
using namespace std;

int main()
{
  int i,j,k,count=0;
  for(i=1;i<=9;i++)
    for(j=1;j<=18;j++)
      if(i*10+j*5<100)
          count++;
   cout<<count<<endl;       
  system("pause");
}
