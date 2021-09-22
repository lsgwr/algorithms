//求e的值
#include <iostream>
#include <cstdlib>
using namespace std;

int main()
{
  float s=1,t=1;
  int i;
  for(i=1; i<=100; i++)
  {
    t=t/i;//1/i!由前项1/(i-1)!递推而来
    s=s+t;
  }
  cout<<s;
  system("pause");
  return 0;
}
