# include <iostream>
using namespace std;

int func(int x)
{
  int p;
  if(x==0||x==1)
    return 3;
  p=x-func(x-2);
    return p;     
}

int main()
{
  cout<<func(9)<<endl;
  getchar();  
}
