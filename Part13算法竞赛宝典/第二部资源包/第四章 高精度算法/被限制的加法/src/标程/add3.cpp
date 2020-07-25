//被限制的加法
#include <iostream>
#include <fstream>
using namespace std;
int n,a,b,c,n9,sum,i,j;
bool first;

int main()
{
  ifstream cin("add3.in");
  ofstream cout("add3.out");  
  cin>>n;
  first=1;
  c=0;
  n9=0;//n9表示之前积累的9的个数，初始为0 
  for(i=1;i<=n;i++)//从高位开始，依次处理每一位 
  {
    cin>>a>>b;
    sum=a+b;
    if(sum<9)//无进位情况 
    {
      if(c>0||first==0)//避免前导0 
        cout<<c;
      for(j=1;j<=n9;j++)//因为该位无进位，则可将之前积累的999..99输出 
        cout<<9;
      first=0;
      n9=0;//积累的999...999已输出，因此设为0 
      c=sum;
    }
    else
      if(sum==9)//为9时，只要记录9的个数 
        n9++;
      else//大于9，即产生进位情况 
      {
        c++;//进位后输出 
        cout<<c;
        for(j=1;j<=n9;j++)//因为进位，则将前面积累的9999..99以0000...000输出 
          cout<<0;
        first=0;
        n9=0;
        c=sum-10;//c记录该位数进位后余下的数 
      }
  }
  
  cout<<c;//处理剩下的一段999...999 
  for(j=1;j<=n9;j++)
    cout<<9;
  cout<<"\n"; 
  cin.close();
  cout.close();
  return 0;
}
