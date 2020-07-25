#include<iostream>
#include<fstream>
#include<iomanip>
#define  BASE 10000
using namespace std;

ifstream fin("horse.in");
ofstream fout("horse.out");

int main()
{
    int dx[4]={1,2,2,1};  //设立马走的方向 
    int dy[4]={-2,-1,1,2};
    int x,y,i,j,xa,jj,kk,ak,tempx,tempy,len;
    fin>>x>>y;
    y=y/2;
    int a[3][y+1][500];
    memset(a,0,sizeof(a));
    a[0][y][0]=1;
    a[0][y][1]=1;
    kk=0;
    xa=x;
    tempy=-1;
//------------------------------------------------------------------------------------------------
    while(kk<xa)
    {
        jj=-1;
        ak=kk%3;

        while(jj<y)
         {
           ++jj;
           if(a[ak][jj][0]!=0)
             for(i=0;i<=3;++i)
              {
                 if(jj+dy[i]>=0) 
                  {  if(jj+dy[i]<=y)       tempy=jj+dy[i];
                     else if(jj+dy[i]>y && jj!=y)      tempy=y-(jj+dy[i]-y);    }
                 if(tempy==-1)  continue;
                 tempx=(ak+dx[i])%3;
                 j=0;
                 len=a[tempx][tempy][0];
                 if(tempy==y)    //如果在对称轴上，加两次 
                 {
                    while(j<a[ak][jj][0])  //add
                    {
                      if(j>len)  len=j;
                      j++;
                      a[tempx][tempy][j]+=a[ak][jj][j]*2;     
                      if(a[tempx][tempy][j]>=BASE)
                      {    a[tempx][tempy][j+1]+=a[tempx][tempy][j]/BASE;
                           a[tempx][tempy][j]=a[tempx][tempy][j]%BASE;  }            
                      if( a[tempx][tempy][len+1]>0  )     len++;     
                    }
                } 
                else          //如果不在对称轴上，加一次
                {
                    while(j<a[ak][jj][0])  //add
                    {
                      if(a[ak][jj][0]>len)  len=a[ak][jj][0];
                      j++;
                      a[tempx][tempy][j]+=a[ak][jj][j];     
                      if(a[tempx][tempy][j]>=BASE)  
                      {    a[tempx][tempy][j+1]+=a[tempx][tempy][j]/BASE;
                           a[tempx][tempy][j]=a[tempx][tempy][j]%BASE;  }            
                      if( a[tempx][tempy][len+1]>0  )     len++;     
                    }              
                }
                a[tempx][tempy][0]=len; 
                tempy=-1;
                 }  
             } 
             
             
       for(i=0;i<=y;++i)   
        {for(j=1;j<=a[ak][i][0];++j)
            a[ak][i][j]=0;
          a[ak][i][0]=0;}    
        kk++;
    }
//------------------------------------------------------------------------------------------------    

   ak=(ak+1)%3;
   fout<<a[ak][y][  a[ak][y][0]  ];
    for(i=a[ak][y][0]-1;i>=1;--i)
      fout<<setw(4)<<setfill('0')<<a[ak][y][i];
    fout<<endl;
    return 0;
}
