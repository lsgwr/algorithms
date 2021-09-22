//换零钱2-通用公式
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;

int main()
{
  int n,a,b,c,sum;
  cin>>n;
  a=n/10;
  c=n%10;
  if(c<=5)//如果剩下的钱不够一张5元和一张1元 
    a--;
  b=(n-10)/5;
  c=(n-10)%5;
  if(c<1)
    b--;
  sum=a*(b-a+1);
  cout<<sum<<endl;    
  system("pause");
}
