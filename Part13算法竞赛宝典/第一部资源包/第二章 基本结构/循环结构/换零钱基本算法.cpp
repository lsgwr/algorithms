//»»ÁãÇ®»ù±¾Ëã·¨
#include <iostream>
using namespace std;

int main()
{
  int i,j,k,count=0;
  for(i=1;i<=9;i++)
    for(j=1;j<=18;j++)
      for(k=1;k<=85;k++)
        if((i*10+j*5+k*1)==100)
          count++;
   cout<<count<<endl;       
  system("pause");
}
