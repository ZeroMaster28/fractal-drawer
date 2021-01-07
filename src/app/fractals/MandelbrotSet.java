package app.fractals;

public class MandelbrotSet implements Fractal {

    private final int THRESHOLD = 200;

    @Override
    public double getPointAcceptanceRatio(ComplexNumber toCheck) {
        ComplexNumber c = new ComplexNumber();
        for(int i=0; i<THRESHOLD; i++) {
            c.square();
            c.add(toCheck);
            if(c.getModulus() > 1000) {
                return i/(double) THRESHOLD;
            }
        }
        return 1.0;
    }
}
