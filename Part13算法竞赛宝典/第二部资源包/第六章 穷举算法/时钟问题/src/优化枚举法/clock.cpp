#include <cstdio>
using namespace std;
/*  
Clock[i][j]记录第i行第j列的时钟需转几次才能成功
即读入 一个x Clock[i][j] = (4 - x) % 4
Map[i][j] 记录第 (i - 1) * 3 + j 的方案用了几次
*/
int Clock[4][4], Map[4][4];

int Order(int k)//统一变成范围内值的函数
{
  int c = k;
  while (c < 0)
  {
    c += 4;
  }
  while (c > 4)
  {
    c -= 4;
  }
  return c;
}

int main()
{
  freopen("clock.in", "r", stdin);
  freopen("clock.out", "w", stdout);
  for (int i = 1; i <= 3; ++i)
  {
    for (int j = 1; j <= 3; ++j)
    {
      scanf("%d", &Clock[i][j]);
      Clock[i][j] = (4 - Clock[i][j]) % 4;
    }
  }
  for (Map[1][1] = 0; Map[1][1] <= 3; ++Map[1][1])
  {
    for (Map[1][2] = 0; Map[1][2] <= 3; ++Map[1][2])
    {
      for (Map[1][3] = 0; Map[1][3] <= 3; ++Map[1][3])
      {
        //枚举前3个方案的使用次数
        Map[2][1] = Order(Clock[1][1] - Map[1][1] - Map[1][2]);
        Map[2][3] = Order(Clock[1][3] - Map[1][2] - Map[1][3]);
        Map[2][2] = Order(Clock[1][2] - Map[1][1] - Map[1][2] - Map[1][3]);
        Map[3][1] = Order(Clock[2][1] - Map[1][1] - Map[2][1] - Map[2][2]);
        Map[3][3] = Order(Clock[2][3] - Map[1][3] - Map[2][2] - Map[2][3]);
        Map[3][2] = Order(Clock[3][2] - Map[3][1] - Map[3][3] - Map[2][2]);
        if ((Map[2][1]+Map[3][1]+Map[3][2])%4==Clock[3][1] &&
           (Map[2][3]+Map[3][2]+Map[3][3])%4==Clock[3][3] && 
           (Map[2][2]+Map[1][1]+Map[1][3]+Map[3][1]+Map[3][3])%4==Clock[2][2])
        {
          for (int i = 1; i <= 3; ++i)
          {
            for (int j = 1; j <= 3; ++j)
            {
              for (int k = 1; k <= Map[i][j]; ++k)
              {
                printf("%d", (i - 1) * 3 + j);
              }
            }
          }//输出方案，只要求一组
          printf("\n");
          return 0;
        }
      }
    }
  }
  printf("No Answer !\n");
  return 0;
}
