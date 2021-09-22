//星际争霸1
#include <bits/stdc++.h>
using namespace std;

int FindFather(int father[], int pos)//寻找第i号战舰所在的整个战舰队列的头
{
  return father[pos] < 0? pos:FindFather(father, father[pos]);
}

void Unite(int father[], int posI, int posJ)//合并指令
{
  int fI = FindFather(father, posI); //各自去寻找所在的整个战舰队列头
  int fJ = FindFather(father, posJ);
  //第i号战舰队列，作为一个整体接至第j号战舰队列的尾部
  int rear = -father[fJ]; //posJ所在队列的队尾
  father[fJ] = father[fI];
  father[fI] = rear;
}

int Search(int father[], int posI, int posJ)//查询指令
{
  int fI = FindFather(father, posI); //posI所在队列的队头
  int rear = -father[fI];           //posI所在队列的队尾
  while (rear != posI && rear != posJ) //寻找posI或posJ在队列中的位置
    rear = father[rear];
  if (rear == posJ)//如果先找到posJ，则设posJ = posI，继续寻找posJ
    posJ = posI;
  int len = -1;
  while (rear != father[fI] && rear != posJ)//寻找posJ在队列中的位置
  {
    len++;
    rear = father[rear];
  }
  //如果第i号战舰与第j号战舰当前不在同一列上，则输出-1
  if (rear != posJ)
    len = -1;
  return len;
}

int main()
{
  const int MAX = 30000;
  int *father = new int[MAX+1];
  for (int i=0; i<=MAX; i++) //所有的数组元素值均初始化为自身序号的相反数
    father[i] = -i;//father[i]  为自己的父亲 ，而根的father为尾结点
  int T;
  cin >> T;
  char ch;
  int  posI, posJ;
  for (int i=0; i<T; i++)
  {
    cin >> ch;
    cin >> posI >> posJ;
    if (ch == 'M')
      Unite(father, posI, posJ);
    else
      cout << Search(father, posI, posJ) << endl;
  }
  delete []father;//释放father内存空间 
  return 0;
}

