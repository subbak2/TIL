// 참고자료 : youtu.be/Vi0hauJemxA
package test;

import java.util.LinkedList;

public class hashPractice {
	
	// 해시테이블 클래스
	static class HashTable{
		class Node{
			String key;
			String value;
			public Node(String key, String value) {
				this.key = key;
				this.value = value;
			}
			String value() {
				return value;
			}
			void value(String value) {
				this.value = value;
			}
		}
		
		LinkedList<Node>[] data;
		
		// HashTable를 size 크기의 LinkedList로 선언
		HashTable(int size){
			this.data = new LinkedList[size];
		}
		
		// getHashCoed : String을 받아 ASCII 코드 합을 return
		int getHashCode(String key) {
			int hashcode = 0;
			for (char c : key.toCharArray()) {
				hashcode +=c;
			}
			return hashcode;
		}
		
		// convertToIndex : 해시코드를 modulus 한 값을 return
		int convertToIndex(int hashcode) {
			return hashcode % data.length;
		}
		
		// searchKey : list에서 key 값이 있을 경우 해당 node를 return, 없으면 null 리턴
		Node searchKey(LinkedList<Node> list, String key) {
			if (list == null) return null;
			for (Node node : list) {
				if (node.key.equals(key)) {
					return node;
				}
			}
			return null;
		}
		
		// put : String의 Key, Value 값을 해시 테이블에 추가 
		// 1) 인덱스에 리스트 존재하지 않으면 새로 리스트 만들고 node 추가
		// 2) 인덱스에 리스트 존재하면 
		// 2-1) Key 값이 존재하지 않는다면 node 추가
		// 2-2) Key 값이 존재하면 value 덮어쓰기
		void put(String key, String value) {
			// 0) ASCII 합을 구해서 index를 구함
			int hashcode = getHashCode(key);
			int index = convertToIndex(hashcode);
			
			System.out.println(key + ", hashcode("+ hashcode+"), index("+index+")");
			
			LinkedList<Node> list = data[index];
			// 1) 인덱스에 리스트 존재하지 않으면
			// 새로 리스트 만들고 node 추가
			if (list == null) {
				list = new LinkedList<Node>();
				list.addLast(new Node(key, value));
				data[index] = list;
			}
			// 2) 인덱스에 리스트가 존재하면 
			// 2-1) key값이 존재하지 않는다면 node 추가
			// 2-2) key값이 존재하면 덮어쓰기
			else {
				Node node = searchKey(list, key);
				if (node == null) {
					list.addLast(new Node(key, value));
				}
				else {
					node.value(value);
				}
			}
		}
		// get : key 값이 존재하면 value를 리턴, 없으면 "Not found" 리턴
		String get(String key) {
			int hashcode = getHashCode(key);
			int index = convertToIndex(hashcode);
			LinkedList<Node> list = data[index];
			Node node = searchKey(list, key);
			return node == null? "Not found" : node.value();
		}
		
	}

	public static void main(String[] args) {
		
		HashTable h = new HashTable(3);
		
		h.put("sung", "She is pretty");
		h.put("jin", "She is a model");
		h.put("hee", "She is an angel");
		h.put("min", "She is cute");
		
		System.out.println(h.get("sung"));
		System.out.println(h.get("jin"));
		System.out.println(h.get("hee"));
		System.out.println(h.get("min"));
		System.out.println(h.get("jae"));
		
		h.put("min", "She is beautiful");
		System.out.println(h.get("min"));
		
	
	}
}
