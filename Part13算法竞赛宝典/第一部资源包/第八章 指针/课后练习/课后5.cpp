#include <iostream>
using namespace std;

int main()
{
  int a[][3]={{1,2,3},{4,5,0}},(*pa)[3],i;
  pa=a;
  for(i=0;i<3;i++)
    if(i<2)
      pa[1][i]=pa[1][i]-1;
    else
      pa[1][i]=1;
  cout<<a[0][1]+a[1][1]+a[1][2]<<endl;
  getchar();     
}
