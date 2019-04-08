// º¸¼®µµµÏ
#if 1
#include <stdio.h>
#include <stdlib.h>
#include <queue>
#pragma warning (disable:4996)
#define MAX (300010)

using namespace std;

int N, K;
int Jewel[MAX][2];
int Bag[MAX];
long long sol;
priority_queue<int> pq;

void Input(void);
void MakeSol(void);

int main(void)
{
	scanf("%d %d", &N, &K);
	Input();
	MakeSol();
	printf("%lld\n", sol);
	return 0;
}

int Jcompare(const void * a, const void * b){
	int * aa = (int *)a;
	int * bb = (int *)b;
	return aa[0] - bb[0];
}

int Bcompare(const void * a, const void * b){
	int * aa = (int *)a;
	int * bb = (int *)b;
	return *aa - *bb;
}

void Input(void){
	register int i;
	for (i = 1; i <= N; i++){
		scanf("%d %d", &Jewel[i][0], &Jewel[i][1]);
	}
	qsort(&Jewel[1][0], N, 8, Jcompare);
	for (i = 1; i <= K; i++)
		scanf("%d", &Bag[i]);
	qsort(&Bag[1], K, 4, Bcompare);
	return;
}

void MakeSol(void){
	register int i, j;
	sol = 0;
	for (i = 1, j = 1; i <= K; i++){
		while (j <= N && (Jewel[j][0] <= Bag[i])) {
			pq.push(Jewel[j++][1]);
		}
		if (!pq.empty()) {
			sol += pq.top();
			pq.pop();
		}
	}
	return;
	}
#endif