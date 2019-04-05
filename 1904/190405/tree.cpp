// Æ®¸® - BOJ 4803
#if 1
#include <stdio.h>
#include <string.h>
#include <vector>
#define MAX (510)

using namespace std;

int M, N, sol, tc, cnt;
vector<int> adj[MAX];
int visit[MAX];

void Input(void);
void Count(void);
void Print(void);

int main(void)
{
	for (tc = 1;; tc++)
	{
		scanf("%d %d", &N, &M);
		if (!M && !N) return 0;
		Input();
		Count();
		Print();
	}
	return 0;
}

void Input(void){
	register int i;
	int tmp1, tmp2;
	for (i = 1; i <= N; i++) adj[i].clear();
	memset(visit, 0, sizeof(int)*(N+1));
	for (i = 1; i <= M; i++){
		scanf("%d %d", &tmp1, &tmp2);
		adj[tmp1].push_back(tmp2);
		adj[tmp2].push_back(tmp1);
	}
	return;
}

int DFS(int id){
	int i, len, next, tmp = 0;
	visit[id] = 1;
	len = adj[id].size();
	for (i = 0; i < len; i++){
		next = adj[id][i];
		if (!visit[next]){
			tmp += DFS(next);
		}
	}
	cnt += len;
	return tmp + 1;
}

void Count(void){
	register int i;
	sol = 0;
	for (i = 1; i <= N; i++)
	{		
		if (!visit[i]) {
			cnt = 0;
			if (DFS(i) - 1 == cnt / 2){
				sol++;
			}
		}
	}
	return;
}

void Print(void){
	if (!sol) printf("Case %d: No trees.\n", tc);
	else if (sol == 1) printf("Case %d: There is one tree.\n", tc);
	else printf("Case %d: A forest of %d trees.\n", tc, sol);
	return;
}
#endif