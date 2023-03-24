package list.linkedlist.implementation;

public class Main {

	public static void main(String[] args) {
		
		LinkedList numbers = new LinkedList();
		//리스트의 앞에 데이터 추가
//		numbers.addFirst(30);
//		numbers.addFirst(20);
//		numbers.addFirst(10);
		
		//리스트의 끝에 데이터 추가
		numbers.addLast(10); //0
		numbers.addLast(20); //1
		numbers.addLast(30); //2
		numbers.add(1, 15);
		System.out.println(numbers);
	}

}
