//多边形魔法阵 
#include <fstream>
#include <iostream>

//为方便起见，预定义四个常量
#define additionSymbol '+'
#define additionNumber 0
#define multiplySymbol 'x'
#define multiplyNumber 1

int max(int x, int y)
{
  return x > y? x : y;
}

int min(int x, int y)
{
  return x < y? x : y;
}

using std::fstream;

fstream fin, fout;
typedef bool symbol;

int part;
int num[50];
symbol opt[50];
//Act[i][j] 指从第j个开始运算i个操作符的答案,maxAct为最大值,minAct为最小值 
int maxAct[50][50], minAct[50][50];
/* 
* Action (x1, y1, x2, y2)
(x1, y1)指左边的操作,对y1~x1+y1/ *即从y1数过x1个操作符* /进行操作,同理(x2,y2) 
*/
int maxAddAction(int x1, int y1, int x2, int y2);
int minAddAction(int x1, int y1, int x2, int y2);
int maxMulAction(int x1, int y1, int x2, int y2);
int minMulAction(int x1, int y1, int x2, int y2);

int (*maxAction[2])(int x1, int y1, int x2, int y2);
int (*minAction[2])(int x1, int y1, int x2, int y2);

int main(int argc, char* argv[])
{
  bool fopen(void);
  bool read(void);
  bool solve(void);
  bool write(void);
  fopen() && read() && solve() && write();
  return 0;
}

bool fopen(void)
{
  fin.open("MagicArray.in", std::ios::in);
  fout.open("MagicArray.out", std::ios::out);
  return bool(fin) && bool(fout);
}

bool read(void)
{
  bool Ok = true;
  int i;
  fin >> part;
  bool readNum(int& ptr);
  bool readOpt(symbol &Opt);
  for (i = 0; i < part; ++i)
    Ok &= readNum(num[i]) && readOpt(opt[i]);
  return Ok;
}

bool readNum(int& ptr)
{
  return fin >> ptr;
}

bool readOpt(symbol& Opt)
{
  char Sym;
  fin >> Sym;
  // 把读入的符号转化为数字储存。 
  switch (Sym)
  {
    case additionSymbol: Opt = additionNumber; break;
    case multiplySymbol: Opt = multiplyNumber; break;
    default: return false;
  }
  return true;
}

bool solve(void)
{
  int i, j, k;
  // 不进行运算当然是原值 
  for (i = 0; i < part; ++i)
  {
    maxAct[0][i] = num[i];
    minAct[0][i] = num[i];
  }
  const int index(const int _index);
  maxAction[additionNumber] = maxAddAction;
  maxAction[multiplyNumber] = maxMulAction;
  minAction[additionNumber] = minAddAction;
  minAction[multiplyNumber] = minMulAction;
  // action(left, right) 指从left到right的任意操作，不一定满足 left < right
  for (i = 1; i < part; ++i)/// 在一段数中共包含i个运算符 
	for (j = 0; j < part; ++j)/// _left = index(j), _right = index(i + j)
	{
	  maxAct[i][j] = 0x80000000;
	  minAct[i][j] = 0x3FFFFFFF;
	  //leftPart = action(_left, j + k), rightPart = 
      //action(index(j + k + 1), _right)， 即k指左边部分包含k个运算符。
      for (k = 0; k < i; ++k) 
	  {
	    maxAct[i][j]=max(maxAct[i][j], maxAction[opt[index(j + k)]](k, j, i - k - 1, index(j + k + 1)));
	    minAct[i][j]=min(minAct[i][j], minAction[opt[index(j + k)]](k, j, i - k - 1, index(j + k + 1)));
	  }
	}
  return true;
}

const int index(const int _index)/// @remark index < 2 * part
{
  return _index < part? _index : _index - part;
}

bool write(void)
{
  int maxsolve = 0x80000000;
  for (int i = 0; i < part; ++i)
    maxsolve = maxsolve > maxAct[part - 1][i]? maxsolve : maxAct[part - 1][i];
  fout << maxsolve << std::endl;
  return true;
}

int maxAddAction(int x1, int y1, int x2, int y2)//加法 
{
  return maxAct[x1][y1] + maxAct[x2][y2];
}

int minAddAction(int x1, int y1, int x2, int y2)
{
  return minAct[x1][y1] + minAct[x2][y2];
}

// 乘法，直接把四种结果取最大（最小） 
int maxMulAction(int x1, int y1, int x2, int y2)
{
  return max(max(maxAct[x1][y1] * maxAct[x2][y2],maxAct[x1][y1] * minAct[x2][y2]),
	max(minAct[x1][y1] * maxAct[x2][y2], minAct[x1][y1] * minAct[x2][y2]));
}

int minMulAction(int x1, int y1, int x2, int y2)
{
  return min(min(maxAct[x1][y1] * maxAct[x2][y2],maxAct[x1][y1] * minAct[x2][y2]),
	min(minAct[x1][y1] * maxAct[x2][y2], minAct[x1][y1] * minAct[x2][y2]));
}
