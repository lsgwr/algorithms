//н╩тккЦеп╤о
#include <iostream>
using namespace std;

int main()
{
   int x;
   cin>>x;
   x=x ^ (x >> 1);
   x=x ^ (x >> 2);
   x=x ^ (x >> 4);
   x=x ^ (x >> 8);
   x=x ^ (x >> 16);
   cout<<(x&1)<<endl;
   system("pause");
   return 0;
}  
