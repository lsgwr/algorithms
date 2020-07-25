//判断回文数
#include <iostream>
using namespace std;
#define MAXCHAR 40
struct node
{
  char data;
  struct node *next;
};

int ishs(struct node *head,int n)
{
  char stack[MAXCHAR/2];
  struct node *p=head;
  int top=0;
  while (top<n/2)   /*将前半部分元素入栈*/
  {
    stack[top]=p->data;
	top++;p=p->next;
  }
  if (n%2==1)  /*n为奇数*/
    p=p->next;
    top--;
  while (top>=0 && p!=NULL && stack[top]==p->data)  /*边退栈边比较*/
  {
    top--;p=p->next;
  }
  if (top==-1 && p==NULL)  /*若栈空且链表比较完毕,则是回文数*/
    return(1);
  else
    return(0);    /*否则不是回文数*/
  }
 
int main()
{
  char s[MAXCHAR];
  struct node *head=NULL,*p,*q;
  int i=0;
  printf("输入一个数:");
  scanf("%s",s);
  while (s[i]!='\0')  /*建立字符串的单链表*/
  {
	p=(struct node *)malloc(sizeof(struct node));
	p->data=s[i];p->next=NULL;
	if (head==NULL)
	{
	   head=p;q=p;  /*q总是指向最后一个结点*/
	}
	else
	{
	  q->next=p;q=p;
	}
	i++;  /*i为head链表的长度*/
  }
  if (ishs(head,i))
    printf("%s是回文数\n",s);
  else
    printf("%s不是回文数\n",s);
  system("pause");  
}
