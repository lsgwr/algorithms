//二分查找32位整数的前导0个数 
#include <iostream>
using namespace std;

int nlz(unsigned x)
{
   int n;
   if (x == 0) 
return(32);
   n = 1;
   if ((x >> 16) == 0) 
{n+=16; x = x <<16;}
   if ((x >> 24) == 0) 
{n+=8; x = x << 8;}
   if ((x >> 28) == 0) 
{n+=4; x = x << 4;}
   if ((x >> 30) == 0) 
{n+=2; x = x << 2;}
   n = n - (x >> 31);
   return n;
}

int main()
{
   unsigned x,ans;
   cin>>x;//例如输入7，结果为29
   ans=nlz(x);
   cout<<ans<<endl;
   system("pause");
   return 0;
}
