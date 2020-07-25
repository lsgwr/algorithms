//三角形剖分算法３ 
#include <iostream>
using namespace std;
#define MAXN 100

long f(int x)
{
  if (x==3)
    return 1;
  else
    return((4*x-10)*f(x-1)/(x-1));
}

int main()
{
  freopen("triangle.in","r",stdin);
  freopen("triangle.out","w",stdout);  
  int n;
  cin>>n;
  if((n<=MAXN) && (n>=3))
    cout<<f(n)<<endl;
  return 0;
}
