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
    int x,y,i,j,xa,jj,kk,ak,temp,len;
    fin>>x>>y;
    int a[3][y+1][500];
    memset(a,0,sizeof(a));
    a[0][y/2][0]=1;
    a[0][y/2][1]=1;
    kk=0;
    xa=x-1;
    while(kk<=xa)
    {
        jj=-1;
        ak=kk%3;
       
        while(jj<y)
         {
           ++jj;
           if(a[ak][jj][0]!=0)
             for(i=0;i<=3;++i)
               if(jj+dy[i]>=0 && jj+dy[i]<=y)  
               {
                 j=0;
                 temp=(ak+dx[i])%3;
                 len=a[temp][jj+dy[i]][0];
                  while(j<a[ak][jj][0])  //add
                    {
                      if(j>len)  len=j; 
                      j++;
                      a[temp][jj+dy[i]][j]+=a[ak][jj][j];     
                      if(a[temp][jj+dy[i]][j]>=BASE)
                      {   a[temp][jj+dy[i]][j+1]+=a[temp][jj+dy[i]][j]/BASE;       
                          a[temp][jj+dy[i]][j]=a[temp][jj+dy[i]][j]%BASE;   }      
                               
                      if( a[temp][jj+dy[i]][len+1]>0  )     len++;   
                    
                    } 
                a[temp][jj+dy[i]][0]=len; 
                 }  
             } 

        for(i=0;i<=y;++i)   
         {
          for(j=a[ak][i][0];j>=1;--j) 
            a[ak][i][j]=0;
          a[ak][i][0]=0;
         }
        
        kk++;
    }
    
   
    ak=(ak+1)%3;
    fout<<a[ak][y/2][  a[ak][y/2][0]  ];
    for(i=a[ak][y/2][0]-1;i>=1;--i)
      fout<<setw(4)<<setfill('0')<<a[ak][y/2][i];
    fout<<endl;
    return 0;
}
