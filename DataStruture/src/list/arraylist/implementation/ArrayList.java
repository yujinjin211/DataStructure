package list.arraylist.implementation;

public class ArrayList {
	
	private int size = 0; //몇 개의 데이터가 리스트에 들어있느냐
	private Object[] elementData = new Object[100]; //Object타입의 크기가 100인 배열을 생성해서 elementData라는 이름에 할당...
	
	public boolean addLast(Object element) {
		elementData[size] = element; //ex)size가 0이면 0번째 자리에 element값이 저장된다.
		size++; //사이즈를 증가시킨다.
		return true;
	}
	
	public boolean add(int index, Object element) {
		//엘리먼트 중간에 데이터를 추가하려면 끝의 엘리먼트부터 index의 노드까지 뒤로 한 칸씩 이동시켜야 한다.
		for(int i = size-1; i >= index; i--) {
			elementData[i+1] = elementData[i];
		}
		
		//index에 노드를 추가한다.
		elementData[index] = element;
		size++;
		
		return true;
	}
	
	public boolean addFirst(Object element) {
		return add(0, element);
	}
	
	public String toString() {
		
		String str = "[";
		
		for(int i = 0; i < size; i++) {
			str += elementData[i];
			if(i < size-1) {
				str += ", ";
			}
		}
		
		return str + "]";
	}
	
	public Object remove(int index) {
		//엘리먼트를 삭제하기 전에 삭제할 데이터를 removed 변수에 저장한다.
		Object removed = elementData[index];
		
		//데이터를 삭제하면 배열의 길이도 줄어든다.
		for(int i = index+1; i <= size-1; i++) { //삭제하려는 위치의 그 다음 위치부터 반복문이 시작되어야 한다.
			elementData[i-1] = elementData[i];
		}
		size--;
		
		//마지막 위치의 엘리먼트를 명시적으로 삭제
		elementData[size] = null;
		return removed;
		
	}
	
	public Object removeFirst() {
		return remove(0);
	}
	
	public Object removeLast() {
		return remove(size-1);
	}
	
	public Object get(int index) {
		return elementData[index];
	}
	
	public int size() {
		return size;
	}
	
	public int indexOf(Object o) {
		
		for(int i = 0; i < size; i++) { //배열의 크기만큼 반복
			if(o.equals(elementData[i])) { //인자로 받은 o의 값과 i번째 elementData의 값이 일치하면
				return i; //i를 리턴
			}
		}
		
		return -1;
	}
	
	public ListIterator listIterator() {
		return new ListIterator();
	}
	
	class ListIterator {
		
		private int nextIndex = 0;
		
		public Object next() {
//			Object returnData =  elementData[nextIndex];
//			nextIndex++;
//			return returnData;
			
			return elementData[nextIndex++];
		}
		
		public boolean hasNext() {
			return nextIndex < size();
		}
		
		public Object previous() {
			return elementData[--nextIndex];
		}
		
		public boolean hasPrevious() {
			return nextIndex > 0;
		}
	}
	
}
