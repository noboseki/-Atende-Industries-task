package org.atende.task.service;

import org.atende.task.data.BinaryTree;
import org.atende.task.service.utils.RecognizeObject;

import java.io.PrintStream;
import java.util.List;
import java.util.Stack;

public class DisplayBinaryTree {

    private static DisplayBinaryTree INSTANCE;

    private DisplayBinaryTree() {
    }

    public static DisplayBinaryTree getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DisplayBinaryTree();
        }
        return INSTANCE;
    }

    public static void displayRPNBinaryTree(List<String> tokens) {
        Stack<BinaryTree> stack = new Stack<>();

        for (String token : tokens) {
            BinaryTree model = new BinaryTree(token);
            if (RecognizeObject.isDigital(token)) {
                stack.push(model);
            } else {
                model.setLeft(stack.pop());
                model.setRight(stack.pop());
                stack.push(model);
            }
        }
        print(System.out, stack.pop());
    }

    private static String traversePreOrder(BinaryTree root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }

    private static void traverseNodes(StringBuilder sb, String padding, String pointer, BinaryTree node,
                                      boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    private static void print(PrintStream os, BinaryTree tree) {
        os.print(traversePreOrder(tree));
    }
}
