//堆排序 
#include<iostream>
#define MAXN 100
using std::cin;
using std::cout;
int a[MAXN],n,heapsize;

void maxheapify(int i)/*定义根节点*/
{
  int l,r,largest,t;
  /*计算该节点的左右孩子*/
  l=i<<1;    
  r=(i<<1)+1; 
  /*记录最大元素的下标*/   
  if(l<=heapsize and a[i]<a[l])
    largest=l;
  else
    largest=i;
  if(r<=heapsize and a[r]>a[largest])          
    largest=r;
  /*交换最大元素与i的值,并进行递归检查交换后的节点是否满足大根堆性质*/  
  if(largest != i)
  {
    t=a[i];a[i]=a[largest];a[largest]=t;
    maxheapify(largest);
  }
  return ;         
}

void BuildMaxHeap()//建堆 
{
  int i;
  heapsize=n;    
  for(i=n/2;i>=1;i--)
     maxheapify(i);
  return ;       
}

void heapsort()
{
  int i,t;
  BuildMaxHeap();/*建立最大堆*/    
  for(i=n;i>=2;i--)
  {
    t=a[1];a[1]=a[i];a[i]=t;/*将a[i]与a[1]交换*/
    --heapsize; /*剔除交换后的元素a[i]*/               
    maxheapify(1);/*维护最大堆性质*/                
  }    
  return ;      
}

int main()
{
  int i;
  cin>>n;
  for(i=1;i<=n;i++)
     cin>>a[i];
  heapsort();
  for(i=1;i<=n;i++)
     cout<<a[i]<<" ";   
  system("pause");        
  return 0;         
}
