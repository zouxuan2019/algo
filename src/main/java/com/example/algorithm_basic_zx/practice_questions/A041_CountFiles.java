package com.example.algorithm_basic_zx.practice_questions;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class A041_CountFiles {
    public static void main(String[] args) {
        String path = "/Users/zouxuan/ZouXuan_Projects/workspace/algorithm_basic_zx/src/main/java";
        System.out.println(getFileNumber(path));
    }

    public static int getFileNumber(String folderPath) {
        File root = new File( folderPath );
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Queue<File> linkedList = new LinkedList<>();
        linkedList.add( root );
        int count = 0;
        while (!linkedList.isEmpty()) {
            File folder = linkedList.poll();
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    linkedList.add( file );
                } else if (file.isFile()) {
                    count++;
                }
            }
        }
        return count;
    }
}
