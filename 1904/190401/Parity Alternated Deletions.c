// Parity Alternated Deletions - Problem B
#if 0
#pragma warning (disable : 4996)
#include <stdio.h>
#include <stdlib.h>
#define MAX (2010)

int N, sol, odd_cnt, even_cnt;
int arr[MAX];
int odd_arr[MAX];
int even_arr[MAX];

int compare(const void * a, const void * b){
	int * aa = (int *)a;
	int * bb = (int *)b;
	return *aa - *bb;
}

int main(void)
{
	register int i;
	int tmp;
	scanf("%d", &N);
	sol = odd_cnt = even_cnt = 0;
	for (i = 1; i <= N; i++)
	{
		scanf("%d", &arr[i]);
		if (arr[i] & 1) {
			odd_cnt++;
			odd_arr[odd_cnt] = arr[i];
		}
		else {
			even_cnt++;
			even_arr[even_cnt] = arr[i];
		}
	}
	if (odd_cnt > even_cnt+1){
		tmp = odd_cnt - even_cnt;
		qsort(&odd_arr[1], odd_cnt, sizeof(int), compare);
		for (i = 1; i < tmp; i++)
			sol += odd_arr[i];
	}
	else if (even_cnt>odd_cnt+1){
		tmp = even_cnt - odd_cnt;
		qsort(&even_arr[1], even_cnt, sizeof(int), compare);
		for (i = 1; i < tmp; i++)
			sol += even_arr[i];
	}
	printf("%d\n", sol);
	return 0;
}
#endif