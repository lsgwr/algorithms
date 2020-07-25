#include <iostream>
using namespace std;
int upperlim,sum;

void test(int row,int ld,int rd)
{
  int pos,p;
  if(row!=upperlim)//未填满，即不为11111111时
  {
    pos=upperlim & ~(row | ld | rd);//取反后得到所有可以放的位置
    while(pos!=0)
    {
      p=pos & -pos;//取出最右边的1进行尝试
      pos=pos-p;//将该位置的P从pos中移除
      test(row+p,(ld+p)<<1,(rd+p)>>1);
    }  
  }
  else
    sum++;
}

int main()
{
  freopen("queen.in","r",stdin);
  freopen("queen.out","w",stdout);
  int n;
  cin>>n;
  upperlim=(1 << n)-1;//结果为11111111
  test(0,0,0);//纵列，左对角线，右对角线
  cout<<sum<<endl;
  
  //system("pause");
  return 0;
}

