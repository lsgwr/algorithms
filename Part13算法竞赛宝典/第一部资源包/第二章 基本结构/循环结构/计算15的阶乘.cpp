//¼ÆËã1+2+3¡­+15µÄÖµ
#include <iostream>
using namespace std;

int main()
{
  long long i=1,sum=1;
  while(i<=15)  
  {
    sum=sum*i;
    i++;
  }   
  cout<<sum<<endl;
  system("pause");
  return 0;
}
