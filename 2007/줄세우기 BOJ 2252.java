import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 줄세우기 백준 2252
public class Main {

	static int N, M, cnt;
	
	static int[] indegree;			// indegree 배열
	static ArrayList[] edge; 		// 간선정보 배열 (queue에서 poll 할때 간선 잘라주는 용도이므로 출발지에 add함)
	static Queue<Integer> queue;	// 위상정렬용 Queue
	
	static int[] answer;			// 답 순서를 저장할 배열
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N 학생수 / M 간선수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		indegree = new int[N+1];		// indegree 배열 		
		answer = new int[N+1];			// 정답출력용 배열
		cnt = 0;						// 정답배열 pointer 위치 변수
		
		// 간선배열 2차원으로 선언
		edge = new ArrayList[N+1];				
		for(int i=1; i<=N; i++) {
			edge[i] = new ArrayList<Integer>();
		}
		
		queue = new LinkedList<Integer>(); // Queue 선언
				
		// 입력받으면서 작은 녀석에 간선배열을 넣고, 큰 녀석에 indegree를 추가시킴
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edge[a].add(b);
			indegree[b]++;
		}
		
		// indegree 0인 녀석들 최초 queue에 넣기
		for (int i = 1 ; i <= N ; i++) {
			if(indegree[i]==0) queue.add(i);
		}
		
		// Queue에서 뽑으면서 edge 있는 녀석들 indegree 잘라주고 indegree 0되면 Queue에 넣기
		// Queue에서 뽑을때 answer 배열에 넣음
		while(!queue.isEmpty()) {
			int id = queue.poll();
			
			answer[cnt] = id;
			cnt++;
			
			int size = edge[id].size();
			for(int i=0; i<size; i++) {
				int target = (int) edge[id].get(i);
				indegree[target]--;
				if(indegree[target]==0) queue.add(target);
			}
		}
		
		// asnwer 배열 출력
		for(int i=0; i<cnt; i++) {
			bw.write(String.valueOf(answer[i])+" ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}