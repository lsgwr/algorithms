//鸿门宴 
#include<iostream>
#include <cstdlib>
using namespace std;
#define MAX(a,b) ((a)>(b)?(a):(b))
int N,f[6001][2],parent[6001];
bool visited[6001];

void tree_dp(int node)
{
  if(!visited[node])//if not visited and is not root 
  {
    visited[node] = 1;
    for(int i=1;i <= N;i++)
    {
      if(parent[i] == node)
      {
        if(!visited[i])
          tree_dp(i);
        f[node][1] += f[i][0];
        f[node][0] += MAX(f[i][0],f[i][1]);
      }
    }
  }
}

int main()
{
  freopen("party.in","r",stdin);
  freopen("party.out","w",stdout);  
  cin>>N;
  int index = 1;
  while(index <=N)//输入每个人的价值 
  {
    cin>>f[index][1];
    index++;
  }
  int left,right;
  cin>>left>>right;
  while(left != 0 && right != 0)
  {
    parent[left] = right;
    cin>>left>>right;
  }

  for(int i=1;i<=N;i++)//树型动规 
  {
    tree_dp(i);
  }
  int max = 0;
  for(int i=1;i<=N;i++)//选最优解 
  {
    int temp = MAX(f[i][0],f[i][1]);
    max = MAX( temp,max);
  }
  cout<<max<<endl;
  return 0;
}
