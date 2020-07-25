#include <iostream>
using namespace std;

int main()
{
  int n;
  cin>>n;
  switch(n)
  {
    case 3:n+=3;
    case 1:n++;
      break;
    case 5:n+=5;
    case 4:n+=4;           
  }
  cout<<"n="<<n<<endl;
  system("pause");
}
