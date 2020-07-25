//执行任务 
#include<iostream>
using namespace std;

int main()
{
  int a,b,c,d,e,f;
  for(a=1;a>=0;a--)//穷举每个人是否去的所有情况
    for(b=1;b>=0;b--)       //1:去  0:不去
      for(c=1;c>=0;c--)
        for(d=1;d>=0;d--)
          for(e=1;e>=0;e--)
            for(f=1;f>=0;f--)
               if(a+b>=1&&a+d!=2&&a+e+f==2&&
               (b+c==0||b+c==2)&&c+d==1&&(d+e==0||d==1))
               {
                cout<<"a:"<<a<<endl;
                cout<<"b:"<<b<<endl;
                cout<<"c:"<<c<<endl;
                cout<<"d:"<<d<<endl;
                cout<<"e:"<<e<<endl;
                cout<<"f:"<<f<<endl;                                                                
               }
  system("pause");
  return 0; 
}	
