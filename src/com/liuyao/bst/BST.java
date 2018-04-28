package com.liuyao.bst;

import java.util.LinkedList;

/**
 * Created By liuyao on 2018/4/28 16:34.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        //构造函数实现对node节点的复制
        public Node(Node node){
            this.key=node.key;
            this.value=node.value;
            this.left=node.left;
            this.right=node.right;
        }
    }

    private Node root; //根节点
    private int count; //树的总的节点个数

    public BST() {
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    // 向二分搜索树中插入一个新的(key, value)数据对
    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    // 查看二分搜索树中是否存在键key
    public boolean contain(Key key) {
        return contain(root, key);
    }

    // 在二分搜索树中搜索键key所对应的值。如果这个值不存在, 则返回null
    public Value search(Key key) {
        return search(root, key);
    }

    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }


    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    // 二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }

    // 二分搜索树的层序遍历
    public void levelOrder() {
        LinkedList<Node> list = new LinkedList<Node>();
        list.add(root);
        while (!list.isEmpty()) {
            Node node = list.removeFirst();

            System.out.println(node.key);

            if (node.left != null)
                list.add(node.left);
            if (node.right != null)
                list.add(node.right);
        }
    }

    // 寻找二分搜索树的最小的键值
    public Key minimum(){
        assert count != 0;
        Node minNode=minimum(root);
        return minNode.key;
    }

    private Node minimum(Node node) {
        if (node.left ==null){
            return node;
        }
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大的键值
    public Key maximum(){
        assert count !=0;
        Node maxNode=maximum(root);
        return maxNode.key;
    }

    private Node maximum(Node node) {
        if (node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在节点
    public void removeMin(){
        if (root !=null){
            root=removeMin(root);
        }
    }

    private Node removeMin(Node node) {
        if (node.left == null){
            Node nodeRight=node.right;
            node.right=null;
            count--;
            return nodeRight;
        }
        node.left=removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值所在节点
    public void removeMax(){
        if (root != null){
            root=removeMax(root);
        }
    }

    private Node removeMax(Node node) {
        if (node.right == null){
            Node nodeLeft=node.left;
            node.left=null;
            count--;
            return nodeLeft;
        }
        node.right=removeMax(node.right);
        return node;
    }

    // 从二分搜索树中删除键值为key的节点
    public void remove(Key key){
        root = remove(root, key);
    }

    // 删除掉以node为根的二分搜索树中键值为key的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, Key key) {
        //未找到
        if(node==null){
            return null;
        }
        if (key.compareTo(node.key)<0){
            node.left=remove(node.left,key);
            return node;
        }else if (key.compareTo(key)>0){
            node.right=remove(node.right,key);
            return node;
        }else {
            // 待删除节点左子树为空的情况
            if (node.left == null){
                Node rightNode=node.right;
                node.right=null;
                count--;
                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if (node.right==null){
                Node leftNode=node.left;
                node.left=null;
                count--;
                return leftNode;
            }

            Node successor=new Node(minimum(node.right));
            successor.right=removeMin(node.right);
            successor.left=node.left;

            node.left=node.right=null;
            count--;
            return successor;
        }
    }

    // 查看以node为根的二分搜索树中是否包含键值为key的节点, 使用递归算法
    private boolean contain(Node node, Key key) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contain(node.left, key);
        } else {
            return contain(node.right, key);
        }
    }

    // 在以node为根的二分搜索树中查找key所对应的value, 递归算法
    // 若value不存在, 则返回NULL
    private Value search(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }


    // 向以node为根的二分搜索树中, 插入节点(key, value), 使用递归算法
    // 返回插入新节点后的二分搜索树的根
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            count++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }

        return node;
    }
}
