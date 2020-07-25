/*堆栈演示!*/
#include <iostream>
using namespace std;
typedef struct Stack *link;
typedef struct Stack Snode;

struct Stack
{
  int data;
  struct Stack *next;
};

link init()//初始化栈
{
  link p;
  p=NULL;
  return p;
}

link push(link Head,int x)//入栈
{
  link p;
  p=new Snode;
  if(p==NULL)
  {
    cout<<"\nMemory Error\n";
    return Head;
  }
  p->data=x;
  p->next=Head;
  return p;
}

link pop(link Head)//出栈
{
  link p;p=Head;
  if(p==NULL)
    cout<<"\nStack is Empty!\n";
  else
  {
    p=p->next;
    delete(Head);
  }
  return p;
}

link setnull(link Head)//释放栈
{
  link p;p=Head;
  while(p!=NULL)
  {
    p=p->next;
    delete(Head);
    Head=p;
  }
  return Head;
}

int lenth(link Head)// 获得栈内元素个数
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

int gettop(link Head)//获得栈顶元素
{
  if(Head==NULL)
  {
    cout<<"\nStack is Empty!\n";
    return -1;
  }
  else
    return Head->data;
}

void display(link Head)//显示栈内元素
{
  link p;p=Head;
  if(p==NULL)
    cout<<"\nStack is empty\n";
  else
    do
    {
       cout<<p->data<<" ";
       p=p->next;
    }while(p!=NULL);
}

int empty(link Head)//判断栈是否为空
{
  if(Head==NULL)
    return 1;
  else
    return 0;
}

int main()
{ 
  int i,x;
  link head1;
  head1=init();
  while(i!=6)
  {
    system("cls");
    cout<<"\n 1.Input a stack data";
    cout<<"\n 2.Output a stack data";
    cout<<"\n 3.Empty or Not";
    cout<<"\n 4.Display a top of stack";
    cout<<"\n 5.Display the lenth of stack";
    cout<<"\n 6.Exit and Free Stack\n";
    cout<<"\n  Stack is:  ";
    display(head1);
    cout<<"\n";

    cin>>i;
    switch(i)
    {
      case 1: while(1)
	          { 	
                system("cls");
		        cout<<"\n -.Input a stack data";
		        cout<<"\n -.Output a stack data";
		        cout<<"\n -.Empty or Not";
		        cout<<"\n -.Display a top of stack";
		        cout<<"\n -.Display the lenth of stack";
		        cout<<"\n -.Exit and Free Stack\n";
		        cout<<"\n  Stack is:  ";
	            display(head1);
		        cout<<"\n";
		        cout<<"\ninput a number: until enter -1:\n";
		        cin>>x;
		        if(x==-1)
		          break;
		        head1=push(head1,x);
	          }
	          break;
     case 2: head1=pop(head1);
	         break;
     case 3: if(empty(head1))
		       cout<<"\nStack is empty\n";
	         else
		       cout<<"\nStack is not empty\n";
             system("pause");
	         break;
     case 4: cout<<"\n The top is  "<<gettop(head1)<<endl;
	         system("pause");
	         break;
     case 5: cout<<"\n The length of stack is "<<lenth(head1)<<endl;
	         system("pause");
	         break;
    }
  }
  system("cls");;
  head1=setnull(head1);
  display(head1);
  getchar();
  return 0;
}
