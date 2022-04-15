package Stacks;

import java.util.*;

public class operations {
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       int n=sc.nextInt();
       int [] arr=new int [n];
       for(int i=0;i<n;i++){
           arr[i]=sc.nextInt();
       }
        System.out.println(Arrays.toString(prevGreaterElement(arr)));
        System.out.println(largestHistogram(arr));
    }

    public static boolean isValidParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == '(' || curr == '{' || curr == '[') {
                stack.push(curr);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.peek();
                if ((top == '(' && curr == ')') ||
                        (top == '[' && curr == ']') ||
                        (top == '{' && curr == '}')) {
                    stack.pop();
                } else return false;
            }
        }
        return stack.isEmpty();
    }

    public static int[] nextGreaterElement(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ci = 1;
        int[] res = new int[arr.length];
        while (ci < arr.length) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[ci]) {
                res[stack.pop()] = arr[ci];
            }
            stack.push(ci);
            ci++;
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }

    public static int[] nextSmallestElement(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ci = 1;
        int[] res = new int[arr.length];
        while (ci < arr.length) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[ci]) {
                res[stack.pop()] = ci;
            }
            stack.push(ci);
            ci++;
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = arr.length;
        }
        return res;
    }

    public static int[] prevGreaterElement(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr.length - 1);
        int ci = arr.length - 2;
        int[] res = new int[arr.length];
        while (ci >= 0) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[ci]) {
                res[stack.pop()] = arr[ci];
            }
            stack.push(ci);
            ci--;
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }

    public static int[] prevSmallestElement(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr.length - 1);
        int ci = arr.length - 2;
        int[] res = new int[arr.length];
        while (ci >= 0) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[ci]) {
                res[stack.pop()] = ci;
            }
            stack.push(ci);
            ci--;
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }

    public static int largestHistogram(int[] arr) {
        int[] lsi = prevSmallestElement(arr);
        int[] rsi = nextSmallestElement(arr);
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int area = (rsi[i] - lsi[i] - 1) * arr[i];
            max = Math.max(max, area);
        }
        return max;
    }
}
