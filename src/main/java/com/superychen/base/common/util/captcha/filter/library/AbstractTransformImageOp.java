package com.superychen.base.common.util.captcha.filter.library;


public abstract class AbstractTransformImageOp extends AbstractImageOp {

    protected abstract void transform(int x, int y, double[] t);

    protected void init() {
    }

    private boolean initialized;

    AbstractTransformImageOp() {
        setEdgeMode(EDGE_CLAMP);
    }

    @Override
    protected void filter(int[] inPixels, int[] outPixels, int width, int height) {
        if (!initialized) {
            init();
            initialized = true;
        }
        double[] t = new double[2];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                transform(x, y, t);
                int pixel = getPixelBilinear(inPixels, t[0], t[1], width, height, getEdgeMode());
                outPixels[x + y * width] = pixel;
            }
        }
    }

}

