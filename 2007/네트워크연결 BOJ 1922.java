import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 네트워크연결 백준 1922
public class Main {

	// 간선정보용 class / 출발start, 도착target, 비용cost
	static class info implements Comparable<info>{
		int start, target, cost;

		public info(int start, int target, int cost) {
			this.start = start;
			this.target = target;
			this.cost = cost;
		}

		@Override		// 최소힙 비교연산자
		public int compareTo(info o) {
			return this.cost-o.cost;
		}
	}
	
	static int N, M, ans, Ecnt;				// N 컴퓨터의 수, M 간선의 수, ans 정답, 연결한 간선 Ecnt
	static int[] parent;			 // UnionFind용 parent 배열
	static PriorityQueue<info> pq;		// 비용 정렬용 Priority Queue
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		
		// N 컴퓨터의 수, M 간선의 수
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for (int i=1 ; i<=N; i++) parent[i] = i;		// unionfind 용 부모배열 초기화
		
		pq = new PriorityQueue<info>();
		// input 배열에 가능한 알파벳 입력받기
		for(int i= 1; i<= M; i++)
		{
			int start, target, cost;
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			// cost 순서로 최소힙 정렬
			pq.offer(new info(start, target, cost));
		}
		ans = Ecnt = 0;
		while(!pq.isEmpty()) {
			if(Ecnt==N-1) break;
			info cur = pq.poll();
			if (cur.start==cur.target) continue;		// 같은 컴퓨터는 연결할 필요 x
			if (find(cur.start)!=find(cur.target)) {	// 연결되어있지 않을 경우 연결하고 비용 더함
				Ecnt++;
				union(cur.start,cur.target);
				ans += cur.cost;
			}
		}
		
		bw.write(String.valueOf(ans));
			
		bw.flush();
		bw.close();
		br.close();
	}
	// 조상찾기
	static int find(int a) {
		if(parent[a] == a) return a;
		return find(parent[a]);
	}
	// 동맹맺기
	static void union(int a, int b) {	
		parent[find(a)] = find(b);
	}
}