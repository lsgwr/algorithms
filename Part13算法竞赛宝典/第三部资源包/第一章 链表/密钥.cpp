/*密钥*/
#include <stdio.h>
struct Node
{
  int data;
  struct Node *next;
};

int main()
{
   struct Node *head,*s,*q,*t;
   int n,m,count=0,i;
   printf("输入猴子个数n m号:");
   scanf("%d,%d",&n,&m);
   for (i=1;i<=n;i++)
   {
     s=(struct Node *)malloc(sizeof(struct Node)); /*建立结点s*/
     s->data=i;
     s->next=NULL;
     if (i==1)  /*建立表头结点*/
     {
       head=s,q=head; /*head作为表头结点,q总是指向链表的最后一个结点*/
     }
     else  /*建立其他结点*/
     {
	   q->next=s;
	   q=q->next;
     }
   }
   q->next=head;  /*建立循环单链表*/
   printf("出队前:");
   q=head;  /*q先指向表头结点*/
   while (q->next!=head)  /*显示出队前的顺序*/
   {
     printf("%d ",q->data);q=q->next;
   }
   printf("%d\n",q->data);
   printf("出队后:");
   q=head;  /*q先指向表头结点*/
   do
   {
     count++;  /*计数器增1*/
     if (count==m-1) /*第i个猴子退出*/
     {
  	    t=q->next;        /*t指向要删除的结点*/
	    q->next=t->next;  /*删除结点t*/
	    count=0;          /*计数器重置0*/
	    printf("%d ",t->data);
     }
	 q=q->next;
   } while (q->next!=q);    /*循环直到只剩一个结点*/
   printf("%d\n",q->data);  /*显示最后的一个结点值*/
   system("pause");
   return 0;
}
