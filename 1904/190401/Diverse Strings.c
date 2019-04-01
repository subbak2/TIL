// Diverse Strings - Problem A
#if 1
#pragma warning (disable : 4996)
#include <stdio.h>

int n, flag, cnt, f_flag;
char str[120];

int main(void)
{
	int i, j, k, tmp;
	scanf("%d", &n);
	for (i = 1; i <= n; i++){
		int visit[28] = { 0 };
		f_flag = cnt = flag = 0;
		scanf("%s", &str[0]);
		for (j = 0; str[j]; j++){
			tmp = str[j] - 'a';
			visit[tmp]++;
			cnt++;
			if (visit[tmp] > 1) {
				flag = 1;
				break;
			}
		}
		if (flag == 0){
			for (j = 0; j <= 25; j++){
				if (visit[j]){
					cnt--;
					f_flag = 1;
					if (cnt){
						for (k = j + 1; k <= 25; k++){
							if (visit[k] == 0){
								flag = 1;
								break;
							}
							cnt--;
							if (cnt == 0) break;
						}
					}
				}
				if (f_flag) break;
			}
		}
		if (flag) printf("NO\n");
		else printf("YES\n");
	}
	return 0;
}
#endif