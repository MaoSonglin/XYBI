package com.xinyibi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<T> {
	
	public TreeNode(T data) {
		super();
		this.data = data;
	}

	private T data;
	
	private TreeNode<T> left;
	
	private TreeNode<T> right;
	
	public boolean hasChild(T value) {
		if(getLeft()==null) return false;
		TreeNode<T> p = getLeft();
		while(p != null) {
			if(p.getData()!=null && p.getData().equals(value))return true;
			p = p.getRight();
		}
		return false;
	}
	public TreeNode<T> addChild(T d) {
		TreeNode<T> treeNode = new TreeNode<T>(d);
		TreeNode<T> left2 = getLeft();
		if(left2!=null) {
			TreeNode<T> right2 = left2.getRight();
			while(right2 != null) {
				left2 = right2;
				right2 = right2.getRight();
			}
			left2.setRight(treeNode);
		}else {
			setLeft(treeNode);
		}
		return treeNode;
	}
	
	public TreeNode<T> getChild(T value){
		TreeNode<T> left2 = getLeft();
		if(left2 == null)
			return null;
		TreeNode<T> right2 = left2.getRight();
		while(right2 != null) {
			left2 = right2;
			right2 = right2.getRight();
			if(value.equals(right2.getData())) return right2;
		}
		return null;
	}
}
