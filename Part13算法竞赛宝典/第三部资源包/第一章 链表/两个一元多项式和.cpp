//两个一元多项式之和 
#include <stdlib.h>
#include <stdio.h>
typedef struct List *Link;
typedef struct List Lnode;

struct List
{
  int coef;//系数 
  int expn;//幂 
  struct List *next;
};

Link create()
{
  int a,n,i=1;
  Link Head,s,p;
  Head=(Link)malloc(sizeof(Lnode));
  Head->next=NULL;
  p=Head;
  do  
  {
    printf("\n第 %d 次->系数,幂:",i++);
    scanf("%d %d",&a,&n);
    if(a!=0 ||n!=0)
    {
      s=(Link)malloc(sizeof(Lnode));       
      s->coef=a;s->expn=n;s->next=NULL;
      p->next=s;p=s;
     
    }
  }while(a!=0 ||n!=0);
  printf("\n");
  return(Head->next);   
}

Link add(Link pa,Link pb)
{
  int n;
  Link pc,s,p;
  pc=(Link)malloc(sizeof(Lnode));
  pc->next=NULL;
  p=pc;
  while(pa!=NULL && pb!=NULL)//当两个链表都没有到末尾时 
  {
    if(pa->expn>pb->expn)     //如a的幂大于b的幂 
    {
      s=(Link)malloc(sizeof(Lnode));
      s->coef=pa->coef;s->expn=pa->expn;
      s->next=NULL;p->next=s;p=s;
      pa=pa->next;
    }
    else if(pa->expn<pb->expn)//如a的幂小于b的幂
    {
      s=(Link)malloc(sizeof(Lnode));
      s->coef=pb->coef;s->expn=pb->expn;
      s->next=NULL;p->next=s;p=s;
      pb=pb->next;    
    }
    else                     //如a的幂等于b的幂
    {
      n=pa->coef+pb->coef;
      if(n!=0)
      {
         s=(Link)malloc(sizeof(Lnode));
         s->coef=n;s->expn=pb->expn;s->next=NULL;
         p->next=s;p=s;
      }
      pa=pa->next;pb=pb->next;      
    }
  }                 
  while(pa!=NULL)    //当a还有余项时 
  {
    s=(Link)malloc(sizeof(Lnode));
    s->coef=pa->coef;s->expn=pa->expn;
    s->next=NULL;p->next=s;
    p=s;pa=pa->next;
  }             
  while(pb!=NULL)　 //当b还有余项时 
  {
    s=(Link)malloc(sizeof(Lnode));
    s->coef=pb->coef;s->expn=pb->expn;
    s->next=NULL;p->next=s;
    p=s;pb=pb->next;               
  } 
  return(pc->next);
}

void display(Link Head)
{
  int first=1;   
  Link p=Head;
  while(p!=NULL)
  { 
    if(first)  //显示第一个结点时,不显示+号 
    { 
      if(p->expn==1)
        printf("%dx",p->coef);
      else if(p->expn==0)
        printf("%d",p->coef);
      else                       
        printf("%d^%d",p->coef,p->expn);
      first=0;  
    }  
    else  //显示其他结点时,显示+号 
    {
      if(p->expn==1)
        printf("+%dx",p->coef);
      else if(p->expn==0)
        printf("+%d",p->coef);
      else
        printf("+%dx^%d",p->coef,p->expn);       
    }
    p=p->next;
  }  
  printf("\n"); 
}

int main()
{
  Link Link1,Link2,Link3;
  printf("\n建立第一个一元多项式(以0 0标志结束):\n");
  printf("要求:1.按幂从大到小次序输入各结点.\n");
  printf("     2.没有两个结点具有相同的幂.\n"); 
  Link1=create();

  printf("\n建立第二个一元多项式(以0 0标志结束):\n");
  printf("要求:1.按幂从大到小次序输入各结点.\n");
  printf("     2.没有两个结点具有相同的幂.\n");     
  Link2=create();
  
  Link3=add(Link1,Link2);
  printf("第一个一元多项式为:");
  display(Link1);
  printf("第二个一元多项式为:");
  display(Link2);
  printf("\n相加后的一元多项式为:");
  display(Link3);
  system("pause");  
}
