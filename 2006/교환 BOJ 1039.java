import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 교환 백준 BOJ 1039
public class Main {

	static int K, len, sol;
	static String N;
	static int[][] visit;			// [변경횟수][최대숫자] 해당 변경횟수에서 숫자 방문시 메모이제이션으로 가지치기

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = st.nextToken();				// String 형태의 N (자릿수 계산을 편하게 하기 위함) 
		K = Integer.parseInt(st.nextToken());

		len = N.length();
		visit = new int[K+1][1000001];		// [변경횟수] [최대숫자] 2차원 visit 배열
		
		sol = -1;		// 연산이 불가능하다고 가정하고 시작
		sol = dfs(N, 0);
		
		bw.write(String.valueOf(sol));

		br.close();
		bw.flush();
		bw.close();
	}

	static int dfs(String strN, int depth) {
		int num = Integer.parseInt(strN);
		if (depth==K) return num;	// depth가 K만큼 오면 끝
		
		int ret = visit[depth][num];
		if(ret!=0) return ret;		// 같은 depth에서 방문한 이력이 있으면 메모이제이션된 값 return
		
		ret = -1;					// 처음 방문한 경우 안된다고 가정하고(-1) 시작
	    for (int i = 0; i < len-1; i++)
	    {
	        for (int j = i+1; j < len; j++)
	        {
	        	if (i==0 && strN.charAt(j)=='0') continue;	// 0을 맨 앞으로 가져올 수 없음
	        	String tmpStr = swapLoc(strN,i,j);
	        	int tRet = dfs(tmpStr, depth+1);
	        	ret = tRet > ret? tRet:ret;
	        }
	    }
	    visit[depth][num] = ret;
	    return ret;
	}
	
	// String의 i, j 위치가 들어오면 바꿔주는 함수
	static String swapLoc(String str, int i, int j) {
		char[] chars = str.toCharArray(); 
		char tmp = chars[i];
		chars[i] = chars[j];
		chars[j] = tmp;
		return String.valueOf(chars);
	}
}