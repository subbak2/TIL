import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 피보나치수2 백준 2748
public class Main {
	
	static int N;				// N 피보나치 수
	static long[] dp;			 // 피보나치 수 dp 기록용 배열
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N 입력
		N = Integer.parseInt(br.readLine());
		
		// N+1만큼 dp 배열 선언
		dp = new long[N+1];
		
		dp[0] = 0;
		dp[1] = 1;
		
		// 피보나치 점화식에 따라 N까지 dp 배열 채워줌
		for (int i=2;i<=N;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		// N 번째 피보나치 수 출력
		bw.write(String.valueOf(dp[N]));
			
		bw.flush();
		bw.close();
		br.close();
	}
}