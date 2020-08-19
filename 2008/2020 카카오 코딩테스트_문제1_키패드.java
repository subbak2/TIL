public class Solution {
	// 문제에선 매개변수로 주어지는 부분 
	// (로컬에서 돌려보기위해 변수선언)
	static int [] numbers = {1,3,4,5,8,2,1,4,5,9,5};
	static String hand = "right";
	
	// 좌표 클래스 r : row (행) / c : column(열)
	static class location{
		int r,c;
		public location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}


	static location[] map = new location[12]; 	// 숫자별 좌표를 담은 배열
	static boolean rFlag = true;	// 어느손잡이인지 판별용 : 오른손잡이 true / 왼손잡이 false
	static int lLoc = 10;			// *을 임의로 10으로 변환
	static int rLoc = 11;			// #을 임의로 11으로 변환
    
	public static void main(String[] args) {
		String answer = "";
				
		// 만약에 왼손잡이이면 flag를 false로 
		if (hand.equals("left")) {
			rFlag = false;
		}
		
		// 각각 다이얼 숫자별 좌표를 초기세팅
		map[0] = new location(3,1);
		map[1] = new location(0,0);
		map[2] = new location(0,1);
		map[3] = new location(0,2);
		map[4] = new location(1,0);
		map[5] = new location(1,1);
		map[6] = new location(1,2);
		map[7] = new location(2,0);
		map[8] = new location(2,1);
		map[9] = new location(2,2);
		map[10] = new location(3,0);
		map[11] = new location(3,2);
	       
		// numbers의 길이만큼 반복문 실행
	    int len = numbers.length;
	    for (int i=0; i<len; i++) {
	    	 int num = numbers[i];
	    	 // 1. num이 1,4,7일 경우 무조건 왼손
	    	 if (num %3 == 1) {
	    		 lLoc = num;
	    		 answer = answer.concat("L");
	    	 }
	    	 // 2. num이 3,6,9일 경우 무조건 오른손
	    	 else if (num %3 == 0 && num != 0) {
	    		 rLoc = num;
	    		 answer = answer.concat("R");
	    	 }
	    	 // 3. num이 2,5,8,0일 경우 판별
	    	 else {
	    		 answer = answer.concat(addAnswer(num));
	    	 }
	     }
	     System.out.println(answer);
	}
	
	// 가운데 다이얼일경우 판별하는 함수
	static String addAnswer(int num) {
		int lDis = distance(map[num], map[lLoc]);	// 왼손에서의 거리
		int rDis = distance(map[num], map[rLoc]);	// 오른손에서의 거리
		
		// 1. 오른손잡이
		if (rFlag) {
			// 1) 왼손이 짧을때만 왼손으로
			if (lDis<rDis) {
				lLoc = num;
				return "L";
			}
			// 2) 나머지는 오른손으로 
			else {
				rLoc = num;
				return "R";
			}
		}
		// 2. 왼손잡이
		else {
			// 1) 오른손이 짧을때만 오른손으로
			if (rDis<lDis) {
				rLoc = num;
				return "R";
			}
			// 2) 나머지는 왼손으로
			else {
				lLoc = num;
				return "L";
			}
		}
	}
	
	// 거리 구하는 함수 y좌표 절대값 차이 + x좌표 절대값 차이
	static int distance(location l1, location l2) {
		int dis1 = l1.r - l2.r;
		int dis2 = l1.c - l2.c;
		if (dis1<0) dis1 *= -1;
		if (dis2<0) dis2 *= -1;
		
		return dis1 + dis2;
	}
}