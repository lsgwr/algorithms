//数组链表的演示程序(栈优化)
#include<iostream>
#define MAXN 100001
using namespace std;

int linklst_data[MAXN] ;
int linklst_point[MAXN] ;
int stack[MAXN];//用于记录没有加入链表的数组下标的栈
int head = -1;//链表的头指针
int stack_head = 0;//栈顶指针 

void del_by_data(int del_data)//删除一个含有del_data的元素
{
  int p=head,pre=-1;  //从头开始遍历链表
  while(p != -1)
  {
    if(linklst_data[p] == del_data)//找到要删除的值，进行删除操作
	{
	  
	  if(p == head)//如果删除的是头指针，则更新头指针
	    head = linklst_point[head];
	  else//这个else 等同于 if (pre != 1)因为当p!=head时p前面已经有元素了
	    linklst_point[pre] = linklst_point[p];
	  
	  linklst_data[p] = -1;//删除p指向的元素的值
	  linklst_point[p] = -1;
	  stack[--stack_head] = p;//将删除元素的数组下标入栈
	  return ;
	}
      pre = p;
      p = linklst_point[p];
  }
  return ;
}

void add_front(int add_data)//在链表前段加入元素*/
{
  int p = 1;
  p = stack[stack_head++];//直接取出栈中的空位*/
  linklst_data[p] = add_data;//将要加入的节点选好的空位赋值*/
  linklst_point[p] = head; //将当前加入元素的指针指向head*/
  head = p;  //使当前的元素成为链表头指针*/
}

void add_rear(int add_data)//在链表末尾加入元素*/
{
  int p = 1,pre;
  p = stack[stack_head++];//直接取出栈中的空位*/
  linklst_data[p] = add_data;  //对要加入的节点的空位进行赋值*/
  if( head != -1 ) //链表不为空
  {
    pre = head;//找到链表中的最后一个元素*/
    while(linklst_point[pre] != -1)
  	  pre = linklst_point[pre];
    linklst_point[pre] = p;//将当前链表中最后一个元素的指针指向要加入的元素
  }
  else//否则直接对head所指的元素赋值
    head = p;
}

void output()
{
  int p=head;
  while(p != -1)
  {
    cout<<linklst_data[p]<<" ";
    p = linklst_point[p];
  }
  cout<<"\n";
  return ;
}


void init()//初始化数组指针值
{
  for(int i = 0;i < MAXN;i++)
  {
    linklst_point[i] = -1;
    linklst_data[i] = -1;
    stack[i] = i + 1;//从1到MAXN给栈加入数组下标
  }
  return ;
}

int main()//演示链表的操作
{
  int ins,data;
  init();
  while(1)
  { 
    cout<<"1.insert a value in front \n";
    cout<<"2.insert a value in rear \n";
    cout<<"3.delete a value \n";
    cout<<"4.quit \n";
    cin>>ins;
    switch(ins)
	{
  	  case 1:
	    cout<<"please insert a value :";
	    cin>>data;
	    add_front(data);
	    break;
  	  case 2:
	    cout<<"please insert a value :";
	    cin>>data;
	    add_rear(data);
	    break;
	  case 3:
	    cout<<"please insert a value :";
	    cin>>data;
	    del_by_data(data);
	    break;
	  default:
	    return 0;
	}
    system("cls");//此命令只能在window平台下使用 
	output();
 }
 return 0;
}
