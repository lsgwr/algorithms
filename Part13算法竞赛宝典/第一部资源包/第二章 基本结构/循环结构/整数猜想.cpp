//ÕûÊı²ÂÏë 
#include <iostream>
using namespace std;

int main()
{
 int i;   
 cin>>i;    
 while (i>1)
 {
   cout<<i<<"->";
   if(i%2==0)
     i=i/2;
   else
     i=i*3+1;       
 }
 if(i==1)
   cout<<"1";
 system("pause");
 return 0;  
}
