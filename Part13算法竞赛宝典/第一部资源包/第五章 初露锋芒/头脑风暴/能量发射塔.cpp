/*数学推导
  1+2+3....+n=(1+n)n/2=n*n/2+n/2
  s=1*1/2+1/2 + 2*2/2+2/2 + 3*3/2+3/2 ....+ n*n/2 + n/2
   =(1*1+2*2+3*3...+n*n)/2 + n*(n+1)/4
  f(x)=x^3-(x-1)^3=3x^2-3x+1
  f(x)+f(x-1)...f(1)=x^3-1=3[x^2+(x-1)^2...1^2]-3(1+x)x/2+x
    1*1+2*2+3*3...+n*n=n(n+1)(2n+1)/6
  s=n(n+1)(2n+1)/12+n(n+1)/4=n*(n+1)*(n+2)/6
*/
#include <iostream>
using namespace std ;

int main ()
{
  int n;
  cin>>n;
  cout<<n*(n+1)*(n+2)/6<<endl;
  system("pause");
  return 0;
}
