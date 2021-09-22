//ÂÏÉúÆ¯ÁÁÊı
#include<iostream>
using namespace std;

int a[100]={0};
int b[2]={-1,-1};

int judge(int n)
{    
    int i;
    for(i=0; a[i]>0; i++)
        if(n%a[i]==0)  
          return 0;
    
    a[i]=n;
    return 1;
}

int main()
{
    int i=3,counter=0;
    a[0]=2;
    while(counter<2)
    {
        int n=i;
        if(b[1]-b[0]==1)
        {
            cout<<b[0]<<","<<b[1]<<endl;
            b[0]=b[1];
            counter++;
        }
        else if(b[1]>b[0])   b[0]=b[1];
        
        if(!judge(i))
        {
            int q;
            for(int j=0; a[j]<=n; j++)
            {
                q=0;
                while(n%a[j]==0)
                {
                    q++;
                    n/=a[j];
                }
                if(q==1) break;
            }
            if(q>=2)    b[1]=i;
        }
        i++;
    } 
    //system("pause");
    return 0;
}
