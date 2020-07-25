//菜单程序的实现 
#include <iostream>
using namespace std;

int main()
{
   int c;
   int a,b;
   do
   {
     system("cls");//清屏  
     cout<<"小学生算术练习系统\n";
     cout<<"   1.加法\n"; 
     cout<<"   2.减法\n";
     cout<<"   3.乘法\n";
     cout<<"   4.除法\n";
     cout<<"   5.退出\n";
     cout<<"请选择 (1~5):  ";
     cin>>c;
     if(c!=5)
     {
        cout<<"请输入两个数，以空格间隔"; 
        cin>>a>>b;
        switch(c)
        {
           case 1:cout<<a<<"+"<<b<<"="<<a+b<<endl;break;
           case 2:cout<<a<<"-"<<b<<"="<<a-b<<endl;break;
           case 3:cout<<a<<"*"<<b<<"="<<a*b<<endl;break;
           case 4:if(b!=0) 
                    cout<<a<<"/"<<b<<"="<<a/b<<endl;
                  else
                    cout<<"除数不能为0！";
                  break;  
           default:cout<<"错误的输入，请重新选择\n"<<endl;                                       
        }    
        system("pause");
     }
     else
     {
       cout<<"练习结束!\n";
       break;
     }
   }while(1);
   system("pause");
   return 0;  
}
