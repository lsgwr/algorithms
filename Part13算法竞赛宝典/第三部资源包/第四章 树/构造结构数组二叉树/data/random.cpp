#include <iostream>
using namespace std;
#define random(num)     (rand() % (num))
#define randomize()     srand((unsigned)time(NULL))
#define n 100
int a[101],i;

int main()
{
  freopen("arraytree10.in","w",stdout);
  randomize();//获取随机数种子,rand()产生0-32767之间的数 
  cout<<n<<endl;
  for(i=1;i<=n;i++)
    a[i]=random(100);
  for(i=1;i<=n;i++)
    printf("%d ",a[i]);
  getchar();
}

