//压缩数组
#include <iostream>
using namespace std;

int main()
{
  freopen("zip.in","r",stdin);
  freopen("zip.out","w",stdout);
  int N,M,temp,num=0,index=1;
  cin>>N>>M;
  int zip[N*M/3][3];//有效数据不会多于全部元素的三分之一 

  for(int i=0;i<N;++i)
    for(int j=0;j<M;++j)
    {
      cin>>temp;
      if(temp!=0)
      {
        num++;
        zip[index][0]=i;
        zip[index][1]=j;
        zip[index][2]=temp;
index++;
      }  
    }  
  zip[0][0]=N;
  zip[0][1]=M;
  zip[0][2]=num;

  for(int i=0;i<=num;i++)
    cout<<zip[i][0]<<" "<<zip[i][1]<<" "<<zip[i][2]<<endl;
  return 0;  
}
