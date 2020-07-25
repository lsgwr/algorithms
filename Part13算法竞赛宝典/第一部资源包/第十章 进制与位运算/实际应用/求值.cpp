//ÇóÖµ
#include<iostream> 
using namespace std;  
int a,n,m; 

int ans(int mm) 
{     
  if(mm==1)         
    return a;     
  if(mm==0)         
    return 1;     
  int temp=ans(mm/2)%n;     
  if(mm%2==1)         
    return (temp*temp*a)%n;     
  return (temp*temp)%n; 
}  

int main() 
{     
  cin>>a>>m>>n;     
  cout<<ans(m)<<"\n";
  system("pause");
  return 0; 
}
