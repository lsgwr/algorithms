//零和游戏－hash表 
#include <iostream>
#define MAX 1000000000
#define size 20345677
#define key 745
using namespace std;

int n,a[4040],b[4040],c[4040],d[4040],ans;
int hash[size],sum[size];

void Insert(int num)
{
  int tmp=num;
  num=(num+MAX)%size;
  while(hash[num]!=MAX && hash[num]!=tmp)
    num=(num+key)%size;
  hash[num]=tmp;
  sum[num]++;
}
		
int Find(int num)
{
  int tmp=num;
  num=(num+MAX)%size;
  while(hash[num]!=MAX && hash[num]!=tmp)
    num=(num+key)%size;
  if(hash[num]==MAX)
    return 0;
  else
    return sum[num];
}	

int main()
{
  int i,j;
  cin>>n;
  for(i=0;i<n;i++)
    cin>>a[i]>>b[i]>>c[i]>>d[i];
  for(i=0;i<size;i++)
    hash[i]=MAX;
  for(i=0;i<n;i++)//前两列的和 
    for(j=0;j<n;j++)
      Insert(a[i]+b[j]);
  for(i=0;i<n;i++)//后两列和的相反数 
    for(j=0;j<n;j++)
      ans+=Find(-(c[i]+d[j]));
  cout<<ans<<endl;
  return 0;
}
