//Ô¤Ëã£­Ì°ÐÄ·¨ 
#include <stdio.h>
#include <stdlib.h>

struct dot
{
  double dis, p;
}dots[100001];

double have = 0, used = 0;
double d1, c, d2, p;
int place = 0;
 
int com(const void *a, const void *b)
{
  struct dot i = *(struct dot *)a, j = *(struct dot *)b;
  return i.dis - j.dis;
}
 
void driveto(int id)
{
  place = id;
}
 
int main()
{
  freopen("budget.in","r",stdin);
  freopen("budget.out","w",stdout);  
  int i;
  int n, id;
  double small;
  scanf("%lf%lf%lf%lf%d", &d1, &c, &d2, &p, &n);
  dots[0].p = p;
  dots[n + 1].dis = d1;
  for(i = 1; i <= n; i++)
  {
    scanf("%lf%lf", &dots[i].dis, &dots[i].p);
  }
  qsort(dots, n + 2, sizeof(struct dot), com);
  while(dots[place].dis < d1)
  {
    id = -1;
    for(i = place + 1; i <= n + 1; i++)
    {
      if(dots[place].dis + c * d2 >= dots[i].dis)
      {
        if(dots[i].p <= dots[place].p)
        {
          id = i;
          break;
        }
      }
      else
        break;
    }
    if(i == place + 1 && id == -1)
    {
      printf("No Solution\n");
      return 0;
    }
    if(id != -1)
    {
      if(dots[place].dis + have * d2 >= dots[id].dis)
      {
        have -= (dots[id].dis - dots[place].dis) / d2;
      }
      else
      {
        used += ((dots[id].dis - dots[place].dis) / d2 - have) * dots[place].p;
        have = 0;
      }
    }
    else
    {
      small = 100000000;
      id = -1;
      for(i = place + 1; i <= n + 1; i++)
      {
        if(dots[place].dis + c * d2 >= dots[i].dis)
        {
          if(small >= dots[i].p)
          {
            small = dots[i].p;
            id = i;
          }
        }
        else
          break;
      }
      used += (c - have) * dots[place].p;
      have = c;
      have -= (dots[id].dis - dots[place].dis) / d2;
    }
    place = id;
  }
  printf("%.2lf\n", used);
  return 0;
}
