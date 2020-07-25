//对字符串数组排序
#include <iostream>
using namespace std;

int cmp(const void *a,const void *b)
{
  return strcmp((char *)a,(char *)b);
}

int main()
{
  char a[10][5];//字符数组
  for(int i=0;i<10;i++)
    for(int j=0;j<5;j++)
      cin>>a[i][j];
  //qsort(a,10,sizeof(a[0]),cmp);
  qsort(&a[3],5,sizeof(a[3]),cmp);//对部分字符串数组排序 
  cout<<endl;
  for(int i=0;i<10;i++)
  {
    for(int j=0;j<5;j++)
      cout<<a[i][j];
    cout<<endl;  
  }
  system("pause");
  return 0;  
}
