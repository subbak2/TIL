import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 달리기 백준 2517
public class Main {
	
	// 선수 id : 현재 순서, speed : 속도
	static class player implements Comparable<player>{
		int id, speed;
		public player(int id, int speed) {
			this.id = id;
			this.speed = speed;
		}
		// 내림차순 정렬
		@Override
		public int compareTo(player o) {
			return o.speed - this.speed;
		}
	}
	
	static int N, nn;					// N 선수 숫자,	nn 인덱스드트리 위한 숫자
	static int[] indexedTree;			// 인덱스드트리
	static player[] list;				// 선수의 list (현재순위 -> speed순 정렬 예정)
	static int[] ans;					// 정답출력용 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, M, K 입력
		N = Integer.parseInt(br.readLine());
		
		// list의 정렬시 0부터 전체 정렬을 위해 list[0]을 초기화해서 넣어줌
		list = new player[N+1];
		list[0] = new player(0,0);
		
		// i번째 선수의 id와 speed를 list 배열에 넣음
		for(int i=1; i<=N; i++) {
			list[i] = new player(i, Integer.parseInt(br.readLine()));
		}
		// speed 기준 내림차순 정렬		
		Arrays.sort(list);
				
		for (nn=1; nn<N; nn*=2);	    // 인덱스드트리 크기 특정 및 선언   (N보다 큰 최소 2^N)
		indexedTree = new int[nn*2+2];
		ans = new int[N+1];				// 정답출력 배열
		
		// list에서 선수 뽑으면서 speed 제일 높은 선수부터 indexedTree에 넣고 합을 구함 = speed로 역전 가능한 최고 순위
		for (int i=0; i<N; i++) {
			player cur = list[i];
			// 지금 들어온 선수를 인덱스드트리에 넣기
			edit(cur.id, 1);
			// 1 ~ 최초순위까지 이미 들어온 선수 count = 가능한 최고 순위 
			ans[cur.id] = sum(1,cur.id);  
		}
		
		// 정답배열 출력
		for (int i=1; i<=N; i++) {
			bw.write(String.valueOf(ans[i])+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 인덱스드트리 수정 1) 해당 id 값 수정 2) 위로 올라가면서 값 갱신 -> logN의 시간복잡도
	static void edit(int id, int value) {
		int x = id + nn - 1;
		// 인덱스드트리 위치에 value로 값을 덮어쓰고
		indexedTree[x] = value;
		x /= 2;
		while(x>0) {
			indexedTree[x] = indexedTree[x*2] + indexedTree[x * 2 + 1];
			x /=2;
		}
		return;
	}
	
	// 인덱스드트리 합 구하기 위로 올라가면서 합 구하기
	static int sum(int start, int end) {
		int l = start + nn - 1;
		int r = end + nn - 1;
		int ret = 0;
		while(l<=r) {
			if ((l & 1) == 1) ret += indexedTree[l++];	// 왼쪽 id가 홀수이면 값이 튀므로 더해주고 l++ 해줌 (짝수부터 시작해야하니까)
			if ((r & 1) == 0) ret += indexedTree[r--];	// 오른쪽 id가 짝수이면 값이 튀므로 더해주고 r-- 해줌 (홀수로 끝나야하니까)
			l/=2;	// 위로 올라가기
			r/=2;	// 위로 올라가기
		}
		return ret;
	}
}