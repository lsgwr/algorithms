//区域划分问题 
#include <iostream>
#include <cstdlib>
using namespace std;

int n,p,total,i;
int main()
{
  freopen("area.in","r",stdin);
  freopen("area.out","w",stdout);  
  cin>>n>>p;
  total=2*p;
  for(i=p+1;i<=n;i++)
    total=total+i;
  cout<<total<<endl;
  return 0;
}
