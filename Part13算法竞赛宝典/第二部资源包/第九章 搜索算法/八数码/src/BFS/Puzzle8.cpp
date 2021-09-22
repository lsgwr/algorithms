//八数码－宽度搜索 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
using namespace std;

const int MAXN=370000;

int n, final, Ans, p;
int last[MAXN], queue[MAXN], rank[MAXN];
//last上一步的号码，用于输出路径（本题无输出路径，仅作参考）
//queue存储队列
//rank 记录当前搜索深度，即走了多少次
bool visit[MAXN];//结点是否已经访问
int s[9];//当前八数码的状态
int front = 1,rear = 1;//队列首尾

int turn()//转换成数字
{
  int i;
  int ans = 0;
  for(i = 0; i < 9; i++)
    ans = ans * 10 + s[i];
  return ans;
}

int cantor()//康托压缩
{
  bool use[9] = {0};
  int i, j, no;
  int ans = 0;
  for(i = 8; i >= 1; i--)
  {
    no = 0;
    use[s[8 - i]] = true;
    for(j = 0; j < s[8 - i]; j++)	
    {
      if(use[j] == true)
		no++;
    }
    ans = (ans + s[8 - i] - no) * i;//0 开始
  }
  return ans;
}

void init()
{
  int i;
  for(i = 0; i < 9; i++)//初状态
  {
    scanf("%d", &s[i]);
    n = n * 10 + s[i];
  }
  queue[1] = n;
  visit[cantor()] = true;
  n = 0;
  s[0] = 1;s[1] = 2;s[2] = 3;
  s[3] = 8;s[4] = 0;s[5] = 4;
  s[6] = 7;s[7] = 6;s[8] = 5;
  final = cantor();//末状态
}

void Ucan(int num)//反康托展开，这里用数字展开，而非康托值展开
{
  int i;
  for(i = 8; i >= 0; i--)
  {
    s[i] = num % 10;
    num /= 10;
  }
}

int findzero()//到0的位置
{
  int i;
  for(i = 0; i < 9; i++)
    if(s[i] == 0)
  return i;	
}

void bfs(int c)
{
  int t, num;
  t = s[p]; s[p] = s[p + c]; s[p + c] = t;
  num = cantor();
  if(visit[num] != true)
  {
    queue[++rear] = turn();//换成数字
    visit[num] = true;
    last[rear] = front;
    rank[rear] = rank[front] + 1;
    if(num == final)//到答案
      Ans = rank[front] + 1;
  }
  t = s[p]; s[p] = s[p + c]; s[p + c] = t;
}

int main()
{
  freopen("Puzzle8.in","r",stdin);
  freopen("Puzzle8.out","w",stdout);
  init();
  if(visit[final] == true)//初状态即末状态
  {
    printf("0\n");
    return 0;
  }
  while(front <= rear && Ans == 0)
  {
    Ucan(queue[front]);
    p = findzero();
    if(p >= 3)//向上搜索
      bfs(-3);
    if(Ans == 0 && p < 6)
      bfs(3);//向下搜索
    if(Ans == 0 && (p % 3) > 0)
      bfs(-1);//向左搜索
    if(Ans == 0 && (p % 3) < 2)
      bfs(1);//向右搜索
    front++;
  }
  if(Ans == 0)//始终没有答案
    printf("-1\n");
  else
    printf("%d\n", Ans);
  return 0;
}
