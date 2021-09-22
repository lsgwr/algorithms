//队列-求迷宫问题
#include <iostream>
#define m 5  /*行数*/
#define n 5  /*列数*/
#define MaxSize 100
using namespace std;

int zx[9]={0,-1,-1,0,1,1,1,0,-1};
int zy[9]={0,0,1,1,1,0,-1,-1,-1};  
int mg[m+2][n+2]={1,1,1,1,1,1,1,
                  1,0,0,0,1,0,1,
                  1,0,1,0,0,0,1,
                  1,0,0,1,0,1,1,
                  1,1,1,0,1,0,1,
                  1,0,1,1,1,0,1,
                  1,1,1,1,1,1,1};
struct stype
{
  int x,y,pre;
}queue[MaxSize];

void printlj(int rear)//打印路径 
{
  int i,j;
  i=rear;
  do
  {
    j=i;
    i=queue[i].pre;
    queue[j].pre=-1;
  } while (i!=0);
  printf("\n迷宫路径:\n\t入口->");
  i=0;
  while (i<MaxSize)
  {
	if (queue[i].pre==-1)
	   printf("(%d,%d)->",queue[i].x,queue[i].y);
	i++;
  }
  printf("出口\n");
}

void mglj()
{
  int i,j,x,y,v,front,rear,find=0,no=1;
  queue[1].x=1;queue[1].y=1;queue[1].pre=0;

  front=1;rear=1; 
  mg[1][1]=-1;    
  printf(" 步号  x   y  pre\n");
  printf("%4d%4d%4d%4d\n",no++,queue[rear].x,queue[rear].y,queue[rear].pre);
  while (front<=rear && !find)  
  {
	x=queue[front].x; y=queue[front].y;
	for (v=1;v<=8;v++)   
	{
	  i=x+zx[v];j=y+zy[v]; 
	  if (mg[i][j]==0)    
	  {
	     rear++;   
	     queue[rear].x=i;
	     queue[rear].y=j;
	     queue[rear].pre=front;  
	     mg[i][j]=-1; 
	     printf("%4d%4d%4d%4d\n",no++,queue[rear].x,queue[rear].y,queue[rear].pre);
  	  }
	  if (i==m && j==n) /*找到了出口*/
	  {
	    printlj(rear);  /*打印找到的路径*/
	    find=1;         /*设置为1时便于退出循环*/
	  }
    }
    front++;  
  }
  if (!find) printf("不存在路径!\n");
}

int main()
{
  mglj();  /*产生迷宫路径*/
  getchar();
}
