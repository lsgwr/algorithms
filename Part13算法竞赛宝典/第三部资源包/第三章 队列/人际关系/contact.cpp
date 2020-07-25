//人际关系
#include <bits/stdc++.h>
using namespace std;
int  q[10005][2],man[105][105];//q为队列
bool visit[110];

int main()
{
  freopen("contact.in","r",stdin);
  freopen("contact.out","w",stdout);
  int n,x,y,rear=1,front=1;
  cin>>n>>x>>y;
  for(int i=1; i<=n; i++)
    for(int j=1; j<=n; j++)
      cin>>man[i][j];
  q[front][0]=x;
  q[front][1]=0;//保存步数
  visit[x]=true;//标记为已走过
  while(front<=rear)
  {
    if(q[front][0]==y)
    {
      cout<<q[front][1]-1<<endl;
      return 0;
    }
    for(int i=1; i<=n; i++)
      if(man[i][q[front][0]]==1 && !visit[i])
      {
        q[++rear][0]=i;//入队
        q[rear][1]=q[front][1]+1;
        visit[i]=true;
      }
    front++;//出队
  }
  return 0;
}

