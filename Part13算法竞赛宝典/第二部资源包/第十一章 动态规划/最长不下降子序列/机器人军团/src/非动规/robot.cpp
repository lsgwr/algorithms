//最长不下降序列-非动规 
#include <iostream>
#include <cstdlib>
#define SIZE 1001
using namespace std;
 
int main()
{
  freopen("robot.in","r",stdin);
  freopen("robot.out","w",stdout);
  int i, j, n, top, temp;
  int stack[SIZE];
  cin >> n;
  top = 0;
  stack[0] = -1;//第一个元素可能为0
  for (i = 0; i < n; i++)
  {
    cin >> temp;
    if (temp > stack[top])//比栈顶元素大数就入栈
      stack[++top] = temp;
    else
    {
      int low = 1, high = top;
      int mid;
      while(low <= high)//二分检索栈中比temp大的第一个数
      {
        mid = (low + high) / 2;
        if (temp > stack[mid])
          low = mid + 1;
        else
          high = mid - 1;
      }
      stack[low] = temp;//用temp替换
    }
  }
  cout << top << endl;//最长序列数就是栈的大小
  return 0;
}
