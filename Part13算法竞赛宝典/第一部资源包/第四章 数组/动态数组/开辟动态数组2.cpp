//开辟动态数组2 
#include <iostream>
using namespace std;

int main()
{
  int *a,n;
  cin>>n;
  a=new int[n];
  for(int i=0;i<n;i++)
    cin>>a[i];
  for(int i=0;i<n;i++)
    cout<<a[i]<<' ';
  system("pause");
  return 0;      
}
