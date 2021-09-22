#include <stdio.h>
#include <malloc.h>
#define M 13

struct node
{
  int key; /*关键字*/
  int si;  /*散列次数*/
  struct node *next;
};

struct hnode
{
  struct node *link;
};

main()
{
   struct hnode LH[M];
   struct node *s,*p;
   int i,d,step,x[M]={87,25,310,8,27,132,68,95,187,123,70,63,47};
   for (i=0;i<M;i++)
     LH[i].link=NULL;
   for (i=0;i<M;i++)
   {
	 step=0;
	 d=x[i] % 13;
	 s=(struct node *)malloc(sizeof(struct node));
	 s->key=x[i];s->next=NULL;
	 if (LH[d].link==NULL)
	 {
	    LH[d].link=s;step++;
	    s->si=step;
	 }
     else
	 {
	   p=LH[d].link;
	   step++;
	   while (p->next!=NULL)
	   {
	      p=p->next;step++;
	   }
	   step++;s->si=step;p->next=s;
	 }
       }
       printf("哈希表:\n");
       step=0;
       for (i=0;i<M;i++)
       {
	 p=LH[i].link;
	 printf("%d:link->",i);
	 while (p!=NULL)
	 {
	   step+=p->si;
	   printf("[%d,%d]->",p->key,p->si);
	   p=p->next;
	 }
	 printf("∧\n");
       }
       printf("\n平均查找长度:ASL=%g\n",1.0*step/M);
    }
