#include <iostream>
#include <math.h>
using namespace std;
int a[2000];
int main()
{
  int i,j;
  a[2]=2;
  for(i=3;i<=2000;i+=2)
    a[i]=i;
  for(i=3;i<=2000;i+=2)
  {
    for(j=3;j<sqrt(i);j++)
      if(a[i]%j==0)
      {
        a[i]=0;
        break;
      }
  }
  for(i=6;i<=2000;i+=2)
    for(j=3;j<i-2;j+=2)
    {  
      if(a[j]==0)
        break;
      else
        if(a[i-j]==0)
          break;
        else
          cout<<a[j]<<' '<<a[i-j]<<endl;
      if(j==i-1)
        cout<<"error"<<endl;
    }
  system("pause");
  return 0;
}
