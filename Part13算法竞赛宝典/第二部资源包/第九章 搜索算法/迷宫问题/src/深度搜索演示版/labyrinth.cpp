//迷宫问题－深度优先算法 
#include <iostream>
using namespace std; 
#define MAX_ROW 5
#define MAX_COL 5

//定义结点为结构体 
struct point 
{ 
  int row, col; 
}stack[512];

//定义地图 
int maze[MAX_ROW][MAX_COL]={0, 0, 1, 0, 0,
	                        0, 1, 0, 1, 1,
	                        0, 1, 0, 0, 0,
	                        0, 1, 0, 1, 0,
	                        0, 0, 0, 1, 0,};

//定义节点的前趋并初始化为-1 
struct point predecessor[MAX_ROW][MAX_COL] = 
{
	{{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}},
	{{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}},
	{{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}},
	{{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}},
	{{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}},
};

int top = 0;//堆栈的指针初始为0 

void push(struct point p)//入栈 
{
  stack[top++] = p;
}

struct point pop(void)//出栈 
{
  return stack[--top];
}

int is_empty()//判断堆栈是否为空 
{
  return top == 0;
}

void visit(int row, int col, struct point pre)
{
	struct point visit_point = { row, col };
	maze[row][col] = 2;//标记为2，即已走过 
	predecessor[row][col] = pre;//标记该节点的前驱 
	push(visit_point);//将该节点压入堆栈 
}

void PrintWay(int x,int y)//输出行走路线，由于是倒推路线，所以需用递归顺序输出 
{
   if(predecessor[x][y].row == -1)
   {
     printf("1 1\n");
     return;
   }
   else
   {
     PrintWay(predecessor[x][y].row,predecessor[x][y].col);
     printf("%d,%d\n", x+1, y+1);  
   }    
}

int main()
{
  struct point p = { 0, 0 }; 
  maze[p.row][p.col] = 2;//标记为2表示该点已被走过 
  push(p);	
  
  while (!is_empty())//当堆栈不为空时 
  {
    p = pop();//出栈 
    if (p.row == MAX_ROW - 1 && p.col == MAX_COL - 1) //如果找到出口就跳出  
      break;
    if (p.row+1 < MAX_ROW && maze[p.row+1][p.col] == 0) //向下试探
      visit(p.row+1, p.col, p);
    if (p.col+1 < MAX_COL  && maze[p.row][p.col+1] == 0)//向右试探
      visit(p.row, p.col+1, p);
    if (p.row-1 >= 0 && maze[p.row-1][p.col] == 0)      //向上试探 
      visit(p.row-1, p.col, p); 
    if (p.col-1 >= 0  && maze[p.row][p.col-1] == 0)     //向左试探 
	  visit(p.row, p.col-1, p); 
  }
  if (p.row == MAX_ROW - 1 && p.col == MAX_COL - 1) 
    PrintWay(p.row,p.col);        
  else
    printf("No path!\n");
  return 0;
}
