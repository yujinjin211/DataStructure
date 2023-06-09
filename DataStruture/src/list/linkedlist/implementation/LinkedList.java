package list.linkedlist.implementation;

public class LinkedList {
	
	//첫번째 노드를 가리키는 필드
	private Node head;
	private Node tail;
	private int size = 0;
	
	private class Node {
		//데이터가 저장될 필드
		private Object data;
		
		//다음 노드를 가리키는 필드
		private Node next;
		public Node(Object input) {
			this.data = input;
			this.next = null;
		}
		
		//노드의 내용을 쉽게 출력해서 확인해볼 수 있는 기능
		public String toString() {
			return String.valueOf(this.data);
		}
	}
	
	//리스트의 맨 앞에 노드 추가
	public void addFirst(Object input) {
		Node newNode = new Node(input); //Node 객체가 생성되는 시점에 data의 값이 input이 되고 next의 값은 null이 된다.
		
		newNode.next = head; //새로운 노드의 다음 노드로 head를 지정한다.
		head = newNode; //head로 새로운 노드를 지정한다.(data의 값은 head가 되고 next의 값은 null이 된다.)
		size++; //size가 0->1이 된다.
		if(head.next == null) {
			tail = head; //만약 next의 값이 null이면 tail의 값을 head의 값으로 초기화한다.
		}
	}
	
	//리스트의 맨 끝에 노드 추가
	//tail 없이도 구현 가능하지만 그럴 경우 마지막 노드의 값을 순차적으로 탐색해야 하기 때문에 효율적이지 않다.
	public void addLast(Object input) {
		Node newNode = new Node(input);
		
		if(size == 0) { //만일 리스트의 사이즈가 0이면(노드가 없으면)
			addFirst(input); //리스트의 맨 앞에 추가
		} else { //리스트에 노드가 있으면
			tail.next = newNode; //마지막 노드의 다음 노드로 newNode를 지정.
			tail = newNode; //마지막 노드의 값을 갱신한다.
			size++; //리스트의 사이즈를 증가시킨다.
		}
	}
	
	//특정 위치의 노드를 찾기
	Node node(int index) { //Node클래스를 리턴하는 node. 원래 내부에서만 사용하는 api면 public으로 하면 안된다.
		//특정 위치의 노드를 찾으려면 먼저 리스트의 첫 번째 노드를 찾아야한다.
		Node x = head;
		for(int i = 0; i < index; i ++) { 
			x = x.next; //n 번째 노드를 찾으려면...head가 가리키는 노드의 next를 가져오면 된다.
		}
		return x;
	}
	
	//특정 위치에 원하는 노드 추가하기
	public void add(int k, Object input) {
		if(k == 0) {
			addFirst(input);
		} else { //추가하려는 위치가 맨 앞이 아닌 경우.
			
			Node temp1 = node(k-1); //ex) k가 2인 경우 1번째 노드의 값이 temp1에 대입된다.
			
			//k번째 노드를 temp2로 지정한다.
			Node temp2 = temp1.next;
			//새로운 노드를 생성한다.
			Node newNode = new Node(input);
			//temp1의 다음 노드로 새로 생성한 노드를 지정한다.
			temp1.next = newNode;
			//새로 추가한 노드의 다음 노드로 temp2를 지정한다.
			newNode.next = temp2;
			size++;
			
			//만약 리스트의 제일 끝에 노드를 추가하는거면
			if(newNode.next == null) { //다음 노드가 없으면 제일 끝에 있는 노드라는 뜻이 된다.
				tail = newNode;
			}
		}
	}
	
	public String toString() {
		if(head == null) {
			return "[]";
		}
		//head가 누군지 찾아야한다.
		Node temp = head;
		String str = "[";
		
		while(temp.next != null) {
			str += temp.data + ", "; // 현재 temp의 값
			temp = temp.next;
		}
		
		//마지막 노드는 next값이 없어서 while문에 포함되지 않기 때문에 출력결과에 포함시키기 위해 while문 밖에 적는다.
		str += temp.data;
		
		return str + "]";
	}
	
