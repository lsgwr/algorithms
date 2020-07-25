//哈希表
#include <iostream>
using namespace std;
#define M  11
#define N  7

struct hterm
{
  int key;   /*关键字值*/
  int si;    /*散列次数*/
};

struct hterm hashlist[M+1];
int i,address,sum,d,x[N+1];
float average;

int main()
{
  for (i=1;i<=M;i++)  //置初值
  {
    hashlist[i].key=0;
    hashlist[i].si=0;
  }
  x[1]=22;  x[2]=41;   x[3]=53;
  x[4]=46;  x[5]=30;   x[6]=13;
  x[7]=1;  
  for (i=1;i<=N;i++)
  {
    sum=0;
    address=(3*x[i]) % M;
    d=address;
    if (hashlist[address].key==0)//若没有冲突则保存  
    {
      hashlist[address].key=x[i];
      hashlist[address].si=1;
    }
    else  //处理冲突
    {
      do  
      {
		d=(d+(x[i]*7) % 10 +1) % 11;
        sum=sum+1;
      }while (hashlist[d].key!=0);
      hashlist[d].key=x[i];
      hashlist[d].si=sum+1;
    }
  }
  printf("哈希表地址:   ");
  for (i=0;i<M;i++)
    printf("%-4d",i);
  printf("\n");
  printf("哈希表关键字: ");
  for (i=0;i<M;i++) 
    printf("%-4d",hashlist[i].key);
  printf("\n");
  printf("搜索长度:     ");
  for (i=0;i<M;i++) 
    printf("%-4d",hashlist[i].si);
  printf("\n");
  average=0;
  for (i=0;i<M;i++) 
    average=average+hashlist[i].si;
  average=average/N;
  printf("平均搜索长度:ASL(%d)=%g",N,average);
  system("pause");
  return 0;
}
