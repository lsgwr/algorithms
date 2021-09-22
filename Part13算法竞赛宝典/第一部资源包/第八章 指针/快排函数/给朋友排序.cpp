//给朋友排序 
#include <fstream>
#include <string>
using namespace std;
int n;

struct TFriend
{
  string name[2];
  int pop;//流行程度 
  int idx;//原始排序的下标       
}fr[50000+10];

int cmp_name(const void *a,const void *b)//以姓名为关键字排序 
{
   TFriend *fr1=(TFriend *)a;//强制转换类型 
   TFriend *fr2=(TFriend *)b;
   if(fr1->name[0]<fr2->name[0])
     return -1;
   if(fr1->name[0]>fr2->name[0])
     return 1;
   return 0;     
}

int cmp_pop(const void *a,const void *b)//pop为第一关键字，idx为第二关键字 
{
   TFriend *fr1=(TFriend *)a;
   TFriend *fr2=(TFriend *)b;
   if(fr1->pop>fr2->pop)//比较第一个关键字 
     return -1;
   if(fr1->pop<fr2->pop)
     return 1;
   if(fr1->idx<fr2->idx)//如第一关键字相同，比较第二个关键字 
     return -1;
   if(fr1->idx>fr2->idx)
     return 1;
   return 0;              
}   

int main()
{
   ifstream fin("friends.in");
   ofstream fout("friends.out");
   int n=0;
   while(fin>>fr[n].name[0]>>fr[n].name[1])//成功读取数据时则循环 
   {
     fr[n].idx=n;
     n++;                                        
   }
   qsort(fr,n,sizeof(fr[0]),cmp_name);
   int st=0;//st记录当前相等区间的起点，0自然是第一个相等区间的起点 
   for(int i=1;i<=n;i++)
   { //扫描完数组或发现与前一个元素不同，则相等区间结束 
     if(i==n ||fr[i].name[0]!=fr[i-1].name[0])
     {
       for(int j=st;j<i;j++)
         fr[j].pop=i-st;
       st=i;//下一个相等区间从i开始          
     }        
   }
   qsort(fr,n,sizeof(fr[0]),cmp_pop);
   for(int i=0;i<n;i++)
     fout<<fr[i].name[0]<<" "<<fr[i].name[1]<<endl;
   return 0;     
}
