package app.fractals;

public class JuliaSet implements Fractal {

    private final int THRESHOLD = 200;
    private final ComplexNumber setParameter;

    public JuliaSet() {
        this(new ComplexNumber(-0.8, 0.156));
    }

    public JuliaSet(ComplexNumber parameter) {
        this.setParameter = parameter;
    }

    @Override
    public double getPointAcceptanceRatio(ComplexNumber toCheck) {
        ComplexNumber c = new ComplexNumber(toCheck.getRe(), toCheck.getIm());
        for(int i=0; i<THRESHOLD; i++) {
            c.square();
            c.add(setParameter);
            if(c.getModulus() > 1000) {
                return i/(double) THRESHOLD;
            }
        }
        return 1.0;
    }
}
