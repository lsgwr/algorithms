//显示ASCII码
#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
  int i,n=0;
  for(i=15;i<128;i++)
  {
    if (n%10==0 && n>0)
      cout<<endl;//每显示十个换一行
    cout <<setw(4)<<i<<":"<<(char)i<<" ";
    n++;   
  }  
  cout<<endl;
  system("pause");
  return 0;
}
