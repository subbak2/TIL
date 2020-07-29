import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 내려가기 백준 2096
public class Main {
		
	static int N, max, min;			// N 줄의 개수, max 출력할 최댓값, min 출력할 최솟값
	static int[][] map;				// 숫자가 써있는 지도
	static int[][] maxMap;			// 최댓값들을 가지고 내려오는 경우 기록
	static int[][] minMap;			// 최솟값들을 가지고 내려오는 경우 기록
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st; 
		
		// N, L 입력
		N = Integer.parseInt(br.readLine());
		
		map = new int [N+1][3];
		maxMap = new int [N+1][3];
		minMap = new int [N+1][3];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 1. 차례대로 지도에 입력받음
			map[i][0] = Integer.parseInt(st.nextToken());	
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
			
			// 2. maxMap 현재높이의 최댓값을 갱신
			// 2-1. 가장 왼쪽 자리는 바로 윗칸과 가운데 칸에서만 
			// 올 수 있으므로 둘 중에 큰 값을 현재 입력값에 더 함
			maxMap[i][0] = big(maxMap[i-1][0], maxMap[i-1][1]);
			maxMap[i][0] += map[i][0];
			
			// 2-2. 가운데 자리는 모든 칸에서 내려올 수 있으므로 
			// 세 칸 중에 큰 값에 현재 입력값을 더 함
			maxMap[i][1] = big(maxMap[i-1][0],maxMap[i-1][1],maxMap[i-1][2]);
			maxMap[i][1] += map[i][1];

			// 2-3. 가장 오른쪽 자리는 바로 윗칸과 가운데 칸에서만 
			// 올 수 있으므로 둘 중에 큰 값을 현재 입력값에 더 함
			maxMap[i][2] = big(maxMap[i-1][2], maxMap[i-1][1]);
			maxMap[i][2] += map[i][2];
			
			// 3. minMap의 경우 maxMap과 같은 로직으로 처리
			minMap[i][0] = small(minMap[i-1][0], minMap[i-1][1]);
			minMap[i][0] += map[i][0];
			
			minMap[i][1] = small(minMap[i-1][0],minMap[i-1][1],minMap[i-1][2]);
			minMap[i][1] += map[i][1];

			minMap[i][2] = small(minMap[i-1][2], minMap[i-1][1]);
			minMap[i][2] += map[i][2];
		}
		max = big(maxMap[N][0], maxMap[N][1], maxMap[N][2]);
		min = small(minMap[N][0], minMap[N][1], minMap[N][2]);
		
		bw.write(String.valueOf(max)+" "+String.valueOf(min));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int big(int a, int b) {
		if (a>b) return a;
		return b;
	}
	static int small(int a, int b) {
		if (a<b) return a;
		return b;
	}
	static int big(int a, int b, int c) {
		if (a>b) return big(a,c);
		return big(b,c);
	}
	static int small(int a, int b, int c) {
		if (a<b) return small(a,c);
		return small(b,c);
	}
}