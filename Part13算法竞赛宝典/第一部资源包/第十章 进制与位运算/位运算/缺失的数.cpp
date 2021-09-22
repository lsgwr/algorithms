//È±Ê§µÄÊı 
#include <iostream>
using namespace std;

int find(int a[],int size)
{
  int number=0;
  for(int i=0;i<size;i++)
  {
    number=number^(i+1)^a[i];
  }
  number^=(size+1);
  return number;
}

int main()
{
  int a[10]={1,2,4,5,6,7,8,9,10,11};
  cout<<find(a,10)<<endl;
  system("pause");
}
