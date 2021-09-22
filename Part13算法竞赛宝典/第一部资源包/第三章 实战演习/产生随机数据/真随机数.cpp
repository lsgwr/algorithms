//真随机数 
#include <iostream>
using namespace std;    
#define n 100
int a[101],i;

int main()
{
  srand((unsigned)time(NULL));//获取随机数种子,rand()产生0-32767之间的数 
  for(i=1;i<=n;i++)
    a[i]=rand() % 100;
  for(i=1;i<=n;i++)
    cout<<a[i]<<' ';
  getchar();
  return 0;
}
