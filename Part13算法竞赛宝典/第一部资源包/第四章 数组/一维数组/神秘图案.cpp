//神秘图案
#include <bits/stdc++.h>
using namespace std;

int main()
{
  int j = 0;//计数变量
  int index = 0;//找第i+1个洞
  int n = 0;//找的次数
  int s = 1;//间隔数
  int mark[10]= {0}; //记录是否找过
  for (; n<1000; n++)
  {
    mark[index] = 1;
    index+=1+s++;
    index%=10;
  }
  for (j=0; j<10; j++)
    if (mark[j] == 0)
      cout<<j+1<<' ';
  return 0;
}
