package com.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

        int value;
    LoggerTest left;
    LoggerTest right;

        public LoggerTest(int value) {
            this.value = value;
        }

    public static void main(String[] args) {
        LoggerTest root = new LoggerTest(1);
        root.left = new LoggerTest(2);
        root.right = new LoggerTest(3);
        root.left.left = new LoggerTest(4);
        root.left.right = new LoggerTest(5);
        root.right.left = new LoggerTest(6);
        root.right.right = new LoggerTest(7);

        System.out.println(findsum(root, 1, 0));

    }

    static int getNumber() {
        return 5;
    }

    static int findsum(LoggerTest root, int level, Integer sum ){

        if(root == null)
            return 0;
        if(level % 2 ==1)
            sum +=root.value;

        findsum(root.left, level+1, sum);
        findsum(root.right, level+1, sum);

        return sum;
    }

}