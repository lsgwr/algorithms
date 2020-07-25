# include <iostream>
using namespace std;

int fact()
{
  static int i=5;
  if(i==0) return 1;
  else
  {
    i--;
    return (i+1)*fact();  
  }   
}

int main()
{
  cout<<fact()<<endl;
  getchar();     
}
