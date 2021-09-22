#include <iostream>
#include <cmath>

using namespace std;

class binary{
public:
	double left , right;

	binary(double l, double r)
	{
		if (l > r)
		{
			left = 0;
			right = -1;
		}
		else
		{
			left = l;
			right = r;
		}
	}
	binary(void)
	{
		left = 0;
		right = -1;
	}
	const binary operator & (const binary& other) const
	{
		double l, r;

		if (other.left > left)
			l = other.left;
		else
			l = left;
		if (other.right < right)
			r = other.right;
		else
			r = right;

		return binary(l, r);
	}
	bool operator != (const binary& other) const
	{
		return left != other.left || right != other.right;
	}
	bool operator == (const binary& other) const
	{
		return left == other.left && right == other.right;
	}
};

const binary NULLbinary = binary(0, -1);

binary a[10000];

int compare(const void* x, const void* y)
{
	const binary* a = static_cast<const binary*>(x);
	const binary* b = static_cast<const binary*>(y);

	return int (a->left - b->left);
}

int main(int argc, char* argv[])
{
    	freopen("location2.in", "r", stdin);
	freopen("location2.out", "w", stdout);
	unsigned timer, N, W, H, i, X, RR, C = 0, U = 1;
	double T, L, R, Q = 0;

	cin >> timer;

	while (timer--)
	{
		cin >> N >> W >> H;

		C = 0; U = 1; Q = 0;

		T = double(H) * double(H) / 4.0;

		for (i = 0; i < N; ++i)
		{
			cin >> X >> RR;

			if (RR > double(H) / 2.0)
			{
				L = X - sqrt(RR * RR - T);
				R = X * 2 - L;

				if (L < 0) L = 0;
				if (R > W) R = W;

				a[C++] = binary(L, R);
			}
		}

		qsort(a, C, sizeof(binary), compare);

		for (i = 0; i < C && fabs(a[i].left) <= 1e-6; ++i)
			Q = fmax(Q, a[i].right);

		T = Q;

		for (; i < C && Q < W; ++i)
		{
			if (a[i].left > T)
			{
				U = 0;
				break;
			}
			else if (a[i].right > T && a[i].left <= Q)
			{
				T = a[i].right;
			}
			else if (a[i].left <= T && a[i].left > Q)
			{
				Q = T;
				++U;
				--i;
			}
		}

		if (Q < W && T >= W)
			++U;
		else if (T < W)
			U = 0;

		cout << U << endl;
	}

	return 0;
}
