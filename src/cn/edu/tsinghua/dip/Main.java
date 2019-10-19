package cn.edu.tsinghua.dip;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String imageName = "TestImg/jp_agwn";
        String imageFileExtension = ".png";
        BufferedImage srcImg = ImageIO.read(new File(imageName + imageFileExtension));

        int[][] redPixels = new int[srcImg.getWidth()][srcImg.getHeight()];
        int[][] greenPixels = new int[srcImg.getWidth()][srcImg.getHeight()];
        int[][] bluePixels = new int[srcImg.getWidth()][srcImg.getHeight()];

        for (int i = 0; i < srcImg.getWidth(); i++) {
            for (int j = 0; j < srcImg.getHeight(); j++) {
                int pixel = srcImg.getRGB(i, j);
                redPixels[i][j] = (pixel & 0x00ff0000) >> 16;
                greenPixels[i][j] = (pixel & 0x0000ff00) >> 8;
                bluePixels[i][j] = (pixel & 0x000000ff);
            }
        }

        BufferedImage destImg = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_ARGB);

        int filterSize = 7;
        // Filter redMeanFilter = new MeanFilter(redPixels, filterSize);
        // Filter greenMeanFilter = new MeanFilter(greenPixels, filterSize);
        // Filter blueMeanFilter = new MeanFilter(bluePixels, filterSize);

        Filter redMeanFilter = new AdaptiveAveragingFilter(redPixels, filterSize);
        Filter greenMeanFilter = new AdaptiveAveragingFilter(greenPixels, filterSize);
        Filter blueMeanFilter = new AdaptiveAveragingFilter(bluePixels, filterSize);

        int newAlpha = (-1) << 24;
        for (int i = 0; i < srcImg.getWidth(); i++) {
            for (int j = 0; j < srcImg.getHeight(); j++) {
                int red = redMeanFilter.getPixel(i, j);
                int green = greenMeanFilter.getPixel(i, j);
                int blue = blueMeanFilter.getPixel(i, j);
                destImg.setRGB(i, j, newAlpha | (red << 16) | (green << 8) | blue);
            }
        }

        ImageIO.write(destImg, "png", new File(imageName + "_processed.png"));
        System.out.println("finished!");
    }

}
