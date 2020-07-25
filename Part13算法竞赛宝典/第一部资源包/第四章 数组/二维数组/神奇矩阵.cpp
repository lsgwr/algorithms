//神奇矩阵 
#include <iostream>
using namespace std;

int main()
{
  int a,b,c,i,j,k,n=0,ary[100];
  int a1,b1,c1,a2,b2,c2,a3,b3,c3;
  for(i=123;i<=987;i++)//找出三数和为15，且三位数各不相同的所有三位数 
  { 
    a=i/100;   //百位 
    b=i/10%10; //十位 
    c=i%10;    //个位 
    if((a+b+c==15)&&(a!=c)&&(a!=b)&&(b!=c)&&(a!=0)&&(b!=0)&&(c!=0))
    {
     ary[n]=i;                                       
     n++;
    }  
  } 
  for(i=0;i<n;i++)//枚举，无需检查同一行三位数之和是否为15 
    for(j=i+1;j<n;j++)//保证三个数依次递增，以防数据重复使用
      for(k=j+1;k<n;k++)
      {
          a1=ary[i]/100; b1=ary[i]/10%10; c1=ary[i]%10;                      
          a2=ary[j]/100; b2=ary[j]/10%10; c2=ary[j]%10;
          a3=ary[k]/100; b3=ary[k]/10%10; c3=ary[k]%10;
            if((a1+a2+a3==15) && (b1+b2+b3==15) && (c1+c2+c3==15) &&
                               a1*b1*c1*a2*b2*c2*a3*b3*c3==362880)             
            { //试所有的行排列组合 
              if((a1+b2+c3)==15 &&(c1+b2+a3==15))//123
                cout<<ary[i]<<endl<<ary[j]<<endl<<ary[k]<<endl<<endl;
              if((a1+b3+c2)==15 &&(c1+b3+a2==15))//132
                cout<<ary[i]<<endl<<ary[k]<<endl<<ary[j]<<endl<<endl;            
              if((a2+b1+c3)==15 &&(c2+b1+a3==15))//213
                cout<<ary[j]<<endl<<ary[i]<<endl<<ary[k]<<endl<<endl;             
              if((a2+b3+c1)==15 &&(c2+b3+a1==15))//231
                cout<<ary[j]<<endl<<ary[k]<<endl<<ary[i]<<endl<<endl;             
              if((a3+b2+c1)==15 &&(c3+b2+a1==15))//321
                cout<<ary[k]<<endl<<ary[j]<<endl<<ary[i]<<endl<<endl;             
              if((a3+b1+c2)==15 &&(c3+b1+a2==15))//312
                cout<<ary[k]<<endl<<ary[i]<<endl<<ary[j]<<endl<<endl;                             
            }             
      }  
   getchar();
   return 0;
}
