//求N！后面零的个数，方法3仅需sqrt(N)次循环
#include <iostream>
using namespace std;
int i,ii,n;

int main()
{
  cin>>n;
  i=n;
  ii=0;
  while(i>=5)
  {
    i=i/5;
    ii=ii+i;
  }
  cout<<ii<<endl;
  system("pause");
  return 0;
}
