//TSP
#if 1
#include <stdio.h>
#pragma warning (disable: 4996)
#define MAX (65600)
#define INF (0x70000000)

int N;
int W[17][17];
int visit[17][MAX];

void Input(void);
int makeSol(int cur, int visited);
int Min(int a, int b);

int main(void)
{
	Input();
	printf("%d\n", makeSol(0, 1));
	return 0;
}

int makeSol(int cur, int visited) {
	int i;
	int * ret = visit[cur];
	if (visited == ((1 << N) - 1)) 
		return W[cur][0] ? W[cur][0] : INF;
	if (ret[visited] != -1) return ret[visited];
	ret[visited] = INF;
	for (i = 0; i < N; i++) {
		if (visited & (1 << i)) continue;
		if (W[cur][i] == 0) continue;
		ret[visited] = Min(ret[visited], makeSol(i, visited | (1 << i)) + W[cur][i]);
	}
	return ret[visited];
}

void Input(void) {
	register unsigned int i, j;
	scanf("%d", &N);
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++)
		{
			scanf("%d", &W[i][j]);
		}
		for (j = 0; j < 1 << N; j++)
			visit[i][j] = -1;
	}
	return;
}

int Min(int a, int b) {
	return a < b ? a : b;
}
#endif
