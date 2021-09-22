// 完整链表
#include <bits/stdc++.h>
using namespace std;
typedef int ElemType;
typedef struct List *link;
typedef struct List Lnode;

struct List
{
  ElemType data;
  struct List *next;
};

link setnull(link Head)
{
  link p;
  p=Head;
  while(p!=NULL)
  {
    p=p->next;
    free(Head);
    Head=p;
  }
  return Head;
}

link insert(link Head,ElemType x,int i)
{
  link NewPoint,p=Head;
  int j=1;
  NewPoint=(link)malloc(sizeof(Lnode));
  NewPoint->data=x;
  if(i==1)
  {
    NewPoint->next=Head;
    Head=NewPoint;
  }
  else
  {
    while(j<i-1 && p->next!=NULL)
    {
      p=p->next;
      j++;
    }
    if(j==i-1)
    {
      NewPoint->next=p->next;
      p->next=NewPoint;
    }
    else  printf("插入元素失败,输入的值错误!");
  }
  return Head;
}

link create(link Head)
{
  ElemType newData;
  link NewPoint;

  Head=(link)malloc(sizeof(Lnode));
  printf("请输入链表元素值: \n");
  scanf("%d",&newData);
  Head->data=newData;
  Head->next=NULL;

  while(1)
  {
    NewPoint=(link)malloc(sizeof(Lnode));
    if(NewPoint==NULL)
      break;
    printf("请输入链表元素值: 结束则输入'-1'\n");
    scanf("%d",&newData);
    if (newData==-1)
      return Head;
    NewPoint->data=newData;
    NewPoint->next=Head;
    Head=NewPoint;
  }
  return Head;
}

int lenth(link Head)
{
  int len=0;
  link p;
  p=Head;
  while(p!=NULL)
  {
    len++;
    p=p->next;
  }
  return len;
}

ElemType get(link Head,int i)//函数有bug,能发现吗？ 
{
  int j=1;
  link p;
  p=Head;
  while(j<i && p!=NULL)
  {
    p=p->next;
    j++;
  }
  if(p!=NULL)
    return(p->data);
  else
    printf("输入数据错误!");
  return -1;
}

int locate(link Head,ElemType x)
{
  int n=0;
  link p;
  p=Head;
  while(p!=NULL && p->data !=x)
  {
    p=p->next;
    n++;
  }
  if(p==NULL)
    return -1;
  else
    return n+1;
}

void display(link Head)
{
  link p;
  p=Head;
  if(p==NULL)
    printf("\n链表为空\n");
  else
    do
    {
      printf("%d ",p->data);
      p=p->next;
    }
    while(p!=NULL);
  printf("\n");
}

link connect(link Head1,link Head2)
{
  link p;
  p=Head1;
  while(p->next !=NULL)
  {
    p=p->next;
  }
  p->next=Head2;

  return Head1;
}

link del(link Head,int i)
{
  int j=1;
  link p,t;
  p=Head;
  if(i==1)
  {
    p=p->next;
    free(Head);
    Head=p;
  }
  else
  {
    while(j<i-1 && p->next !=NULL)
    {
      p=p->next;
      j++;
    }
    if(p->next!=NULL && j==i-1)
    {
      t=p->next;
      p->next=t->next;
    }

    if(t!=NULL)
      free(t);
  }
  return Head;
}

int main()
{
  int l;
  link head1;
  link head2;

  head1=create(head1);
  printf("\n链表1为\n");
  display(head1);

  head2=create(head2);
  printf("\n链表2为\n");
  display(head2);

  connect(head1,head2);
  printf("\n链表合并为\n");
  display(head1);
  while(1)
  {
    system("cls");//清屏命令
    display(head1);
    printf("操作菜单：\n");
    printf("  1.输出链表长度\n");
    printf("  2.获取链表第n个元素值\n");
    printf("  3.查找链表中某个元素的位置\n");
    printf("  4.插入某元素到链表\n");
    printf("  5.删除链表中第n个元素\n");
    printf("  6.清空链表\n");
    int Choice,x,y;
    scanf("%d",&Choice);
    switch(Choice)
    {
      case 1:
        printf("链表长度为 %d\n",lenth(head1));
        break;
      case 2:
        printf("请输入要查找的元素值位置\n");
        scanf("%d",&x);
        printf("值为 %d\n",get(head1,x));
        break;
      case 3:
        printf("请输入要查找的元素值\n");
        scanf("%d",&x);
        printf("位置在 %d\n",locate(head1,x));
        break;
      case 4:
        printf("请输入要插入的值和位置\n");
        scanf("%d%d",&x,&y);
        head1=insert(head1,x,y);
        break;
      case 5:
        printf("请输入要删除元素的位置\n");
        scanf("%d",&x);
        head1=del(head1,x);
        break;
      case 6:
        head1=setnull(head1);
    }
    display(head1);
    system("pause");
  }
  return 0;
}


