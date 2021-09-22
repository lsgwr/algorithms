#include <iostream>
using namespace std;

int main()
{
  int n;
  cin>>n;
  if(n++<10)
    cout<<n<<endl;
  else 
    cout<<n--<<endl;
}
