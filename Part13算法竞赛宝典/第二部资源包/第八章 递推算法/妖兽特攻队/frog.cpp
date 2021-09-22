//яЩйчль╧╔╤с  
#include <iostream>
using namespace std;

int main()
{
  freopen("frog5.in","r",stdin);
  freopen("frog5.ans","w",stdout);  
  int i,j,k,m,n;
  cin>>n>>m;
  m++;
  for(i=1;i<=n;i++)
    m*=2;
  cout<<m;
  return 0;   
}
