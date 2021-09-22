//全排列问题-非字典序 
#include <iostream>
#include <cstdio>
#include <cstdlib>
#define Swap(i,j){int t;t=a[i];a[i]=a[j];a[j]=t;}//预定义两数互换的函数 
using namespace std;
int total=0,n;
int a[20]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

void print()
{
  for(int i=0;i<n;i++)
    cout<<a[i];
  cout<<endl; 
}

void permutation(int from,int to)//进一步优化可省略第二个参数 
{
  int i,j;
  if(from==to)  //递归结束 
  {
    print();//打印结果  
    total++;
  }
  else
    for(i=from;i<=to;++i)//（from~to）个元素依次与第1个元素即第from个元素互换 
    {
      Swap(i,from);//互换操作 
      permutation(from+1,to);//递归求（from+1~to）的全排列 
      Swap(i,from);//恢复                  
    }                          
}

int main()
{ 
  freopen("permutation.in","r",stdin);
  freopen("permutation.out","w",stdout);  
  cin>>n;
  permutation(0,n-1);
  cout<<total<<endl;
  return 0;
}
