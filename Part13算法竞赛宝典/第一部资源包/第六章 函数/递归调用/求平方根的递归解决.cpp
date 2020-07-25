//求平方根的递归解决 
#include <iostream>
#include <cmath>
using namespace std;

double SquareRoot(double a,double x0)
{  
  double x1,ans;
  x1=(x0+a/x0)/2;
  if(fabs(x1-x0)>1e-8)
    ans=SquareRoot(a,x1);
  else
    ans=x1;
  return ans;    
}

int main()
{
  double x;
  cin>>x;
  cout<<SquareRoot(x,1.0);
  system("pause");
  return 0;
}
