
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 가르침 백준 BOJ 1062

public class Main {
	
	static int N, K, sol;
	static int possible;
	static String[] words;
	static boolean[] alpha;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		sol = 0;
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// K가 5미만일 경우 절대 단어를 읽을 수 없음 (a,c,i,n,t 필수) 
		if(K<5) {
			bw.write(String.valueOf(sol));
			br.close();
			bw.flush();
			bw.close();
			return;
		}		
		
		// 불필요한 접두사, 접미사 제외하고 words[] 에 저장
		words = new String[N];
		String word; 
		for(int i=0; i<N; i++) {
			word = br.readLine();
			words[i] = word.substring(4, word.length()-4);
		}
				
		// 필수 알파벳인 a,c,i,n,t를 true로 마킹, 마킹 가능한 숫자 한도를 계산
		alpha = new boolean[26];
		alpha['a'-'a'] = true;
		alpha['c'-'a'] = true;
		alpha['i'-'a'] = true;
		alpha['n'-'a'] = true;
		alpha['t'-'a'] = true;
		possible = K-5; 

		// DFS를 통해 배울 alphabet '조합'을 선택 후 
		// DFS 내부에서 배울 수 있는 단어 개수 체크   
		dfs(1,0);	// 'b'의 id인 1부터 시작, 현재 cnt 0 <= possible 까지 dfs 실행

		
		bw.write(String.valueOf(sol));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	//DFS로 알파벳 조합 생성
	static void dfs(int id, int cnt) {
		// 가능한 알파벳을 모두 사용했을때만 체크
		if(cnt==possible) {
			int result = wordCnt();
			sol = result > sol? result : sol;
			return;
		}
		// id가 알파벳 개수 26개를 넘어갈 경우 종료
		if (id>=26) return;
		// 아직 사용하지 않은 알파멧일때만 dfs로 확인
		if (alpha[id]==false) {
			alpha[id] = true;
			dfs(id+1, cnt+1);
			alpha[id] = false;
		}
		// 현재 알파벳을 pass하고 dfs 진행
		dfs(id+1, cnt);
	}

	// 단어 개수 체크 함수
	static int wordCnt() {
		String word;	    // 단어 
		boolean tFlag;	    // 단어별 가능여부 체크 flag
		int result = 0;		// 현재 알파벳 조합으로 가능한 최종 결과값
		
		for(int i=0; i<N; i++) {
			tFlag = true;
			word = words[i];
			int len = word.length();
			for (int j=0; j<len; j++) {
				char check = word.charAt(j);
				if (alpha[check-'a']==false) {
					tFlag = false;
					break;
				}
			}
			if (tFlag) result++;
		}
		return result;
	}
}