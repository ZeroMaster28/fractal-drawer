package app.fractals;

/**
 * Base interface for all fractals
 */
public interface Fractal {

    /**
     * Returns <code>boolean</code> indicating if given point belongs to the fractal.
     * @param c point to check
     * @return info whether it belongs or not
     */
    public default boolean hasPoint(ComplexNumber c) {
        return getPointAcceptanceRatio(c) >= 0.999;
    };

    /**
     * Returns ratio of how well this point belongs to the given fractal.
     * Usually it might be implemented as number of steps performed so far during
     * condition checking which is divided by all steps planned. So if it returns
     * <number>1.0</number> or something close to that value, it means that given
     * point belongs to the fractal.
     * @param c point to check
     * @return value between <number>0</number> and <number>1</number>
     */
    public double getPointAcceptanceRatio(ComplexNumber c);
}
