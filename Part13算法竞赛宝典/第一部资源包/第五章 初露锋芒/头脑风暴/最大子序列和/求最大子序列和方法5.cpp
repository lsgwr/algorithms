//求最大子序列和 方法5
#include <iostream>
#include <fstream>
using namespace std;
int a[35000];

int main()
{
    ifstream fin("sum.in");
    ofstream fout("sum.out");
    int i,j,k,n,sum=-2147483647,b=0;
    fin>>n;
    for(i=1;i<=n;++i)
      fin>>a[i];
    for(i=n;i>=1;--i)
    {
      b+=a[i];
      if(b>sum)
        sum=b;
      if(b<0)
        b=0; 
      
    }  
    fout<<sum<<endl;
    return 0;    
}
