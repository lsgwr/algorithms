//变形约瑟夫问题解法一
#include<iostream>
using namespace std;
int main()
{
   int n,k;
   long long s=0;
   cin>>n>>k;
   for(int i=1;i<=n;i++)
     s=s+k%i;
   cout<<s<<endl;
   system("pause");
}
