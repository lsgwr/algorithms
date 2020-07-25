//µİ¹éÇóº¯Êı 
#include "iostream"
using namespace std;

double px(double x,int n)
{						
  if (n==1)							
    return x;
  else 
    return x*(1-px(x,n-1)); 	
}
main() 
{ 
  double x;
  int n;
  cin>>x>>n;		
  cout<<px(x,n)<<endl;
  system("pause");
}