	//처음 노드 삭제하기
	public Object removeFirst() {
		//temp에 head의 값을 저장하고, 원래 head는 head가 가리키던 다음 노드의 값을 저장한다.
		Node temp = head;
		head = head.next;
		
		Object returnData = temp.data; //반환할 값인 returnData에는 temp의 값을 저장한다.
		temp = null; //temp를 비운다.
		size--; //노드를 삭제했으므로 리스트의 사이즈를 줄인다.
		
		return returnData;
	}
	
	//중간 노드 삭제하기
	public Object remove(int k) {
		if(k == 0) {
			return removeFirst();
		}
		//삭제할 노드의 이전 노드를 temp에 저장한다.
		Node temp = node(k-1); //node 메소드는 특정 위치의 노드 값을 찾아준다.(위에서 만들었음)
		//삭제할 노드를 todoDeleted에 저장한다. 
		Node todoDeleted = temp.next;
		//바로 todoDeleted를 삭제하지 않는다. todoDeleted의 다음 노드를 temp(todoDeleted의 이전 노드)의 다음 노드가 되도록 한다.
		temp.next = temp.next.next;
		Object returnData = todoDeleted.data;
		
		//만약 삭제하려는 노드가 맨 마지막 노드라면(tail이라면) temp를 맨 마지막 노드로 지정해줘야한다.
		if(todoDeleted == tail) {
			tail = temp;
		}
		//todoDeleted의 값을 null로 바꾼다.
		todoDeleted = null;
		size--; //리스트의 사이즈를 감소시킨다.
		
		return returnData;
	}
	
	//맨 마지막 노드 삭제하기
	public Object removeLast() {
		return remove(size-1);
	}
	
	//엘리먼트의 크기 알아내기
	public int size() {
		return size;
	}
	
	//특정 위치의 엘리먼트 가져오기
	public Object get(int k) {
		Node temp = node(k);
		return temp.data;
	}
	
	//특정한 값을 가진 엘리먼트의 인덱스 값 알아내기
	public int indexOf(Object data) {
		//temp를 head로 지정한다.
		Node temp = head;
		int index = 0;
		//찾고자 하는 노드가 몇 번째 인덱스에 있는지 하나하나 찾는다.
		while(temp.data != data) { //temp에 담긴 data가 넘겨받은 data와 같으면 while문을 빠져나온다.
			temp = temp.next;
			index++;
			if(temp == null) {
				return -1; //리스트에 없는 값이면 -1을 반환한다.
			}
		}
		return index;
	}
	
	//Iterator 객체 생성과 next 메소드
	public ListIterator listIterator() {
		return new ListIterator();
	}
	
	class ListIterator {
		
		private Node next;
		private Node lastReturned;
		private int nextIndex;
		
		public ListIterator() {
			next = head; //ListIterator 객체가 생성되면 next는 head를 가리킨다.
		}
		
		public Object next() { //next() 메소드를 호출하면
			lastReturned = next; //lastReturned의 값이 next가 되고
			next = next.next; //원래의 next는 현재 노드의 다음 노드를 가리키게 된다.
			nextIndex++;
			return lastReturned.data;
		}
		
		public boolean hasNext() {
			return nextIndex < size;
		}
		
		public void add(Object input) {
			Node newNode = new Node(input); //입력받은 값으로 새 노드를 생성한다.
			
			if(lastReturned == null) {
				head = newNode; //새로 만들어진 노드를 head로 지정
				newNode.next = next; //새 노드가 가리키는 다음 노드의 값으로 next를 지정한다.				
			} else {
				lastReturned.next = newNode;
				newNode.next = next;
			}
			
			lastReturned = newNode;
			nextIndex++;
			size++;
		}
		
		public void remove() {
			if(nextIndex == 0) {
				throw new IllegalStateException();
			}
			LinkedList.this.remove(nextIndex-1);
			nextIndex--;
		}
	}
	
}
