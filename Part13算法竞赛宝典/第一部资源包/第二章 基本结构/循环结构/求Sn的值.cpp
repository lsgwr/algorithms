//«ÛS=a+aa+aaa+aaaa+°≠°≠
#include <iostream>
#include <cstdlib>
using namespace std;

int main()
{
  int a,n,count=1,sn=0,tn=0;
  cin>>a>>n;//a=3   n=4
  while(count<=n)
  {
    tn=tn+a;
    sn=sn+tn;
    a=a*10;
    ++count;

  }
  cout<<sn<<endl;
  system("pause");
  return 0;
}
