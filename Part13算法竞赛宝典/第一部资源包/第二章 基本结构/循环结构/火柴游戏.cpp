//火柴游戏
#include <iostream>
using namespace std;

int main()
{
  int num=21,man,computer;
  while(num!=1)
  {
    cout<<"共有"<<num<<"根火柴,请问你取多少根火柴?(1-4)\n";           
    cin>>man;
    if(man>4 ||man<1)
      continue;  
    cout<<"\n计算机取"<<5-man<<"根火柴,还剩"<<num-5<<"根火柴\n";
   num=num-5;
  }  
  cout<<"你输了! ";
  system("pause");
  return 0;  
}
