//数组仿真队列 
#include <iostream>
using namespace std;
int queue[5]={0};
int front =-1;  //头指针 
int rear =-1;   //尾指针 

void addqueue(int value)
{
  if(rear>=5)
    cout<<"The queue is full!\n";
  else
  {
    rear++;
    queue[rear]=value;    
  }       
}

int delqueue()
{
  int temp;
  if(front==rear)
    return -1;
  else
  {
    front++;
    temp=queue[front];
    queue[front]=0;
    return temp;    
  }       
}

void display()
{
  int i;
  for(i=0;i<5;i++)
  {
    if(queue[i]!=0)
      cout<<queue[i]<<' ';                   
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
