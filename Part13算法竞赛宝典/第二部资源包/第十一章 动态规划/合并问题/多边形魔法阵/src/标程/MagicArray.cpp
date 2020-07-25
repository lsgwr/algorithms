//多边形魔法阵 
#include <iostream>
#include <cstdlib>
#include <fstream>
#define NMAX 51

std::ifstream cin ("MagicArray.in");
std::ofstream cout("MagicArray.out");

int n;
int arr[NMAX];
char opr[NMAX];
int fmax[NMAX][NMAX],
    fmin[NMAX][NMAX];
bool bmax[NMAX][NMAX],
     bmin[NMAX][NMAX];

int calcmax(int i, int l); 
int calcmin(int i, int l); 

int main(void)
{
  cin >> n;
  for(int i = 0; i < n; i++)
    cin >> arr[i] >> opr[i];
    
  int ans = 0x80000000, t;//初始化为最小值 
  for(int i = 0; i < n; i++)//循环每个节点开始链  
    if((t = calcmax(i, n)) > ans)
      ans = t;
  cout << ans << '\n';
  return 0;
}

//求下标为i开始，长度为l的链最大值 
int calcmax(int i, int l)
{
  if(l <= 1)
    return arr[i];
  if(bmax[i][l])//另用数组记录是否已经计算，防止结果为0导致多次重算 
    return fmax[i][l];//如果已计算就返回  
  int ret = 0x80000000;//初始化为最小值 
  for(int t = 0; t < l - 1; t++)
  {
    int tmp;
    if(opr[(i + t) % n] == '+')
    {//加号只用计算两边最大值 
      tmp = calcmax(i, t + 1) + 
      calcmax((i + t + 1) % n, l - t - 1);
      if(tmp > ret)
        ret = tmp;
    }
    else
    {//乘法对两边最大、最小值求解，统计最大值 
      tmp = calcmax(i, t + 1) *
      calcmax((i + t + 1) % n, l - t - 1);
      if(tmp > ret)
        ret = tmp;
      tmp = calcmax(i,t+1)*calcmin((i+t+1)%n,l-t-1);
      if(tmp > ret)
        ret = tmp;
      tmp = calcmin(i,t+1)*calcmax((i+t+1)%n,l-t-1);
      if(tmp > ret)
        ret = tmp;
      tmp = calcmin(i,t+1)*calcmin((i+t+1)%n,l-t-1);
      if(tmp > ret)
        ret = tmp;
    }
  }
  bmax[i][l] = true;//设置已经计算 
  fmax[i][l] = ret;//保存结果 
  return ret;
}

//求下标为i开始，长度为l的链最小值（总体同上）  
int calcmin(int i, int l)
{
  if(l <= 1)
    return arr[i];
  if(bmin[i][l])
    return fmin[i][l];
  int ret = 0x7FFFFFFF;
  for(int t = 0; t < l - 1; t++)
  {
    int tmp;
    if(opr[(i + t) % n] == '+')
    {
      tmp=calcmin(i,t+1)+calcmin((i+t+1)%n,l-t-1);
      if(tmp < ret)
        ret = tmp;
    }
    else
    {
      tmp = calcmin(i,t+1)*calcmin((i+t+1)%n,l-t-1);
      if(tmp < ret)
        ret = tmp;
      tmp=calcmax(i,t+1)*calcmin((i+t+1)%n,l-t-1);
      if(tmp < ret)
        ret = tmp;
      tmp=calcmin(i,t+1)*calcmax((i+t+1)%n,l-t-1);
      if(tmp < ret)
        ret = tmp;
      tmp=calcmax(i,t+1)*calcmax((i+t+1)%n,l-t-1);
      if(tmp < ret)
        ret = tmp;
    }
  }
  bmin[i][l] = true;
  fmin[i][l] = ret;
  return ret;
}
