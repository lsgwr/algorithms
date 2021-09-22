//求子集-递归算法 
#include <iostream>
#include <cstdlib>
using namespace std;
int i,j,n;
int a[26+1];//用01来标记，即用N位01对应一个N位二进制数并枚举 

void print()
{
  int i;
  cout<<"(";
  for(i=1;i<=n;i++)
    if(a[i]==1)
      cout<<char('a'+i-1)<<' ';
  cout<<")\n";
}

void Try(int digit)
{
  for(int i=0;i<=1;i++)//穷举所有的0，1组合 
  {
    a[digit]=i;
    if(digit==n)
      print();
    else
      Try(digit+1);
    a[digit]=-1;
  }
}

int main()
{
  freopen("Subset.in","r",stdin);
  freopen("Subset.out","w",stdout);  
  cin>>n;
  for(i=1;i<=n;i++)
    a[i]=-1;
  Try(1);
  return 0;
}
