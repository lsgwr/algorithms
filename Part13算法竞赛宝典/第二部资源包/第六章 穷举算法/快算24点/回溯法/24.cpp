//快算２４点－回溯 
#include "stdio.h"
#define INF 10000000
int a[4][4];//回溯的主体,存放结果 

void put(int k)//打印所需函数 
{
  char c;
  switch(k)
  {
    case 0: c='+';
            break;
    case 1: c='-';
            break;
    case 2: c='*';
            break;
    case 3: c='/';
  }
  printf("%c",c);
  return;
}

int main()
{
  int i,j[3],k[3],l;//i：回溯的层数；j存储的是合并操作的位置；k存储运算符 
  for(i=0;i<4;i++)
    scanf("%d",&a[0][i]);
  for(i=0;i<3;i++)
    j[i]=0,k[i]=-1;
  i=0;
  while(a[3][0]!=24)//a[3][0]是3次合并的最终结果 
  {
    if(i==3)//不满足，往回走 
      i--;
    k[i]++;//换下一运算符 
    if(k[i]==4)//同上 
      j[i]++,k[i]=0;
    if(j[i]==3-i)//同上 
    { 
      j[i]=0,k[i]=-1;i--;
    }
    else
    {
      for(l=0;l<3;l++)//把结果变换到下一层 
      {
        if(l==j[i])//合并位置 
        {
          switch(k[i])//枚举运算符 
          {
            case 0:a[i+1][j[i]]=a[i][j[i]]+a[i][j[i]+1];
                   break;
            case 1:a[i+1][j[i]]=a[i][j[i]]-a[i][j[i]+1];
                   break;
            case 2:a[i+1][j[i]]=a[i][j[i]]*a[i][j[i]+1];
                   break;
            case 3:if(a[i][j[i]+1]!=0 && a[i][j[i]]%a[i][j[i]+1]==0) 
                     a[i+1][j[i]]=a[i][j[i]]/a[i][j[i]+1];
                   else
                     a[i+1][j[i]]=INF;//排除不能整除的数字的干扰 
          }
        }
        else if(l<j[i])
          a[i+1][l]=a[i][l];
        else
          a[i+1][l]=a[i][l+1];
      }
      i++;
    }
    if(i==0 && k[i]==-1)//无解 
      break;
  }
  if(i==0)
    printf("No\n");
  else
    for(i=0;i<3;i++)
    {
      printf("%d",a[i][j[i]]);
      put(k[i]);//打印运算符 
      printf("%d=%d\n",a[i][j[i]+1],a[i+1][j[i]]);
    }
  getchar();
  getchar();
  return 0;
}
