package cn.edu.tsinghua.dip;

public class MeanFilter implements Filter {

    private int[][] pixels;
    private int filterSize;

    MeanFilter(int[][] pixels, int filterSize) {
        this.pixels = pixels;
        this.filterSize = filterSize;
    }

    @Override
    public int getPixel(int i, int j) {
        return Utils.getLocalMean(i, j, pixels, filterSize);
    }

}
