//猛兽军团1 
#include <cstdio>
#include <cstring>
#define N 15
#define M 110
#define MAX 550

int s[MAX];//记录一行以内可能的状态
int num[MAX];//对应每个状态放了多少个猛兽 
int states; //状态总数
long long f[N][M][MAX];//f[i][j][k]，第i行状态为k,共放了j个猛兽
                       //注意此处的下标定义略有不同  
int n,m;

void DP()
{
  int i,j,a,c,mm,b,cc;
  long long ans;
  memset(f,0,sizeof(f));
  //先计算第1行的情况
  for(i=0; i<states; i++)//枚举一行可能出现的状态
  {
    j=num[i];  //在这个状态下放了多少个猛兽 
    if(j<=m) 
      f[1][j][i]++;//该行放的个数不能超过总数
  }

  for(i=2; i<n; i++) //从第2行dp到第n-1行
    for(j=0; j<=m; j++)//到第i行，一共放了j个猛兽
      for(a=0; a<states; a++)//当前第i行的状态
      {
        c=num[a];//c表示第i行放了多少个猛兽
        if(c>j) 
          continue; //若当前行的猛兽数超过总数j，则忽略
        mm=j-c;  //那么前i-1行的总数是mm
        for(b=0; b<states; b++)//枚举第i-1行的状态
        {
          cc=num[b];//cc表示第i-1行放了多少个猛兽 
          if(cc>mm) 
            continue; //若当前行的猛兽数超过总数mm则忽略
          if(s[a] & s[b]) //有攻击
            continue;     
          if(s[a] & (s[b]<<1))//有攻击 
            continue;
          if(s[b] & s[a]<<1)//有攻击   
            continue;
          f[i][j][a] += f[i-1][mm][b];
        }
  }
    
  ans=0;
  for(a=0; a<states; a++)//枚举最后一行的状态
  {
    c=num[a];//该行的猛兽数
    if(c>m) 
      continue;//该行的猛兽数就超过总数，跳过
    j=m-c;  //前n-1行的总数
    for(b=0; b<states; b++)  //第n-1行的状态
    {
      cc=num[b];  //第n-1行的状态
      if(cc>j) 
        continue;//第n-1行的猛兽数超过了总数
      if(s[a] & s[b]) 
        continue;
      if(s[a] & (s[b]<<1)) 
        continue;
      if(s[b] & s[a]<<1)   
        continue;
      f[n][m][a] += f[n-1][j][b];
    }
    ans += f[n][m][a];
  }
  printf("%lld\n",ans);
}

void init_state()
{
  states=0;
  for(int i=0; i<(1<<n); i++)//枚举所有可能状态
  {
    if( i & (i<<1) )//如行内互相攻击丢弃 
      continue; 
    int t=i;
    num[states]=0;
    while(t)//统计在这个i状态下放了多少个猛兽 
    { 
      num[states] += (t&1); 
      t=t>>1; 
    }
    s[states++]=i;//保存这个合法的状态
  }
}

int main()
{
  freopen("embattle.in","r",stdin);
  freopen("embattle.out","w",stdout);
  scanf("%d%d",&n,&m);
  init_state();  //找出所有可能的状态
  DP();  //动态规划解决
  return 0;
}
