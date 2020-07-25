//快速模幂-递推 
#include<iostream>
using namespace std;

int a,b,n;
int a_b_mod_n(int a,int b,int n)
{
    int digit[32]={};
    int i,k,result=1;
    i=0;
    //把b化成2进制,这是因为要知道b何时能整除2，并不需要反复进行
    //减一或除二的操作，只需验证b的二进制各位是0 还是1 就可以了，
    //从左至右或从右至左验证都可以，从左至右会更简洁
    while(b)//例如b=103,二进制为1100111,则digit[]存为11100110000......   
    {
       digit[i++]=b%2;
       b>>=1;
    } 
   
    for(k=i-1;k>=0;k--) //计算a^b mod n
    {  
       result=(result*result)%n;
       if(digit[k]==1)
         result=(result*a)%n;
    }
    return result;
}

int main()
{
  freopen("Modulo.in", "r", stdin);
  freopen("Modulo.out","w",stdout);    
  cin>>a>>b>>n;
  cout<<a_b_mod_n(a,b,n); 
  return 0;   
}
