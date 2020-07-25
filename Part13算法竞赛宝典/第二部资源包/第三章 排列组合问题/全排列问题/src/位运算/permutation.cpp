//全排列位运算 
#include<iostream>
using namespace std;
int N,a[33],full,bitmod[39],amount;

void print()
{
  for(int i=1;i<N;++i)
    cout<<a[i];
  cout<<a[N]<<'\n';
}

void dfs(int dep,int layout)
{
  if(layout==full)//如果所有值均被取走，则输出一个排列 
  {
    print();
    ++amount;
  }
  else
  {
    int p,CanPut=(~layout)&full;//获得当前可取的数 
    while(CanPut)
    {
      p=CanPut&(-CanPut);//找到最右边为1的数 
      CanPut-=p;//将该数取出 
      a[dep]=bitmod[p%37];//保存该棋子的位置 
      dfs(dep+1,layout+p);
    }
  }
}

int main()
{
  freopen("permutation.in","r",stdin);
  freopen("permutation.out","w",stdout);     
  cin>>N;
  for(int i=0;i<N;++i)
    bitmod[(1<<i) % 37]= i+1;//建立哈希表 
  full=(1<<N)-1;//初始为111111...111111，表示所有数均被取走 
  dfs(1,0);
  cout<<amount<<'\n';
  return 0;
}
