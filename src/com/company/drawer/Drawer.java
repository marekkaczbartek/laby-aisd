package com.company.drawer;

public class Drawer {
    public static void drawTriangle(int size) {
        // TODO
        if (size <= 0) {
            System.out.println("fail");
            return;
        }
        for (int i = 1; i <= size; i++) {
            System.out.println("#".repeat(i));
        }
    }

    public static void drawSquare(int size) {
        // TODO
        if (size <= 0) {
            System.out.println("fail");
            return;
        }
        for (int i = 1; i <= size; i++) {
            if (i == 1 || i == size) {
                System.out.println("#".repeat(size));
            }
            else {
                System.out.println("#" + " ".repeat(size-2) + "#");
            }
        }
    }

    public static void drawPyramid(int size) {
        // TODO
        if (size <= 0) {
            System.out.println("fail");
            return;
        }
        
        for (int i = 0; i < size; i++) {
            System.out.println((" ".repeat(size - 1 - i)) + "#".repeat(1 + 2 * i));
        }
    }

    public static void drawPyramid(int size, int start) {
        if (size <= 0) {
            System.out.println("fail");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println((" ".repeat(size - 1 - i + start)) + "#".repeat(1 + 2 * i));
        }

    }

    public static void drawChristmasTree(int size) {
        // TODO
        if (size <= 0) {
            System.out.println("fail");
            return;
        }
        for (int i = 1; i <= size; i++) {
            drawPyramid(i, size-i);
        }
    }
    public static void drawRectangle(int width, int height) {
        if (width <= 0 || height <= 0) {
            System.out.println("fail");
            return;
        }
        
        for (int i = 0; i < height; i++) {
            if (i == 0 || i == height - 1) {
                System.out.println("#".repeat(width));
            } else {
                System.out.println("#" + " ".repeat(width - 2) + "#");
            }
        }
    }
}
