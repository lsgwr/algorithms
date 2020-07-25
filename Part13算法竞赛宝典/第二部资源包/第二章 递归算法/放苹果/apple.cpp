//·ÅÆ»¹û 
#include <iostream>
#include <cstdlib>
using namespace std;
int f( int m, int n)
{
 if( m == 1 || n ==1 || m== 0)
  return 1;
 else if( m < n )
  return f( m, m);
 else
  return f( m - n, n) + f( m, n - 1);
}

int main()
{
  freopen("apple.in","r",stdin);
  freopen("apple.ans","w",stdout);  
  int t, m, n;
  cin>>t;
  while( t-- )
  {
    cin>>m>>n;
    cout<<f( m, n)<<endl;
  }
  return 0;
}
