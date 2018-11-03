// Traveling Salesman Problem (외판원 순회) DSF version.
#if 1
#pragma warning (disable : 4996)
#include <stdio.h>
#define MAX (18)

unsigned int N, sol;
int Cost[MAX][MAX];
int Visit[MAX];

void Input(void)
{
	register unsigned int i, j;
	scanf("%d", &N);
	for (i = 1; i <= N; i++)
	{
		for (j = 1; j <= N; j++)
			scanf("%d", &Cost[i][j]);
	}
	return;
}

void Find(int start, int now, int cnt, int cost)
{
	unsigned int i;
	if (cnt == N)
	{
		int tmp;
		tmp = Cost[now][start];
		if (tmp > 0 && tmp + cost < sol) sol = tmp + cost;
		return;
	}
	for (i = 1; i <= N; i++)
	{
		if (Visit[i] || !Cost[now][i] || Cost[now][i] + cost >= sol) continue;
		Visit[i] = 1;
		Find(start, i, cnt + 1, cost + Cost[now][i]);
		Visit[i] = 0;
	}
}

int main(void)
{
	unsigned int i;
	sol = 0x7fff0000;
	Input();
	for (i = 1; i <= N; i++)
	{
		Visit[i] = 1;
		Find(i, i, 1, 0);
		Visit[i] = 0;
	}
	printf("%d\n", sol);
	return 0;
}
#endif