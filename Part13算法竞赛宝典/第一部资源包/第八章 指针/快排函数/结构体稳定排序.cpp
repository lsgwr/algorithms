//结构体排序 
#include <iostream>
using namespace std;

struct node
{
  double date;
  int flag;
}s[10];

int cmp(const void *a,const void *b)//浮点数是不可能相等的 
{
  return (((struct node *)a)->date>((struct node *)b)->date?1:-1);
}

int main()
{
  for(int i=0;i<10;i++)
  {
    s[i].flag=i+1;
    cin>>s[i].date;
  }
  qsort(s,10,sizeof(s[0]),cmp);
  cout<<endl;
  for(int i=0;i<10;i++)
    cout<<s[i].flag<<" "<<s[i].date<<endl;
  system("pause");  
}
