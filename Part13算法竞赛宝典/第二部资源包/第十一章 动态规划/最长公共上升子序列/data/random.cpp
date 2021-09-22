//产生指定长度的随机字符串 
#include <iostream>
using namespace std;
#define random(num)     (rand() % (num))
#define randomize()     srand((unsigned)time(NULL))

int main()
{
  int i,n,m;//输出n行m个字符的随机字符串
  freopen("LCIS5.in","w",stdout);

  cin>>n>>m; 
  randomize();
  cout<<n<<endl; 

    for(int j=1;j<=n;j++)
    {
      cout<<random(200)<<' ';
    }

  cout<<m<<endl;

    for(int j=1;j<=m;j++)
    {
      cout<<random(200)<<' ';
    }
 

}
