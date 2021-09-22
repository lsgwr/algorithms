//凸多边形三角划分
#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <fstream>
#include <iomanip>
using namespace std;
#define NMAX 50

//定义数据类型 
typedef int BN;

int n;
int q[NMAX];//顶点权值 
BN f[NMAX][NMAX]; //f(i, j)从顶点i到j的最小权值和  
short f_last[NMAX][NMAX];//使f(i, j)权值最小的k 
short f_ans[NMAX-2][3];//分割的三角形顶点 
int idx;//当前数组中顶点数 

ifstream fin("Triangle.in");
ofstream fout("Triangle.out");

BN calc(int i, int j);
void prt(int i, int j);
int comp(const void* a, const void* b);

int main()
{
  int i;
  fin >> n;//输入
  for(i = 1; i <= n; ++i)
    fin >> q[i];
  fout << calc(1, n) << "\n";//优化递归求f(1, n) 
  prt(1, n);//递归保存分割结果到f_ans[][3]  
  qsort(f_ans, idx, 3*sizeof(short), comp);//分割结果按字典序排列 
  for(i = 0, --idx; i < idx; ++i)//输出
    fout << f_ans[i][0] << " "
         << f_ans[i][1] << " "
         << f_ans[i][2] << ",";
  fout << f_ans[i][0] << " "
       << f_ans[i][1] << " "
       << f_ans[i][2];
  return 0;
}

BN calc(int i, int j)//求f(i, j)
{
  if(f[i][j] != 0)//优化，防止重复求值 
    return f[i][j];
  if(j - i == 1)//f(i, i+1) = 0
    return 0;
    
  //f(i,j)=Min{f(i,k)+f(k,j)+q[i]*q[k]*q[j]}(0 < i < k < j)
  int k = i + 1;
  BN *p = &f[i][j];
  BN t;
  *p = calc(i, k) + calc(k, j) + (BN)q[i] * q[k] * q[j];
  f_last[i][j] = k;
    
  for(++k; k < j; ++k)
    if((t=calc(i,k)+calc(k,j)+(BN)q[i]*q[k]*q[j])< *p)
    {
      *p = t;
      f_last[i][j] = k;
    }
  return *p;
}

void prt(int i, int j)//打印i ~ j的分割结果
{
  if(j - i == 2)
  {
    //i, j中间只有一个顶点，构成三角形 ，保存到数组 
    f_ans[idx][0] = i;
    f_ans[idx][1] = i + 1;
    f_ans[idx][2] = j;
    ++idx;
  }
  else
  {
    if(f_last[i][j] - i > 1)//i ~ k 的三角形
      prt(i, f_last[i][j]);
    //分割出的三角形  
    f_ans[idx][0] = i;
    f_ans[idx][1] = f_last[i][j];
    f_ans[idx][2] = j;
    ++idx;
        
    //k ~ j 的三角形 
    if(j - f_last[i][j] > 1)
      prt(f_last[i][j], j);
  }
}

//排列分割结果回调函数，按顶点a, b, c从小到大排序 
int comp(const void* a, const void* b)
{
  int ctmp;
  if((ctmp = ((const short*)a)[0] - ((const short*)b)[0]) != 0)
    return ctmp;
  else
  {
    if((ctmp = ((const short*)a)[1] - ((const short*)b)[1]) != 0)
      return ctmp;
    else
      return ((const short*)a)[2] - ((const short*)b)[2];
  }
}
