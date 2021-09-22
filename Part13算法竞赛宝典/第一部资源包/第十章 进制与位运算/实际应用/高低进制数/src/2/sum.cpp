//高低进制数 2
#include <iostream>
using namespace std;


int main()
{
  freopen("sum.in","r",stdin);
  freopen("sum.out","w",stdout);
  unsigned int n;
  cin>>n;
  n=(n>>16)+(n<<16);
  cout<<n<<endl;
  return 0;
}
