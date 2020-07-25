# include <iostream>
using namespace std;

int fun(int k)
{
  int x,y;
  y=k;
  for(x=1;x<=(k-1);x++)
    y*=k-x;
  return y;
}

int main()
{
  int n;
  for(n=1;n<=5;n++)
    cout<<fun(n)<<" ";
  cout<<endl;     
  getchar();
}
