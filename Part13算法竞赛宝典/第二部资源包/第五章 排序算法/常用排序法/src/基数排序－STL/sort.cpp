//基数排序－STL法 
#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <vector> //须加此句 
#include <queue>　//须加此句　 
using namespace std;
vector <int>vec;//存储数列
vector<int>::iterator point;//定义一个指向vector的指针 
queue <int>que[10];//定义桶 
int n,temp,IntMax=0;

int maxbit(int IntMax)//求最大位数
{
  int digit=1;
  while(IntMax>0)
  {
    digit++;
    IntMax/=10;
  }
  return digit-1;
}

void VecToDeque(vector<int>vec,queue<int>deq[],int n)//按某位数将数字入桶
{
  for(point=vec.begin();point!=vec.end();++point)
  {
    int itor=(*point)/n%10; 
    deq[itor].push(*point);//数值入桶 
  }
}

void Colect(vector<int>&vect,queue<int>deq[])//将桶中排好的数输出到容器中
{
  int count=0;
  for(int i=0;i<10;i++)
  {
    while(!deq[i].empty())//如果不为空 
    {
	  vect[count]=deq[i].front();//取获队列第一个值 
      count++;
      deq[i].pop();//出列 
    }
  }
}

void RadixSort(vector<int>&vec)//基数排序 
{
  int count=maxbit(IntMax);
  int power=1;
  for(int i=1;i<=count;i++)
  {
    VecToDeque(vec,que,power);//依次按位分配到桶 
    Colect(vec,que);//整理到数列 
    power*=10;
  }
}

void output()//输出 
{
  for(point=vec.begin();point!=vec.end();++point)
    cout<<*point<<" ";
  cout<<'\n';     
}

int main()
{
  freopen("sort.in","r",stdin);
  freopen("sort.out","w",stdout);  
  cin>>n;  
  for(int i=0;i<n;i++)
  {
    cin>>temp;
    vec.push_back(temp);//vector后端插入数 
    if(temp>IntMax)//找出最大值 
      IntMax=temp;
  }
  RadixSort(vec);
  output();
  return 0;
}
