//程序名称：最朴素的求X的N次方 

#include <iostream>
#include <cstdlib>
using namespace std;

unsigned Power(unsigned n,unsigned p)
{
  if(p==0)
    return 1;       
  unsigned result=1;      
  for(int i=0;i<p;i++)
   result*=n;
  return result;
}

int main()
{
  freopen("power.in","r",stdin);  
  freopen("power.out","w",stdout);  
  int n,p;
  cin>>n>>p;
  cout<<Power(n,p)<<endl;
}
