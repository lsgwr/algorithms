//等级制
#include <iostream>
using namespace std;

int main()
{
  float x;
  cout<<"输入分数：";cin>>x;
  if(x>=90) 
    cout<<"A"<<endl;
  else if (x>=80) 
    cout <<"B"<<endl;
  else if (x>=70) 
    cout<<"C"<<endl;
  else if (x>=60) 
    cout<<"D"<<endl;
  else 
    cout<<"E"<<endl;
  system("pause");
  return 0;      
}
