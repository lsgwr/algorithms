//ÇúÏß·Ö¸î 
#include <iostream>
#include <cstdlib>
using namespace std;

int f(int n)
{
  if(n==1)
    return 2;
  else
    return f(n-1)+2*(n-1);
}

int main()
{
  freopen("curve5.in","r",stdin);
  freopen("curve5.ans","w",stdout);  
  int n;
  cin>>n;
  cout<<f(n)<<endl;
  return 0;
}
