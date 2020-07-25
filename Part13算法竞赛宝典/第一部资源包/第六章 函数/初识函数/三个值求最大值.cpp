//输入三个值，求其中的最大值
#include <iostream>
using namespace std;

int Max(int x,int y,int z)
{
  if(x>y && x>z)
    return x;
  else if(y>x && y>z)
    return y;
  else return z;               
}
main()
{
  int a,b,c,result;
  cin>>a>>b>>c;
  result=Max(a,b,c); 
  cout<<"最大值为"<<result<<endl; 
  return 0;
}
