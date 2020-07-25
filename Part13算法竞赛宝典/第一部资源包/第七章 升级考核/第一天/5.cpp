#include <iostream>
using namespace std;

void sort(int a[],int n)
{
  int i,j,t;
  for(i=0;i<n-1;i++)
    for(j=i+1;j<n;j++)
      if(a[i]<a[j])
      {
        t=a[i];
        a[i]=a[j];
        a[j]=t;
      }     
}

int main()
{
  int aa[10]={1,2,3,4,5,6,7,8,9,10},i;
  sort(aa,5);
  for(i=0;i<10;i++)
    cout<<aa[i]<<" ";
  cout<<endl;
  getchar();  
}
