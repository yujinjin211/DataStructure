package tree.binarytree.implementation;


class Node {
	//이진트리의 노드는 data와 왼쪽, 오른쪽 두 개의 child 노드를 가진다.
	int data;
	Node left;
	Node right;
}

class Tree {

	public Node root;
	
	//트리의 시작점인 루트 노드를 지정한다.
	public void setRoot(Node node) {
		this.root = node;
	}
	
	//루트 노드를 리턴한다.
	public Node getRoot() {
		return root;
	}
	
	//노드를 생성한다.
	public Node makeNode(Node left, int data, Node right) {
		Node node = new Node();
		node.data = data;
		node.left = left;
		node.right = right;
		return node;
	}
	
	//중위 순회
	public void inorder(Node node) {
		if (node != null) { //노드가 null이 아닐때 까지 재귀 호출 반복.
			inorder(node.left); //왼쪽 자식 노드 탐색
			System.out.println(node.data); //현재 노드 방문
			inorder(node.right); //오른쪽 자식 노드 탐색
		}
	}
	
	//전위 순회
	public void preorder(Node node) {
		if (node != null) {
			System.out.println(node.data); //현재 노드 방문
			preorder(node.left); //왼쪽 자식 노드 탐색
			preorder(node.right); //오른쪽 자식 노드 탐색
		}
	}
	
	//후위 순회
	public void postorder(Node node) {
		if(node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.println(node.data);
		}
	}

}

public class Test {
	
	public static void main(String[] args) {
		Tree t = new Tree();
		//makeNode 메소드는 left, data, right 순으로 작성하여 호출한다.
		//마지막 노드부터 생성
		Node n4 = t.makeNode(null, 4, null);
		Node n5 = t.makeNode(null, 5, null);
		Node n2 = t.makeNode(n4, 2, n5);
		Node n3 = t.makeNode(null, 3, null);
		Node n1 = t.makeNode(n2, 1, n3);
		
		t.setRoot(n1);
		t.postorder(t.getRoot());
		
	}

}
