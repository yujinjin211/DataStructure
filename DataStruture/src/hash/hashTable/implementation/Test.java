package hash.hashTable.implementation;

import java.util.LinkedList;

class HashTable {
	class Node {
		String key; //검색할 키
		String value; //검색 결과 값
		
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
	
	LinkedList<Node>[] data; //data를 저장할 LinkedList를 배열로 선언.
	
	//HashTable을 선언한 순간 HashTable을 얼만한 크기로 만들지 크기를 미리 정해서 배열방을 미리 만들어 놓는다.
	HashTable(int size) {
		this.data = new LinkedList[size];
	}
	
	//key를 받아서 hashcode를 반환.
	int getHashCode(String key) {
		int hashcode = 0; //먼저 hashcode를 0으로 초기화
		for(char c : key.toCharArray()) { //입력받은 키, 문자열을 돌면서 각 ASCII값을 가져와서 hashcode에 더한다. 
			hashcode += c;
		}
		return hashcode;
	}
	
	int convertToIndex(int hashcode) { //hashcode를 받아서 배열방의 index로 변환해주는 메소드
		return hashcode % data.length; //hashcode를 배열방의 크기로 나눈 나머지가 배열방의 index가 된다.
	}
	
	//index로 배열방을 찾은 이후, 배열방의 노드가 여러 개 존재하는 경우 검색 key로 해당 노드를 찾아오는 메소드
	Node searchKey(LinkedList<Node> list, String key) {
		if(list == null) return null; //배열방이 null이면 null을 반환
		for(Node node : list) { //그렇지 않으면 배열방에 있는 LinkedList를 돌면서 노드의 키가 검색하는 키와 같은지 비교한다.
			if(node.key.equals(key)) {
				return node;
			}
		}
		return null;
	}
	
	//데이터를 받아서 저장하는 메소드
	void put(String key, String value) { //저장할 key와 value
		int hashcode = getHashCode(key); //key로 hashcode를 받아온다.
		int index = convertToIndex(hashcode); //받아온 hashcode로 저장할 배열방 번호를 받아온다.
		LinkedList<Node> list = data[index]; //배열방 번호를 이용해서 기존 배열방의 데이터를 가져오고
		if(list == null ) { //배열방이 0이면 
			list = new LinkedList<Node>(); //LinkedList를 생성한다.
			data[index] = list; //해당 list를 배열방에 넣어준다.
		}
		Node node = searchKey(list, key); //배열방에 혹시 기존에 해당 key로 데이터를 가지고 있는지 Node를 받아온다.
		if(node == null) { //node가 null이면 데이터가 없다는 의미로 받아온 정보로 node 객체를 생성하여 list에 추가한다.
			list.addLast(new Node(key, value));
		} else {
			node.value(value); //node가 null이 아니면 해당 노드의 값을 대체해주는 것으로 중복 key를 처리.
		}
	}
	
	//key로 데이터를 가져오는 메소드
	String get(String key) {
		int hashcode = getHashCode(key); //key로 hashcode를 받아온다.
		int index = convertToIndex(hashcode); //받아온 hashcode로 index를 받아온다.
		System.out.println(key + ", hashcode(" + hashcode + "), index" + index + ")" );
		LinkedList<Node> list = data[index]; //index에서 LinkedList를 가져온다.
		Node node = searchKey(list, key); //LinkedList 안에 해당 key를 가진 노드를 가져온다.
		return node == null ? "Not found" : node.value(); //값이 있으면 값을 반환하고 없으면 Not found를 반환한다.
	}
}

public class Test {

	public static void main(String[] args) {
		HashTable h = new HashTable(3);
		h.put("sung", "She is pretty");
		h.put("jin", "She is a model");
		h.put("hee", "She is an angel");
		h.put("min", "She is cute");
		h.put("sung", "She is beautiful"); //중복
		
		System.out.println(h.get("sung"));
		System.out.println(h.get("jin"));
		System.out.println(h.get("hee"));
		System.out.println(h.get("min"));
		System.out.println(h.get("jae"));
	}

}
