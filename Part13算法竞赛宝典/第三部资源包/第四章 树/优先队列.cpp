//优先队列 
#include<iostream>
#include<windows.h>
#define MAXH 100000/*堆中最大元素数量 + 1*/
#define INF 0xffffff
using std::cin;
using std::cout;

int n,heapsize;/*堆的大小*/
int a[MAXH];/*堆中元素的值*/

void maxheapify(int i)//维护最大堆
{
  int l,r,largest,t;
  
  l=i<<1; //计算该节点的左右孩子 
  r=(i<<1)+1;    
  
  if(l<=heapsize && a[i]<a[l])//记录最大元素的下标
    largest=l;
  else
    largest=i;
  if(r<=heapsize && a[r]>a[largest])          
    largest=r; 
  //交换最大元素与i的值,并进行递归检查交换后的节点是否满足最大堆性质
  if(largest != i)
    {
      t=a[i];a[i]=a[largest];a[largest]=t;
      maxheapify(largest);
    }
  return ;         
}

void BuildMaxHeap()//建立最大堆
{
  int i;
  heapsize=n;    
  for(i=n/2;i>=1;i--)
    maxheapify(i);
  return ;       
}

int ExtractMax(int a[])//弹出并返回堆中最大元素
{
  int max;   
  max=a[1];
  a[1]=a[heapsize]; 
  a[heapsize]=-1;  
  heapsize--;
  maxheapify(1);//维护弹出后堆的性质   
  return max;    
}

void HeapIncreaseKey(int i,int key)//改变节点当前的值
{
  int t;
  if(a[i]>key)//更改的值必须要大于当前的值
  {
    cout<<"CURRENT VERX BIGGER THAN KEY\n";
    return;
  }
  a[i]=key;   
  while(i>1 and a[i>>1]<a[i])/*旋转并保持堆的性质*/
  {
    t=a[i];a[i]=a[i>>1];a[i>>1]=t;i=i>>1;
  }   
  return ;    
}

void MaxHeapInsert(int key)//往堆中插入一个元素
{
  heapsize++;
  a[heapsize]=-INF;//初始化插入元素
  HeapIncreaseKey(heapsize,key);//插入元素
  return;   
}

int main()//演示操作
{
  int i,key,v;       
  cin>>n;
  for(i=1;i<=n;i++)        
    cin>>a[i];     
  BuildMaxHeap();//构建堆 
   
  while(1)
  { 
    system("cls");    
    cout<<"Please Insert A Instruction:\n";
    cout<<"1.Show max verx\n";//查找最大优先值 
    cout<<"2.push max verx\n";//最大优先值出列 
    cout<<"3.Change verx\n";//改变某元素的优先值 
    cout<<"4.Insert new Verx\n";//插入新的元素 
    cout<<"Other.Exit\n";  
    Gout(1,40,6,0,20);  
    cin>>i;
    if(i==1)
	  cout<<a[1]<<"\n";
    else if(i==2)
	  cout<<ExtractMax(a)<<"\n";
    else if(i==3)
    {
      cin>>v>>key;
      HeapIncreaseKey(v,key);
    }
    else if(i==4)
    {
      cin>>key;
      MaxHeapInsert(key);
    }
    else
	  break;    
    getchar();    
  }
}
