package app.fractals;

public class MandelbrotSet implements Fractal {

    private final int THRESHOLD = Fractal.APPROXIMATION_STEPS;

    @Override
    public double getPointAcceptanceRatio(ComplexNumber toCheck) {
        ComplexNumber c = new ComplexNumber();
        for(int i=0; i<THRESHOLD; i++) {
            c.square();
            c.add(toCheck);
            if(c.getModulus() > Fractal.MAX_MODULUS) {
                return i/(double) THRESHOLD;
            }
        }
        return 1.0;
    }
}
