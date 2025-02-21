/*
 * *** Joel Mesa / 001 ***
 *
 * This java file contains several simple tree problems that need to be
 * codified. These routines  must use the TreeMap and TreeSet library
 * classes from the Java Collection Framework.
 *
 */

import java.util.*;

class TreeProblems {
    public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {
        Set<Integer> result = new TreeSet<>(setA);
        result.addAll(setB);
        Set<Integer> intersection = new TreeSet<>(setA);
        intersection.retainAll(setB);
        result.removeAll(intersection);
        return result;
    }

    public static void removeEven(Map<Integer, String> treeMap) {
        treeMap.keySet().removeIf(key -> key % 2 == 0);
    }

    public boolean treesEqual(Map<Integer, String> tree1, Map<Integer, String> tree2) {
        return tree1.equals(tree2);
    }
}

// Node class
class Node {
    int value;
    int height;
    Node leftChild, rightChild;

    public Node(int data) {
        value = data;
        height = 0;
        leftChild = rightChild = null;
    }
}
