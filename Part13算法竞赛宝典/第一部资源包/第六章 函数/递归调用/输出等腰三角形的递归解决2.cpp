//输出等腰三角形的递归解决2
#include <iostream>
using namespace std;
int b;
void trangle(int n)
{
     if(n==1)
     {
        for(int i=0;i<2*b-1;i++)
           cout<<"*";
        cout<<endl;
     }
     else
     {
         for(int j=0;j<n-1;j++)
            cout<<" ";
         for(int k=0;k<2*b-2*n+1;k++)
            cout<<"*";
         cout<<endl;
         trangle(n-1);
     }
}
int main()
{
  cin>>b;
  trangle(b);
  system("pause");
  return 0;
}
