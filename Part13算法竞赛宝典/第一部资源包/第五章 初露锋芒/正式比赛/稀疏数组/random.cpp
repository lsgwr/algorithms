#include <iostream>
using namespace std;

int main()
{
  freopen("zip5.in","w",stdout);
  int n,m;
  cin>>n>>m;
  cout<<n<<' '<<m<<endl;
  for(int i=0;i<n;i++)
  {
    for(int j=0;j<m;j++)
      cout<<0<<' ';
    cout<<endl;
  }  
}
