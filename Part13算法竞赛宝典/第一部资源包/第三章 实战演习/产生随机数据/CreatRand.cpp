//产生随机数据 
#include <iostream>
using namespace std;    

int main()
{
  freopen("in.txt","w",stdout);//产生的随机数据写入in.txt  
  srand((unsigned)time(NULL)); 
  cout<<rand()%1000<<endl;//随机产生一个数 
}
