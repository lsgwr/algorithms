#include <iostream>
#include <cstdlib> 
#include <deque>

using namespace std;

deque<int> Q;

int Line[4][25];
const int vec[4] = {5, 3, 4, 24};
int pos[1440], solved[300];

int gcd(int i, int j)//最大公约数 
{
  int k;
  for (;j != 0;)
  {
    k = i % j;
    i = j;
    j = k;
  }
  return i;
}

long long work(int n)
{
  int i, j, k;
  long long ans = 1;
  for (i = 0; i < n; ++i)
  {
    for (j = Q[i], k = 1; j != i; j = Q[j], ++k);
    /**表明当前的球经过几次会回到原来的位置，这里用了逆向思维 
       小球如果n次后回到原来的位置，则对于第n天的队列，一个小球在n天前的位置就是那个位置 
       举例小球从1 -> 2 -> 5 -> 6 -> 3 -> 1
        则j会一次变成3,6,5,2,1因为j表示的是这次变化后小球的位置所以1上的球是3。
       也可以定义一个VISIT数组，写成： 
       for(VISIT[i]=true,j=Q[i],k=1;!VISIT[j]&&j!=i;VISIT[j]=true,j=Q[i],++k);
        可以节约不少时间，不过要重置数组 
    */

		ans = ans * k / gcd(ans, k);//球此小球与之前所有小球的周期的最小公倍数 
	}

	Q.clear();

	return ans;//清空队列 
}

int solve(int n)
{
  int i;
  for (i = 0; i < n; ++i)//初始队列 
    Q.push_back(i);

  for (;;)
  {
    Line[0][++Line[0][0]] = Q.front();//用Line[i][0]记录第几行（球槽）已有的球 
    Q.pop_front();
    for (i = 0; i < 3; ++i)//对于一分钟，五分钟，十五分钟的球槽 
    {
      if (Line[i][0] == vec[i])//若本行达到了容纳极限 
      {
		Line[i + 1][++Line[i + 1][0]] = Line[i][Line[i][0]--];//第一颗球滚入下一行 
        while (Line[i][0] != 0)
		  Q.push_back(Line[i][Line[i][0]--]);//剩余的球依次逆序入队列 
      }
    }
    if (Line[3][0] == vec[3])//若24小时到了 
    {
      i = Line[3][0]--;//先记录本球的编号 
      while (Line[3][0] != 0)//其他球依次入队列 
        Q.push_back(Line[3][Line[3][0]--]);
      Q.push_back(Line[3][i]);//最后这颗球滚入 
      break;
    }
  }
  return work(n);
}

int main()
{
  int n;
  freopen("ballclock.in", "r", stdin);
  freopen("ballclock.out", "w", stdout);
  while (cin >> n, n != 0)
  {
    if (solved[n] != 0)///记录一下，万一有相同的输入可以节约时间 
      cout << solved[n];
    else
      cout << (solved[n] = solve(n)) << '\n';
  }
  return 0;
}

