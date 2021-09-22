//火星人问题－ＳＴＬ法 
#include<iostream> 
#include<algorithm>//必须加上此句 
using namespace std; 
int n,m,martian[10001]; 

void print()  
{
  for(int i=0;i<n-1;i++) 
    cout<<martian[i]<<' '; 
  cout<<martian[n-1]<<"\n";   
}

int main()   
{  
  freopen("martian.in","r",stdin);
  freopen("martian.out","w",stdout);  
  cin>>n>>m;
  for(int i=0;i<n;i++)
    cin>>martian[i];
  for(int i=1;i<=m;i++)//往后数m个排列 
    next_permutation(martian,martian+n);     
  print();
  return 0; 
}
