//翻转棋盘－DFS 
#include <iostream>
#include <cstdlib>
using namespace std;
 
bool map[6][6], Find = false;//变量名不要写成find,会有冲突 
int step;
int dr[5] = {-1, 0, 0, 0, 1};//上下左右中棋子的位移值 
int dc[5] = {0, -1, 0, 1, 0};
 
bool OK()//判断是否成功 
{                           
  for(int i = 1; i <= 4; i ++)
    for(int j = 1; j <= 4; j ++)
      if(map[i][j] != map[1][1])
        return false;
  return true;
}
 
void flip(int row, int col)//翻动点(row,col),改变map[][]状态
{             
  for(int i = 0; i < 5; i ++)
  {
    int x = row + dr[i];
    int y = col + dc[i];
    map[x][y] = !map[x][y];
  }
}
 
void dfs(int row, int col, int dep)//翻转点(row,col),dep为深度
{     
  if(dep == step)
  {
    Find = OK();
    return;
  }
  if(Find || row == 5) 
    return;

  flip(row, col);//翻转点(row,col)
  if(col < 4) 
    dfs(row, col + 1, dep + 1);
  else 
    dfs(row + 1, 1, dep + 1);

  flip(row, col);//还原点(row,col)
  if(col < 4) 
    dfs(row, col + 1, dep);
  else 
    dfs(row + 1, 1, dep);
}
 
int main()
{
  freopen("FlipGame.in","r",stdin);
  freopen("FlipGame.out","w",stdout);  
  char c;
  for(int i = 1; i <= 4; i ++)
    for(int j = 1; j <= 4; j ++)
    {
      cin >> c;
      if(c == 'b') 
        map[i][j] = true;
    }
  for(step = 0; step <= 16; step ++)//16个棋子，共枚举0~16步
  {
    dfs(1, 1, 0);
    if(Find) 
      break;
  }
  if(Find) 
    cout<<step<<endl;
  else 
    cout <<"Impossible\n";
  return 0; 
}
