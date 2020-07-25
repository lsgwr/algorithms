//加分二叉树 
#include <iostream>
#include <cstdlib>
using namespace std;

int n;
int d[31];
int f[31][31];
int root[31][31];//记录节点，用于输出遍历 

int df(int left, int right)
{
  int score = -1;
  int node = -1;//根结点编号 
  if(f[left][right] != -1)//如果已算出值 
  {
    score = f[left][right];
    node = root[left][right];
  }
  else if(left > right)//树为空 
    score = 1;
  else if(left == right)//根是叶结点 
  {
    score = d[left];
    node = left;
  }
  else//这棵树必有子结点 
  {
    for(int i=left; i<=right; i++)//动规 
    {
      int tmp=df(left,i-1)*df(i+1,right)+d[i];     
      if(score<tmp)
      {
        score = tmp;
        node = i;//根新根节点为i 
      }
    }
  }
  f[left][right] = score;
  root[left][right] = node;
  return score;
}

void print(int left, int right)//遍历输出 
{
  if(left <= right)
  {
    cout<<root[left][right]<<" "; 
    print(left,root[left][right]-1);
    print(root[left][right]+1,right);
  }
}

int main()
{
  freopen("tree.in","r",stdin);
  freopen("tree.out","w",stdout);  
  cin>>n;
  for(int i=1;i<=n;i++)
    cin>>d[i];
  for(int i=1;i<=n;i++) 
    for(int j=1; j<=n; j++)
    {
      f[i][j] = -1;  //均初始化为-1   
      root[i][j] = -1;
    }
  cout<<df(1,n)<<endl;
  print(1,n);
  cout<<endl;
  return 0;
}
