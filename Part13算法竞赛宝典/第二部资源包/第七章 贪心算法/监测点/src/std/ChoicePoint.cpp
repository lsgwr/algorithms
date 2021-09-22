/*
先按右端点由小到大排序，相等的话左端点由大到小(否则会少算) 
*/
#include <iostream>
#include <cstdlib>
#include <cstring>
using namespace std;

const int N = 10000;

typedef struct Node 
{
    int a, b;
};
Node q[N];//不可直接把q[N]定义在typedef后边 

int cmp(const void *a, const void *b)
{
  Node *c = (Node *)a;
  Node *d = (Node *)b;
  if(c->b == d->b)
    return c->a < d->a;
  return c->b > d->b;    
}

int cnt = 0;

int main()
{
  int i,j,k;
  int T;
  cin>>T;
  int n;
  while(T--)
  {
    memset(q,0,sizeof(q));
    cin>>n;
    for(i=0; i<n; i++)
      cin>>q[i].a>>q[i].b;
    qsort(q,n,sizeof(Node),cmp);
    int end = -1;//坐标全是正数 
        
    for(i=0; i<n; i++)
      if(end < q[i].a)
      {
        end = q[i].b;
        cnt++;
      }
    cout<<cnt<<endl;
  }
  return 0;  
}
