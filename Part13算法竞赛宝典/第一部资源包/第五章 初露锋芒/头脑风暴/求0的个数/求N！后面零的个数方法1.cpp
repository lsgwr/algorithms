//求N！后面零的个数，方法1
#include <iostream>
using namespace std;

int i,ii,n;
long sum;

int main()
{
  ii=0;
  sum=1;
  cin>>n;
  for(i=1; i<=n; i++)
  {
    sum=sum*i;
    while(sum%10==0)//若后面有0，则去掉0并计数
    {
      sum=sum/10;
      ii=ii+1;
    }
    sum=sum%1000;//保持三位数即可
  }
  cout<<ii<<endl;
  return 0;
}
