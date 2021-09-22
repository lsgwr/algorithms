//多重背包二进制算法 
#include <iostream>
#include <cstdlib> 
using namespace std;   

int maxV[201]; 
int weight[50]; //记录拆分后物体重量 
int value[50];  //记录拆分后物体价值 
int V, N;   

int main() 
{ 
  freopen("Mbag.in","r",stdin);
  freopen("Mbag.out","w",stdout);      
  int i, j;     
  cin>>V>>N;     
  int weig, val, num;     
  int count = 0;       
  for(i=0; i<N;++i)     
  { 
    cin>>weig>>val>>num;   //输入各物品的重量，价值，数量           
    for(j=1;j<=num; j<<=1) //j左移一位，进行二进制拆分         
    {           
      weight[count] = j*weig;// 乘上相应的二进制系数          
      value[count++] = j * val;             
      num -= j;         
    }         
    if(num > 0)//剩余未拆分的         
    {             
      weight[count] = num * weig;             
      value[count++] = num * val;         
    }     
  }     
    
  for(i = 0; i < count; ++i)  // 使用01背包     
    for(j = V; j >= weight[i]; --j)         
    {             
      int tmp = maxV[j-weight[i]] + value[i];             
      maxV[j] = maxV[j] > tmp ? maxV[j] : tmp;         
    }     
  printf("%d",maxV[V]); 
  return 0;
}
