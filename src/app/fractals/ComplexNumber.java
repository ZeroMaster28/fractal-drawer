package app.fractals;

/**
 * Class represents basic complex number. To reduce number of objects' creations
 * this not an immutable class. It means that every operation performed on this
 * class modifies it.
 */
public class ComplexNumber {
    private double re, im;

    public ComplexNumber() {
        this.re = 0.0;
        this.im = 0.0;
    }

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public void add(ComplexNumber c) {
        this.re += c.getRe();
        this.im += c.getIm();
    }

    public void subtract(ComplexNumber c) {
        this.re -= c.getRe();
        this.im -= c.getIm();
    }

    public void multiply(ComplexNumber c) {
        double re = this.re;
        double im = this.im;
        this.re = re*c.getRe() - im*c.getIm();
        this.im = re*c.getIm() + im*c.getRe();
    }

    public void square() {
        multiply(this);
    }

    public double getModulus() {
        return Math.sqrt(this.re*this.re + this.im*this.im);
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }
}
