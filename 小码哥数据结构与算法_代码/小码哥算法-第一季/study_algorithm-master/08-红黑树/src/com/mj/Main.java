package com.mj;

import java.util.Comparator;

import com.mj.printer.BinaryTrees;
import com.mj.tree.AVLTree;
import com.mj.tree.BinarySearchTree;
import com.mj.tree.BinaryTree.Visitor;

public class Main {
	
	public static void main(String[] args) {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12 , 1
		};
		
		AVLTree<Integer> bst = new AVLTree<>();
		
		for(int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}

		BinaryTrees.println(bst);
		System.out.println(bst.height());
		
		bst.levelOrder(new Visitor<Integer>() {
			@Override
			public void visit(Integer element) {
				System.out.print("_" + element + "_");
			}
		});
		
		System.out.println();
		
		bst.inorder(new Visitor<Integer>() {
			@Override
			public void visit(Integer element) {
				System.out.print("_" + element + "_");
			}
		});
		System.out.println();
		System.out.println(bst);
		
		// 利用匿名类创建比较器
		@SuppressWarnings("unused")
		BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getAge() - p2.getAge();
			}
		});
	}

}
