import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 암호만들기 백준 1759
public class Main {

	static int L, C;				// L은 길이, C는 알파벳 개수
	static int lastMoeum;			// 마지막 모음이 있는 곳을 기록 (dfs 가지치기를 위함)
	
	static String[] input;			// input 받은 배열
	static ArrayList<String> answer;			// 답 순서를 저장할 배열
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// L 비밀번호길이 / C 문자개수
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// input 배열에 가능한 알파벳 입력받기
		input = new String[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++)
		{
			input[i] = st.nextToken();
		}
		// input 오름차순으로 정렬
		Arrays.sort(input);
		
		// 마지막 모음을 체크 -> dfs에서 가지치기를 위함
		lastMoeum = 0;
		for (int i=1; i<C; i++) {
			String tmp = input[i];
			if (tmp.equals("e")||tmp.equals("i")||tmp.equals("o")||tmp.equals("u")) {
				lastMoeum = i;
			}
		}
		
		// 답은 동적배열로 가능하게 선언
		answer = new ArrayList<String>();
		dfs(0, 0, 0, "");	// id : 현재위치/ moeum : 모음 Cnt / cnt : 만든 배열 길이 / ans : 정답String
		
		// 정답 출력
		int size = answer.size();
		for (int i = 0; i<size; i++) {
			bw.write(answer.get(i)+"\n");
		}
			
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	static void dfs(int id, int moeum, int cnt, String ans) {		// id : 현재위치	/	 moeum : 모음 개수 	/	cnt : 만든 배열 길이
		// 글자 개수를 다 채우고 모음이 1개면 정답에 추가 
		if (cnt == L && moeum>=1 && cnt-moeum>=2) {
			answer.add(ans);
			return;
		}
		if (id == C) return;	// 배열 못 채우고 끝
		if (moeum==0 && id>lastMoeum) return;	// 모음을 충족할 수 없어서 끝

		String tmp = input[id];
		// 모음이면 flag 바꿔서 dfs 진행
		if (tmp.equals("a")||tmp.equals("e")||tmp.equals("i")||tmp.equals("o")||tmp.equals("u")) {
			dfs(id+1, moeum+1, cnt+1, ans+input[id]);
		}
		// 자음이면 그냥 dfs 진행
		else {
			dfs(id+1, moeum, cnt+1, ans+input[id]);
		}
		// 문자를 선택 안 할 경우
		dfs(id+1, moeum, cnt, ans);
		return;
	}
}