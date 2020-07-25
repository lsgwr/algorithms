//八数码问题－ＩＤＡ* 
#include <iostream>
#include <cstdlib>
using namespace std;

inline int Abs(int a) 
{
  return a>0 ? a : -a;
}

int limit=-1;//深度限制
char ans[100];

//数字表示方向，0为上，1为右，2为下，3为左
char cdir[4]={'u','r','d','l'};//方向对应字母
char opcdir[4]={'d','l','u','r'};//反方向对应字母

/* 某数字在棋盘中的位置loc用一个0~8的整数表示，分别对应以下位置
   0 1 2
   3 4 5
   6 7 8
   那么该位置所在行为loc/3 , 所在列为loc%3  (均从0开始计数
   如位置1在第0行第1列  */
/*目标状态
  1 2 3
  8 0 4
  7 6 5  */
const int aim_state[9]={1,2,3,8,0,4,7,6,5};//目标状态
const int aim_loc[9]={4,0,1,2,5,8,7,6,3};//每个数字的目标位置

inline int ManhattanDis(int loc1,int loc2)
{
  return Abs(loc1/3-loc2/3) + Abs(loc1%3-loc2%3);
}

inline int h(int state[9])//以 1-8 的点的位置距离它们的目标位置和为估价值
{  
  int ans=0;
  for(int i=0;i<9;i++)
    if(state[i])
      ans+=ManhattanDis(i,aim_loc[state[i]]);
  return ans;
}

inline bool check(int state[9])//比较是否成功 
{
  for(int i=0;i<9;i++)
    if(state[i]!=aim_state[i])
      return 0;
  return 1;
}

//判断空格在loc位置时能否向dir方向移动，同时返回移动后的位置nxt
inline bool canMove(int loc, int dir, int &nxt)
{
  switch(dir)
  {
    case 0://上
        nxt=loc-3;
        return loc>2;
    case 1://右
        nxt=loc+1;
        return loc%3!=2;
    case 2://下
        nxt=loc+3;
        return loc<6;
    case 3://左
        nxt=loc-1;
        return loc%3;
  }
}

//state为当前棋盘状态，now为空格当前位置，dep为搜索深度
bool dfs(int state[], int now, int dep=0)
{
  if(dep==limit)
    return check(state);
    
  if(dep+h(state)>limit) //深度限制
    return 0;
  int nxt;//空格移动后的位置
  for(int dir=0;dir<4;dir++)//空格可往四个方向移动
    if(  (dep==0 || ans[dep-1]!=opcdir[dir]) //不往回走
          && canMove(now,dir,nxt) )
    {
      state[now]=state[nxt];
      state[nxt]=0;
            
      ans[dep]=cdir[dir];
      if(dfs(state, nxt, dep+1))
        return 1;
      //还原
      state[nxt]=state[now];
      state[now]=0;
    }    
  return 0;
}
int main()
{
  freopen("Puzzle8.in","r",stdin);
  freopen("Puzzle8.out","w",stdout);
  int initial_state[9], x;

  for(int i=0; i<9; i++)
  {
    cin>>initial_state[i];
    if(initial_state[i]==0)
      x=i;
  }
    
  //判断是否有解，注意本题是初始序列逆序对数位奇数则有解
  int sum=0;
  for(int i=0;i<8;i++)  
    for(int j=i+1;j<9;j++)
      if(  initial_state[i] && initial_state[j]
              && initial_state[i]>initial_state[j])
        sum++;
  if(sum&1)
  {
    limit=h(initial_state);
    while(limit<100 && !dfs(initial_state, x))
      limit++;
  }
  cout<<limit<<endl;
  return 0;
}
