//求数列前20项之和
#include <iostream>
using namespace std;
int main()
{
  int n,t;
  long double a=2,b=1,s=0;
  int x;
  cin>>x;
  for(n=1; n<=x; n++)
  {
    s=s+a/b;
    t=a,a=a+b,b=t;
    cout<<a<<" "<<b<<"  ";
  }
  cout<<s<<endl;
  getchar();
  getchar();
  return 0;
}
