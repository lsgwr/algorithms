//技能树 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
using namespace std;

int n,p,i,l[50];
int brother[50],child[50],son[50];//兄弟技能，子技能，自身 
long f[50][1000];

struct Node
{
  char n[100], f[100];
  int l, cost[50], value[50], learn;
}stree[50];

int find(char ch[100])//找到父技能，只有学了父技能，才能学子技能 
 
{
  for (int j = 1; j <= n; j++)
	if (strcmp(stree[j].n, ch) == 0) 
      return j;
  return 0;
}

void clear()
{
  memset(son, 0, sizeof(son));
  memset(brother, 0, sizeof(brother));
  memset(child, 0, sizeof(child));
  memset(f, -1, sizeof(f));
  memset(stree, 0, sizeof(stree));
}

void set_tree()
{
  int father;
  clear();
  for (i = 1; i <= n; i++)
  {
    cin.getline(stree[i].n, 100);//extra '\n'
    cin.getline(stree[i].n, 100);
    cin.getline(stree[i].f, 100);
    scanf("%d", &stree[i].l);
    for (int j = 1; j <= stree[i].l; j++) 
      scanf("%d", &stree[i].cost[j]);
    for (int j = 1; j <= stree[i].l; j++) 
      scanf("%d", &stree[i].value[j]);
  }
  scanf("%d", &p);
  for (i = 1; i <= n; i++) 
    scanf("%d", &stree[i].learn);
  for (i = 0; i <= p; i++) 
    f[0][i] = 0;
  for (i = 1; i <= n; i++)//按要求把技能之间的关系建好树 
  {
    father = find(stree[i].f);
    if (son[father] == 0) 
      child[father] = i;
    else 
      brother[son[father]] = i;
    son[father] = i;
  }
}

long dp(int root, int j)//动规 
{
  if (root == 0) 
    return f[root][j] = 0; // 此时直接继续 
  if (f[root][j] >= 0) 
    return f[root][j];
  long max, co = 0, va = 0, s;
  max = dp(brother[root], j);
  if (stree[root].learn) //如果该技能已经学习过一些 
  for (int k = 1; k <= j; k++)
  {
    s=dp(child[root],k)+dp(brother[root],j-k);//子技能
    if(s>max) 
      max = s;
  }
  for (int g=stree[root].learn+1;g<=stree[root].l;g++)//学习本身 
  {
    co += stree[root].cost[g];
    va += stree[root].value[g];
    for (int k = 0; k <= j - co; k++)
    {
      s=dp(child[root],k)+dp(brother[root],j-co-k)+va;
      if(s>max) 
        max = s;
    }
  }
  return f[root][j] = max;
}

int main()
{
  freopen("skill.out", "w", stdout);
  freopen("skill.in", "r", stdin);
  while (scanf("%d", &n) != EOF)
  {
    set_tree();
    printf("%ld\n", dp(child[0], p));
  }
}
