//数字三角形－回溯算法 
#include <iostream>
using namespace std;
int n,Max,num[20][20],key[20];//key标记左右，左为1，右为2，0为未走过 

void fun()
{
  int i=1,j=1,s=num[1][1];//准备从最顶点往下走 
  do
  {
    if(i<n)//当没有走到底部时 
      if(key[i]==0)//如果该点没有走过 
      {
        key[i]=1;//标记为左边走过 
        i++;//指针向下移动 
        s=s+num[i][j];//加左下点的数 
      }
      else
        if(key[i]==1)//如果左边已走过 
        {
          key[i]=2;//标记为右边走过 
          i++;//向下移 
          j++;//向右移 
          s=s+num[i][j];//加右下点的数 
        }
        else//如果左下和右下都走过 
        {
          key[i]=0;//恢复为未走过 
          s=s-num[i][j];//往回走，减去最后加的数 
          if(key[i-1]==2)//如果上一点左右均走过 
            j--;//回退时回到左上点 
          i--;//指针上移 
        }
    else
    {
      if(Max<s)
        Max=s;
      s=s-num[i][j];//回退时要减去最后加的值 
      if(key[i-1]==2)//如果这一步是向右走的
        j--;//向左移
      i--;//向上移
    }
  }while(!(i==0));//当没有回溯到最顶点时 
}

void init()
{
  int i,j;
  cin>>n;
  for(i=1;i<=n;i++)
    for(j=1;j<=i;j++)
      cin>>num[i][j];
}

int main()
{ freopen("tower.in","r",stdin);
  freopen("tower.out","w",stdout); 
  init();
  fun();
  cout<<Max<<endl;
  return 0;
}
