//第Ｋ小数２ 
#include <iostream>
#include <cstdlib>
#include <cstdio>

using namespace std;
const int sup = 100010;
int A[sup], B[sup];

bool myCMP(int a, int b)
{
  return a < b;
}

int Bsearch(int s1, int e1, int s2, int e2, int kth)
{
  int idxA = (s1 + e1) >> 1;//一分为二 
  int idxB = (s2 + e2) >> 1;
  int lenA = idxA - s1 + 1;
  int lenB = idxB - s2 + 1;
  if(s1 > e1)  
    lenA = 0;
  if(s2 > e2)  
    lenB = 0;
  int Len = lenA + lenB;//两个数组中当前取出的元素个数和
  //超过了要求的k个元素，那么根据不同情况，
  //将两个数组中的一个截掉后半部分，保留前半部分
  if(Len > kth) 
  {
    //数组A为空或当前B中的中间元素更大,则第k大数倾向于存在B的前半部分
    if(0!=lenB && (0==lenA || A[idxA]<=B[idxB]))//B中还有元素
      return Bsearch(s1, e1, s2, idxB - 1, kth);//截掉B数组中一半元素
    else  
      return Bsearch(s1, idxA - 1, s2, e2, kth);//否则只能截取A中的元素
  }
  else//两个数组中选取的元素个数不够k个，那么根据不同情况，
      //将两个数组中的一个后移到后半部分
  {
    if(kth == Len)//正好相等
    {
      if(0 == lenA) 
        return B[idxB];
      else if(0 == lenB)//并且其中一个是空  
        return A[idxA];
    }
    if(0 != lenA && (0 == lenB || A[idxA] <= B[idxB]))//道理同上 
      return Bsearch(idxA + 1, e1, s2, e2, kth - lenA);
    else //已知道前lenA个大的，在剩下的元素中找到第kth - lenA大的数 
      return Bsearch(s1, e1, idxB + 1, e2, kth - lenB);
    }
}

int main()
{
  freopen("k2.in","r",stdin);
  freopen("k2.out","w",stdout);  
  int n, m, k;
  scanf("%d %d %d", &n, &m, &k);
  for(int i = 0; i < n; ++ i) 
    cin>>A[i];
  for(int i = 0; i < m; ++ i)  
    cin>>B[i];
  sort(A, A + n, myCMP);//若已有序，则无需再排序 
  sort(B, B + m, myCMP);
  printf("%d\n", Bsearch(0, n - 1, 0, m - 1, k));
  return 0;
}
