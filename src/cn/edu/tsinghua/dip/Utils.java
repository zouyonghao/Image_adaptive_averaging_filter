package cn.edu.tsinghua.dip;

class Utils {
    static int getMean(int[][] array) {
        int sum = 0;
        int size = array.length * array[0].length;
        for (int[] rows : array) {
            for (int v : rows) {
                sum += rows[v];
            }
        }

        return sum / size;
    }

    static int getVar(int mean, int[][] array) {
        int sum = 0;
        int size = array.length * array[0].length;
        for (int[] rows : array) {
            for (int v : rows) {
                sum += (rows[v] * rows[v] - mean * mean);
            }
        }

        return sum / size;
    }

    static int getLocalMean(int i, int j, int[][] pixels, int filterSize) {
        int halfSize = filterSize / 2;
        int left = i - halfSize;
        int right = i + halfSize;
        int top = j - halfSize;
        int bottom = j + halfSize;
        int sum = 0;
        int count = 0;
        for (int a = left; a < right; a++) {
            for (int b = top; b < bottom; b++) {
                if (a > 0 && b > 0 && a < pixels.length && b < pixels[0].length) {
                    sum += pixels[a][b];
                    count++;
                }
            }
        }
        return sum / count;
    }

    static int getLocalVar(int mean, int i, int j, int[][] pixels, int filterSize) {
        int halfSize = filterSize / 2;
        int left = i - halfSize;
        int right = i + halfSize;
        int top = j - halfSize;
        int bottom = j + halfSize;
        int sum = 0;
        int count = 0;
        for (int a = left; a < right; a++) {
            for (int b = top; b < bottom; b++) {
                if (a > 0 && b > 0 && a < pixels.length && b < pixels[0].length) {
                    sum += (pixels[a][b] * pixels[a][b] - mean * mean);
                    count++;
                }
            }
        }
        return sum / count;
    }
}
