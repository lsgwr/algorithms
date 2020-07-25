//改进的加密术
#include <iostream>
using namespace std;

int main()
{
  char c1,c2,c3,c4,c5;
  int t;
  cin>>c1>>c2>>c3>>c4>>c5;
  cin>>t;
  c1+=t;//即c1=c1+4;
  c2+=t;
  c3+=t;
  c4+=t;
  c5+=t;
  cout<<c1<<c2<<c3<<c4<<c5<<endl;
  system("pause");
  return 0;
}
