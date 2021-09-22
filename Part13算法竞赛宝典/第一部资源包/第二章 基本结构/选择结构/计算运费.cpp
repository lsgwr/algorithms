//计算运费
#include <iostream>
using namespace std;

int main()
{
  int c,s;
  float p,w,d,f;
  cin>>p>>w>>s;
  c=s/250; //c是s的映射 
  switch(c)
  {
    case 0:d=0;break;
    case 1:d=2;break;
    case 2:
    case 3:d=5;break;
    case 4:
    case 5:
    case 6:
    case 7:d=8;break;
    case 8:
    case 9:
    case 10:
    case 11:d=10;break;
    default:d=15;break;
  }
  f=p*w*s*(1-d/100.0);
  cout<<"总运费="<<f<<endl;
  system("pause");
  return 0;
}
