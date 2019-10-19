package cn.edu.tsinghua.dip;

public class AdaptiveAveragingFilter implements Filter {

    private int[][] pixels;
    private int filterSize;

    private double globalVar;

    AdaptiveAveragingFilter(int[][] pixels, int filterSize) {
        this.pixels = pixels;
        this.filterSize = filterSize;
        int globalMean = Utils.getMean(pixels);
        globalVar = Utils.getVar(globalMean, pixels);
    }

    @Override
    public int getPixel(int i, int j) {
        int localMean = Utils.getLocalMean(i, j, pixels, filterSize);
        int localVar = Utils.getLocalVar(localMean, i, j, pixels, filterSize);

        // f(x,y) = g(x,y) - (globalVar / localVar)*(g(x,y) - localMean)
        double temp = (globalVar / localVar - 1) * ((1d) / (1 + Math.exp(globalVar - localVar))) + 1;
        // double temp = (globalVar / localVar);
        // if (temp > 1) {
        //     temp = 1;
        // }
        return (int) (pixels[i][j] - temp * (pixels[i][j] - localMean));
    }

}
