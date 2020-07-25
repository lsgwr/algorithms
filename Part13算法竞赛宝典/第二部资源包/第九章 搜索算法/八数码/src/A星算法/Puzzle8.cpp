//八数码－Ａ* 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
using namespace std;

const int MAXN = 370000;

struct node
{
  int math;//数字值
  int ca;//cantor值
  int zero;//0的位置
}dui[MAXN];//堆

struct node d;
int s[9],f[9]={1,2,3,8,0,4,7,6,5};//s记录过程状态，f记录末状态
int h[MAXN];//h是当前到目标的估价值
int g[MAXN];//g是起点到当前的代价
int duip[MAXN];//duip是指向dui的指针
int all = 1;//all是duip的长度
int len = 1, final;//len是dui的长度，final是末状态cantor值
bool visit[MAXN];//记录是否已经访问

int cantor()//康托压缩
{
  int i, j;
  int ans = 0;
  int no = 0;
  int use[9] = {};
  for (i = 0; i <= 7; i++)//最后一位毫无选择余地，所以循环到7
  {
    no = 0;
    use[s[i]] = true;
    for (j = 0; j < s[i]; j++)
    {
	  if (use[j])
	    no++;
    }
    ans = (ans + s[i] - no) * (8 - i);
  }
  return ans;
}

int turn()//转换为数字
{
  int i;
  int ans = 0;
  for (i = 0; i < 9; i++)
    ans = ans * 10 + s[i];
  return ans;
}

int _h()//计算h函数，该估价函数<实际值，故能得到最优解
{
  int i;
  int ans = 0;
  for (i = 0; i < 9; i++)
	if (s[i] != f[i] && s[i] != 0)//０的位置不参与考虑 
	  ans++;
  return ans;
}

void init()//输入初始化
{
  int i;
  dui[1].math = 0;
  for (i = 0; i < 9; i++)
  {
    scanf("%d", &s[i]);
    if (s[i] == 0)
      dui[1].zero = i;
  }
  dui[1].math = turn();
  dui[1].ca = cantor();
  visit[dui[1].ca] = true;
  h[dui[1].ca] = _h();
  duip[1] = 1;
  s[0] = 1; s[1] = 2; s[2] = 3;
  s[3] = 8; s[4] = 0; s[5] = 4;
  s[6] = 7; s[7] = 6; s[8] = 5;
  final = cantor();
}

void s_get(int x)//用math数字得到状态
{
  int i;
  for (i = 8; i >= 0; i--)
  {
    s[i] = x % 10;
    x /= 10;
  }
}

void upwei(int root)//由上至下维护堆
{
  int t = root;
  int change;
  if((root<<1)<=all 
  && (g[dui[duip[root<<1]].ca]+h[dui[duip[root<<1]].ca])
    <(g[dui[duip[root]].ca]+h[dui[duip[root]].ca]))
    t = root << 1;
  if(((root<< 1)+1)<=all 
  && (g[dui[duip[(root<<1)+1]].ca]+h[dui[duip[(root<<1)+1]].ca])
    <(g[dui[duip[t]].ca]+h[dui[duip[t]].ca]))
    t =(root << 1) + 1;
  if (t != root)
  {
    change = duip[t]; 
    duip[t] = duip[root]; 
    duip[root] = change;
    upwei(t);
  }
}

void downwei(int x)//由下至上维护堆
{
  int t;
  while(x>1&&(g[dui[duip[x]].ca]+h[dui[duip[x]].ca])
    <(g[dui[duip[x>>1]].ca]+h[dui[duip[x>>1]].ca]))
  {
    t = duip[x]; 
    duip[x] = duip[x >> 1]; 
    duip[x >> 1] = t; 
    x >>= 1;
  }
}

void add(int mea)//加入结点
{
  int t;
  t = s[d.zero + mea]; 
  s[d.zero + mea] = s[d.zero]; 
  s[d.zero] = t;
  t = cantor();
  if (!visit[t])
  {
    visit[t] = true;
    dui[++len].ca = t;
    dui[len].math = turn();
    dui[len].zero = d.zero + mea;
    duip[++all] = len;
    h[dui[len].ca] = _h();
    g[dui[len].ca] = g[d.ca] + 1;
    downwei(all);
  }
  t = s[d.zero + mea]; 
  s[d.zero + mea] = s[d.zero]; 
  s[d.zero] = t;
}

void _A()
{
  while (all >= 1)
  {
    if (h[dui[duip[1]].ca] == 0)
    {
      printf("%d\n", g[dui[duip[1]].ca]);
      return;
    }
    d = dui[duip[1]];
    s_get(dui[duip[1]].math);
    duip[1] = duip[all];
    all--;
    if (all > 1)
      upwei(1);
    if (d.zero > 2)//向上走
      add(-3);
    if (d.zero < 6)//向下
      add(3);
    if ((d.zero % 3) > 0)//左
      add(-1);
    if ((d.zero % 3) < 2)//右
      add(1);
  }
  printf("-1\n");
}

int main()
{
  freopen("Puzzle8.in", "r", stdin);
  freopen("Puzzle8.out", "w", stdout);
  init();
  if (dui[1].ca == final)//如果末状态即初状态
  {
    printf("0\n");
    return 0;
  }
  _A();
  return 0;
}
