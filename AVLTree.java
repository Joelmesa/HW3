/*
 * *** Joel Mesa / 001 ***
 *
 * This java file is a Java object implementing simple AVL Tree.
 * You are to complete the deleteElement method.
 *
 */

import java.lang.Math;


/**
 *  Class: AVLNode
 *
 *  This class represents an inner node of the AVL Tree.
 *  It replaces the generic "Node" class to avoid conflicts.
 */

class AVLNode {
    int value;                      // the node's value
    int height;                     // height of node based on its [sub]trees
    AVLNode leftChild, rightChild;   // left and right subtrees

    public AVLNode(int data) {       // parameterized constructor
        value = data;
        height = 0;
        leftChild = rightChild = null;
    }
}


/**
 *  Class 'LUC_AVLTree'
 *
 *  AVL Tree class definition with an updated `AVLNode` class.
 */

class LUC_AVLTree {
    private AVLNode rootNode;    // The root node of the AVL Tree

    public LUC_AVLTree() {
        rootNode = null;
    }

    public void removeAll() {
        rootNode = null;
    }

    public boolean checkEmpty() {
        return rootNode == null;
    }

    public void insert(int value) {
        rootNode = insertElement(value, rootNode);
    }

    public void delete(int value) {
        rootNode = deleteElement(value, rootNode);
    }

    public String preorderTraversal() {
        return preorderTraversal(rootNode);
    }

    private AVLNode insertElement(int value, AVLNode node) {
        if (node == null) {
            return new AVLNode(value);
        }

        if (value < node.value) {
            node.leftChild = insertElement(value, node.leftChild);
        } else if (value > node.value) {
            node.rightChild = insertElement(value, node.rightChild);
        } else {
            return node; // No duplicates allowed
        }

        node.height = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        int balance = getBalanceFactor(node);

        if (balance > 1 && value < node.leftChild.value) {
            return LLRotation(node);
        }
        if (balance < -1 && value > node.rightChild.value) {
            return RRRotation(node);
        }
        if (balance > 1 && value > node.leftChild.value) {
            return LRRotation(node);
        }
        if (balance < -1 && value < node.rightChild.value) {
            return RLRotation(node);
        }

        return node;
    }

    private AVLNode deleteElement(int value, AVLNode node) {
        if (node == null) return null;

        if (value < node.value) {
            node.leftChild = deleteElement(value, node.leftChild);
        } else if (value > node.value) {
            node.rightChild = deleteElement(value, node.rightChild);
        } else {
            if (node.leftChild == null || node.rightChild == null) {
                node = (node.leftChild != null) ? node.leftChild : node.rightChild;
            } else {
                AVLNode temp = minValueNode(node.rightChild);
                node.value = temp.value;
                node.rightChild = deleteElement(temp.value, node.rightChild);
            }
        }

        if (node == null) return node;

        node.height = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        int balance = getBalanceFactor(node);

        if (balance > 1 && getBalanceFactor(node.leftChild) >= 0) return LLRotation(node);
        if (balance > 1 && getBalanceFactor(node.leftChild) < 0) return LRRotation(node);
        if (balance < -1 && getBalanceFactor(node.rightChild) <= 0) return RRRotation(node);
        if (balance < -1 && getBalanceFactor(node.rightChild) > 0) return RLRotation(node);

        return node;
    }

    private AVLNode minValueNode(AVLNode node) {
        while (node.leftChild != null) node = node.leftChild;
        return node;
    }

    private int getBalanceFactor(AVLNode node) {
        return (node == null) ? 0 : getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    private int getHeight(AVLNode node) {
        return (node == null) ? -1 : node.height;
    }

    private int getMaxHeight(int left, int right) {
        return Math.max(left, right);
    }

    private AVLNode LLRotation(AVLNode node) {
        AVLNode leftChild = node.leftChild;
        node.leftChild = leftChild.rightChild;
        leftChild.rightChild = node;

        node.height = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        leftChild.height = getMaxHeight(getHeight(leftChild.leftChild), getHeight(leftChild.rightChild)) + 1;
        return leftChild;
    }

    private AVLNode RRRotation(AVLNode node) {
        AVLNode rightChild = node.rightChild;
        node.rightChild = rightChild.leftChild;
        rightChild.leftChild = node;

        node.height = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        rightChild.height = getMaxHeight(getHeight(rightChild.leftChild), getHeight(rightChild.rightChild)) + 1;
        return rightChild;
    }

    private AVLNode LRRotation(AVLNode node) {
        node.leftChild = RRRotation(node.leftChild);
        return LLRotation(node);
    }

    private AVLNode RLRotation(AVLNode node) {
        node.rightChild = LLRotation(node.rightChild);
        return RRRotation(node);
    }

    private String preorderTraversal(AVLNode node) {
        if (node == null)
            return "";

        return node.value + " " + preorderTraversal(node.leftChild) + preorderTraversal(node.rightChild);
    }
}
