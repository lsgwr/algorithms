#include <fstream>
std::fstream fin("k4.in", std::ios::in);
std::fstream fout("k4.out", std::ios::out);

int A[100000], B[100000], C[1000000], Cptr, n;

int dec(const void* a, const void* b)
{
  return *static_cast<const int*>(a) - *static_cast<const int*>(b);
}

bool enough(int limit)//k3的原理,O(N)
{
  int Aptr, Bptr, sum = 0;
  for (Aptr = 0, Bptr = n - 1; Aptr < n; ++Aptr)
  {
    while (Bptr >=0 && A[Aptr] + B[Bptr] > limit) 
      --Bptr;
    sum += Bptr + 1;
  }
  return sum >= n;
}

//找到第n大的数后，把所有比它小的数加入新数组，参见k3，O(N)
void push(int limit)
{
  int Aptr, Bptr, i, sum = 0, ptr1, ptr2;
  for (Aptr = 0, Cptr = 0; Aptr < n; ++Aptr)
	for (Bptr=0;A[Aptr]+B[Bptr]<=limit && Bptr<n;++Bptr)
	  C[Cptr++] = A[Aptr] + B[Bptr];
  for (ptr1 = 0, ptr2 = 0; ptr1 < Cptr; ++ptr1)
	if (C[ptr1] != limit) C[ptr2++] = C[ptr1];
  while (ptr2 < n)
	C[ptr2++] = limit;
}

//参见k3, O(logN)
int find(int left, int right, bool (*law)(int arg))
{
  int mid;
  while (left < right)
  {
    mid = int((unsigned(left)+unsigned(right))/2);
    if (law(mid))
      right = mid;
    else
      left = mid + 1;
  }
  return left;
}

int main()
{
  fin >> n;
  for (int i = 0; i < n; ++i) 
    fin >> A[i];
  for (int i = 0; i < n; ++i) 
    fin >> B[i];
  qsort(A, n, sizeof(A[0]), dec);//排序O(NlogN)
  qsort(B, n, sizeof(B[0]), dec);//排序O(NlogN)
  push(find(A[0]+B[0],A[n-1]+B[n-1],enough));
  qsort(C, Cptr, sizeof(C[0]), dec);//最后排序输出
  for (int i = 0; i < n; ++i) 
    fout << C[i] <<' ';
  fout << std::endl;
}
