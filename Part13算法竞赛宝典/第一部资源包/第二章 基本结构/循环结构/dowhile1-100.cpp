//¼ÆËã1+2+3¡­+100µÄÖµ
#include <iostream>
using namespace std;

int main()
{
  int i=1,sum=0;
  do
  {
    sum=sum+i;
    i++;
  } while(i<=100);
  cout<<sum<<endl;
  system("pause");
  return 0;
}
