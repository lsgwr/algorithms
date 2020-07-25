//全排列算法-深搜字典序 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;

bool used[100];//标记某个数字是否被使用过 
int a[100],Count,N;

void print()
{
  for(int k=1;k<N+1;k++)
    cout<<a[k];
  cout<<"\n";     
  Count++;
}

void dfs(int i)
{
  if(i>N)//递归结束，打印结果 
    print();
  else  
    for(int k=1;k<=N;k++)
      if(used[k]==0) 
      {
        used[k]=1;//做已使用过的标记 
        a[i]=k;//赋值 
        dfs(i+1);
        used[k]=0;//还原为未使用 
      }
}

int main()
{
  freopen("permutation.in","r",stdin);
  freopen("permutation.out","w",stdout); 
  int j;
  cin>>N;
  dfs(1);
  cout<<Count<<endl;
  return 0;
}
