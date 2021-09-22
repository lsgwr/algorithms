//神奇矩阵优化一
#include <iostream>
using namespace std;
int a[3][3],b[3][3];

int main()
{
    int i,tx,ty;
    int x=0,y=1;
    a[0][1]=1;//将1放在第0行第1列 
    for(i=2;i<=9;i++)//依次放2->9 
    {
        tx=(x+2)%3;
        ty=(y+1)%3;
        if(a[tx][ty]==0)//如果斜上方未填数字 
        {
          a[tx][ty]=i;//x为行，y为列
          x=tx;
          y=ty;
        }
        else//否则填数到下方 
        {
          x=(x+1)%3; 
          a[x][y]=i;
        }   
    }
  for(i=0;i<=3;i++)
  {
    cout<<a[0][0]<<a[0][1]<<a[0][2]<<endl;
    cout<<a[1][0]<<a[1][1]<<a[1][2]<<endl;
    cout<<a[2][0]<<a[2][1]<<a[2][2]<<endl;        
    cout<<endl;
    cout<<a[2][0]<<a[2][1]<<a[2][2]<<endl; //上下翻转 
    cout<<a[1][0]<<a[1][1]<<a[1][2]<<endl;
    cout<<a[0][0]<<a[0][1]<<a[0][2]<<endl;
    cout<<endl;     
    
    for(int ii=0;ii<3;ii++)//借助辅助数组b进行旋转 
      for(int jj=0;jj<3;jj++)
        b[jj][2-ii]=a[ii][jj];
    for(int ii=0;ii<3;ii++)
      for(int jj=0;jj<3;jj++)
        a[ii][jj]=b[ii][jj];    
  }
  system("pause");
  return 0;
}
