//´òÓ¡Êı×ÖÁâĞÎ2
#include <iostream>
using namespace std;

int main()
{
  for(int i=-3;i<=3;i++)
  {
    int k=abs(i);      
    for(int j=1;j<=k;j++)
     cout<<' ';
    for(int j=k-3;j<=3-k;j++)
      cout<<abs(j)+1;
    cout<<endl;                    
  }
  system("pause");
  return 0;
}
