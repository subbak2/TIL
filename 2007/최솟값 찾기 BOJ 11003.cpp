// 최솟값 찾기 11003
#if 1
#include <stdio.h>
#include <deque>

using namespace std;

typedef struct st {
	int id, val;		// id : 입력받은 순서, val : 값
	st(int id, int val) {
		this->id = id;
		this->val = val;
	}
}node;

int N, L;			// N 숫자 개수, L 최솟값을 구할 범위 (D[i] = A[i-L+1] ~ A[i] 의 최솟값
int i, input;		// i 입력받는 순서, input 입력받은 값
deque<node> deq;	// 앞뒤로 pop이 가능한 deque를 이용 (최대 L개만 갖고 있을 예정)

int main(void) {
	//freopen("input.txt", "r", stdin);
	scanf("%d %d", &N, &L);
	for (i = 0; i < N; i++) {
		// 상황 : 제약조건인 deque의 첫번째 숫자의 id가 i-L보다 작다면? 
		// 동작 : 오래된 숫자 제거
		if (!deq.empty() && deq.front().id <= i - L) deq.pop_front();
		// 새로운 숫자의 입력
		scanf("%d", &input);
		// 목표 : deque의 first숫자를 최솟값으로 만들기 위해서
		// 상황 : deque의 last부터 비교해가면서 input보다 기존 값이 더 클 경우
		// 동작 : 제거해줌 (왜냐하면 지금 input된 값보다 쓸모가 없는 값이므로 제거)
		while (deq.size() && deq.back().val >= input) {
			deq.pop_back();
		}
		// 지금 input된 값은 나중에 최솟값이 될 수도 있으므로 무조건 넣어줌
		deq.push_back(node(i, input));
		// 현재 deque의 first 값은 최솟값을 보장함
		printf("%d ", deq.front().val);
	}
	return 0;
}
#endif