//Å£¼ÇÊý1
#include <iostream>
using namespace std;
int s,t,c;

int main()
{
  freopen("cow.in","r",stdin);
  freopen("cow.out","w",stdout);
  cin>>s>>t;
  for(int i=s;i<=t;++i)
  {
    int cc=0;
    for(int x=i;x>0;x/=2)
      cc+=x%2;
    c+=(cc<=4);
  }
  cout<<c<<endl;   
  system("pause");
}
