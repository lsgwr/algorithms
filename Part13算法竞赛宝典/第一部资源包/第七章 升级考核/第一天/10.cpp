# include <iostream>
using namespace std;

int fun(int a);

int main()
{
  int a=2,i;
  for(i=0;i<3;i++)
    cout<<fun(a)<<" ";
  cout<<endl; 
  getchar();     
}

int fun(int a)
{
  int b=0;
  static int c=3;
  b++;
  c++;
  return(a+b+c);
}
