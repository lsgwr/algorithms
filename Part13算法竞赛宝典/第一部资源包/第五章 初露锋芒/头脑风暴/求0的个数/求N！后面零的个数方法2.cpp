//求N！后面零的个数，方法2需N/5次循环
#include <iostream>
using namespace std;

int main()
{
  int i,ii,j,n;
  j=5;ii=0;
  cin>>n;
  while(j<=n)
  {
    i=j;
    while(i%5==0)//有多少因子5，就有多少个0 
    {
      i=i/5;
      ii=ii+1;
    }
    j=j+5;
  }
  cout<<ii<<endl;
  system("pause");
  return 0;
}
