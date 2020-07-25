//循环队列演示程序 
#include <iostream>
#define MAXN 5 /*队列的最多元素个数*/
using namespace std;

int queue[MAXN];
int Front=0;//头指针
int Rear=0;//尾指针 

void addqueue(int value) //输入value值入队列
{
  if(Front==0 && (Rear+1)%MAXN==MAXN)
    cout<<"队满"<<endl;//元素从未出队情况下的队满 
  else if((Rear+1)%MAXN==Front)
    cout<<"队满"<<endl;//已有元素出队后的情况下的队满 
  else
  { 
    queue[Rear]=value;
    Rear=(Rear+1)%MAXN;
  }  
}

int delqueue()  //输出队列数据
{
  int temp;
  if(Front==Rear)
    cout<<"队列为空"<<endl;
  else
  {
     temp=queue[Front];
     queue[Front]=-1;//取出后该位置设置为-1
     Front=(Front+1)%MAXN;//指向下一位置
     return temp; //输出元素值
  }
}

void display()//显示队列里所有的元素
{
  for(int i = 0 ;i < MAXN; ++ i)
    cout<<queue[i]<<" ";
  cout<<endl;
}

void init()//初始化队列，设定空值为-1*/
{
  for(int i = 0 ;i < MAXN; ++ i)
    queue[i] = -1;
}

int main()
{
  int select,temp;
  init();
  while(1)
  {
    cout<<"1.Input a data\n";
    cout<<"2.Output a data\n";
    cout<<"3.Display the queue\n";
    cout<<"4.Exit\n";
    cin>>select;
    switch(select)
	{
      case 1:cout<<"Please input a value";
             cin>>temp;
             addqueue(temp);
             break;
      case 2:delqueue();
             break;
      case 3:display();
             break;
      case 4:return 0;
    } 
    system("cls");
    display();        
  }
  return 0;
}            
