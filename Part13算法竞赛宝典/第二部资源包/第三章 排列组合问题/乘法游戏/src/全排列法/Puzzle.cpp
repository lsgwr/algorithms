//乘法游戏-全排列法 
#include <iostream>
using namespace std;
int card[20],permutation[20],n,ans=2147483647;
bool used[20];

void calculate()
{
  int i, sum=0, remove[20];
  for(i=1; i<=n+2; i++)  
    remove[i]=1;//remove[i]用于标记card[i]是否被取走,0表示被取走
  for(i=1; i<=n; i++)
  {
    remove[permutation[i]+1]=0;
    int mul=card[permutation[i]+1];//取出的牌的值赋给mul 
    int j=permutation[i]+1;//取出的牌的位置赋值给j 
    while(remove[j]==0) 
      j++;  //找到右边的牌
    mul*=card[j];     
    while(remove[j-1]==0) 
      j--;  //找到左边的牌
    mul*=card[j-1];
    sum+=mul;  //将乘积计入总分
  }
  if(sum<ans)//选出最小值 
    ans=sum;
}

void dfs(int dep)
{
  if(dep > n)
    calculate();
  else
    for(int i=1; i<=n; i++)
      if(used[i]==0)
      {
        permutation[dep]=i;
        used[i]=1;
        dfs(dep+1);
        used[i]=0;
      }
}

int main()
{
  freopen("Puzzle.in","r",stdin);
  freopen("Puzzle.out","w",stdout);
  int i;
  cin>>n;
  for(i=1; i<=n; i++)
    cin>>card[i];
  n-=2;  //要取的牌只有n-2张
  dfs(1);
  cout<<ans <<endl;
  return 0;
}
