//链表仿真队列 
#include  <iostream>
using namespace std;
typedef struct QueueNode node;
typedef struct QueueNode *link;
struct QueueNode
{
  int data;
  struct QueueNode *next;
};

link front =NULL;//队头 
link rear =NULL; //队尾 

void addqueue(int value)
{
   link new_node;
   new_node=(link)malloc(sizeof(node));
   new_node->data=value;
   new_node->next=NULL;
   if(front==NULL)  //若为队列第一个元素 
     front=new_node;
   else
     rear->next=new_node;
   rear=new_node;
}   

int delqueue()
{
   link top;
   int temp;
   if(front!=NULL)
   {
     top=front;
     front=front->next;
     temp=top->data;
     free(top);
     return temp;
   }
   else
   {
     rear=NULL;
     return -1;     
   }
}

void display()
{
  link point;
  point=front;   
  while(point!=NULL)
  {
    cout<<point->data<<' ';
    point=point->next;                  
  }
  cout<<endl;
}
   

int main()
{
  int select;
  int i,temp;
  while(1)
  {
    cout<<"1.Input a data\n";
    cout<<"2.Output a data\n";
    cout<<"3.Exit\n";
    cin>>select;
    switch(select)
	{
      case 1:cout<<"Please input a value";
             cin>>temp;
             addqueue(temp);
             break;
      case 2:if(delqueue()==-1)//如果队列是空
               cout<<"The queue is empty!\n";
             else
               cout<<"The out value is "<<temp<<endl;
             break;

       case 3:return 0;
    } 
    system("cls");
    display();        
  }
  return 0;
}            
