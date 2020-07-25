//负下标数组 
# include <iostream>
using namespace std;
int main ()
{
  int a[100],i,*p;
  for (i = 0; i < 100; i++)//赋初值 
    a[i] = i;
    
  p = &a[50];//指针指向数组中间 
  for (i = -50; i < 50; i++)
    cout<<p[i]<<' ';
  return 0;
}
