#include <iostream>
using namespace std;

int main()
{
  int a[4][4]={{1,2,-3,-4},{0,-12,-13,14},{-21,23,0,-24},{-31,32,-33,0}};
  int i,j,s=0;
  for(i=1;i<4;i++)
  {
    for(j=0;j<4;j++)
    {
      if(a[i][j]<0) continue;
      if(a[i][j]==0) break;
      s+=a[i][j];
    }
  } 
  cout<<s<<endl;
  getchar();  
}
