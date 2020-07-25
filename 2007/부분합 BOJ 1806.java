import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 부분합 백준 1806
public class Main {
	
	static int N, S;				// N 숫자 개수, S 목표합
	static int[] array;				// 입력받은 배열
	static int sp, tmpSum, ans;		// sp 시작점, ep 끝점, tmpSum 임시합, ans 갯수
	
	static final int IMPOSSIBLE = 100001;	// 불가능한 값 = 초기값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N, S 입력
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		// 초기값 세팅
		sp = tmpSum = 0;
		ans = IMPOSSIBLE;
		array = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			// 1. 입력받으면서 array 배열에 넣고 임시 합을 더함
			array[i]= Integer.parseInt(st.nextToken());
			tmpSum += array[i];
			
			// 2. 임시합이 목표합보다 크거나 같을 경우 시작점을 당김
			if (tmpSum>=S) {
				// 2-1. 최소 개수를 찾기 위해 출발점에 1을 더하면서 답과 비교
				for(int j=sp; j<=i; j++) {
					// 당긴 값과 답을 비교
					if (ans > (i-j+1)) ans = i-j+1;
					// 답 갱신을 위해 임시합에서 값을 빼줌
					tmpSum -= array[j];
					// 임시합이 목표합보다 작아졌을 경우 end point를 늘리기 위해 break
					if (tmpSum<S) {
						sp = j+1;
						break;
					}
				}
			}
		}
		// 불가능할 경우  ans를 0으로 갱신
		if(ans == IMPOSSIBLE) ans = 0;
		bw.write(String.valueOf(ans)+"\n");				

		bw.flush();
		bw.close();
		br.close();
	}
}