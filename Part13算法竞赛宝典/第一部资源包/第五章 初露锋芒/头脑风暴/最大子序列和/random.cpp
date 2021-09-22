#include <iostream>
using namespace std;
#define random(num)     (rand() % (num))
#define randomize()     srand((unsigned)time(NULL))
#define n 3000000
//10000000
int a[n+1],i,temp;

int main()
{
  freopen("sum10.in","w",stdout);
  randomize();//获取随机数种子 
  a[1]=n;
  for(i=1;i<=n;i++)
    a[i]=random(n);
  cout<<a[1]<<endl;  
  for(i=1;i<=n-1;i++)
  {
    temp=random(2);
    if(temp==0)                  
      printf("%d ",-a[i]);
    else
      printf("%d ",a[i]);    
  }
  cout<<a[n]<<endl;

}
