//Æå×ÓÒÆ¶¯ 
#include <iostream>
#include <cstdlib>
using namespace std;

void move(int k)
{
  if(k==4)
  {
    cout<<"4,5-->9,10\n";
    cout<<"8,9-->4,5\n";
    cout<<"2,3-->8,9\n";       
    cout<<"7,8-->2,3\n";
    cout<<"1,2-->7,8\n";
  } 
  else
  {
    cout<<k<<','<<k+1<<"-->"<<2*k+1<<','<<2*k+2<<endl;
    cout<<2*k-1<<','<<2*k<<"-->"<<k<<','<<k+1<<endl;     
    move(k-1);  
  }  
}

int main()
{
  freopen("piece.in","r",stdin);
  freopen("piece.out","w",stdout);  
  int N=0; 
  while(N<4)  
    cin>>N;   
  move(N);
  return 0;
}
