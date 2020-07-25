//求子集-位运算 
#include<iostream>
using namespace std;
int N;

void output(int n)
{
  cout<<"(";   
  for(int i=0;i<N;i++)
    if((1<<i)&n) 
      cout<<char(i+'a')<<' ';
    cout<<")";     
  cout<<endl;
}

int main()
{
  freopen("Subset.in","r",stdin);
  freopen("Subset.out","w",stdout); 
  cin>>N;
  cout<<"()\n";
  for(int i=1;i<(1<<N);i++)//循环2^N次 
    output(i);  
  return 0;
}
