//猫和老鼠
#include <cstdio>
#include <iostream>
#include <cstdlib>
using namespace std;

struct Node
{
	int x, y;//坐标
	char pos;//方向
};
char array[10][11];

void MoveNext(struct Node* who)//方向的改变或者坐标的改变
{
  switch (who->pos)//获取当前方位，决定下一步走向 
  {
	case 'N': 
      if (who->x == 0 || array[who->x - 1][who->y] == '*')
	    who->pos = 'E';
	  else
	    who->x--;
	  break;
	case 'S': 
      if (who->x == 9 || array[who->x + 1][who->y] == '*')
		who->pos = 'W';
      else
        who->x++;
	  break;
	case 'E': 
      if (who->y == 9 || array[who->x][who->y + 1] == '*')
		who->pos = 'S';
      else
        who->y++;
	  break;
	case 'W': 
      if (who->y == 0 || array[who->x][who->y - 1] == '*')
		who->pos = 'N';
      else 
        who->y--;
      break;
	}
}

int main()
{
  freopen("catmouse.in", "r", stdin);
  freopen("catmouse.ans", "w", stdout);
  int N, i, j, k, count;
  struct Node Mouse, Cat;
  scanf("%d", &N);
  gets(array[0]);//获取多余的回车符 
  for (k = 0; k < N; k++)
  {
    for (j = 0; j < 10; j++)
      gets(array[j]);
    for (i = 0; i < 10; i++)////读图，获取两人所在的位置并标记 
      for (j = 0; j < 10; j++)
      {
		if (array[i][j] == 'C') 
		{
		  Cat.x = i;
		  Cat.y = j;
		  Cat.pos = 'N';
		}
		else if (array[i][j] == 'M')
		{
		  Mouse.x = i;
		  Mouse.y = j;
		  Mouse.pos = 'N';
		}
      }
    count = 0;
    //一百步之内或者未找到则继续找
    while (count < 100 && (Mouse.x != Cat.x || Mouse.y != Cat.y))
    {
      MoveNext(&Mouse);
      MoveNext(&Cat);
      count++;
    }
    if(Mouse.x == Cat.x && Mouse.y == Cat.y)//到输出步数
      printf("%d\n", count);
    else//未找到
      printf("-1\n");
  }
  return 0;
}
