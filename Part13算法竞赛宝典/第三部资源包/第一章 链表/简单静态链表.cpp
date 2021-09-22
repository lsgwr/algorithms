// 简单静态链表
#include <iostream>
using namespace std;

struct  student  
{
  long num;
  float score;
  struct student *next; //该指针指向student类型的结构体
};//注意必须有分号

int main()
{
  struct student a,b,c,*head,*p;
  a.num=34341;  a.score=81.5;
  b.num=34343;  b.score=97;
  c.num=34344;  c.score=82;
  head=&a;
  a.next=&b;
  b.next=&c;
  c.next=NULL;
  p=head;
  do  //输出记录 
  {
     cout<<p->num<<" "<<p->score<<endl;
     p=p->next;
  }while(p!=NULL); 
  getchar();
}
