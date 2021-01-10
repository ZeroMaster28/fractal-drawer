package app.threads;

import app.fractals.ComplexNumber;
import app.fractals.Fractal;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.RecursiveAction;

public class ForkDrawFractal extends RecursiveAction {

    private final ComplexNumber[][] complexPlane;
    private final Fractal fractal;
    private final int precessNum;
    private final double[][] resultImage;

    public ForkDrawFractal(ComplexNumber[][] complexPlane, Fractal fractal, int processNum, double[][] result) {
        this.complexPlane = complexPlane;
        this.fractal = fractal;
        this.precessNum = processNum;
        this.resultImage = result;
    }

    @Override
    protected void compute() {
        Collection<PartialDrawer> drawers = new LinkedList<>();
        int pixelsWidth = resultImage[0].length;
        for(int i=0; i<precessNum; i++) {
            drawers.add(new PartialDrawer(i*pixelsWidth/precessNum,
                    (i+1)*pixelsWidth/precessNum));
        }
        invokeAll(drawers);
    }

    /**
     * Partial drawer is class that is responsible for evaluating particular image slice
     */
    private class PartialDrawer extends RecursiveAction {
        private final int colFrom;
        private final int colTo;

        private PartialDrawer(int colFrom, int colTo) {
            this.colFrom = colFrom;
            this.colTo = colTo;
        }

        @Override
        protected void compute() {
            for (int i=0; i<complexPlane.length; i++) {
                for(int j=colFrom; j<colTo; j++) {
                    resultImage[i][j] = fractal.getPointAcceptanceRatio(complexPlane[i][j]);
                }
            }
        }
    }
}
