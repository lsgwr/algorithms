//求10-500间的全部素数，方法是使用嵌套for循环处理
# include <iostream>
# include <math.h>
using namespace std;
int main()
{	
 int number,i,n=0;
 for(number=11;number<=500;number+=2)
 {
   for(i=2;i<=sqrt(number);i++) 
      if(number%i==0) break; 
   if(i>sqrt(number))  
   {
      cout<<number<<" ";
      n++;   //n为输出数字累计值
      if(n%10==0) 
         cout<<"\n";  //一行输出10个数据即换行
   }//end if
 }//end for
 system("pause");
 return 0;
}
