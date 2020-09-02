package com.rolex.microlabs.slidingwindow.case3;

import javafx.scene.control.Cell;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class LongMaxUpdater extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    /**
     * Version of max for use in retryUpdate
     */
    @Override
    final long fn(long v, long x) { return v > x ? v : x; }

    /**
     * Creates a new instance with initial maximum of {@code
     * Long.MIN_VALUE}.
     */
    public LongMaxUpdater() {
        base = Long.MIN_VALUE;
    }

    /**
     * Updates the maximum to be at least the given value.
     *
     * @param x the value to update
     */
    public void update(long x) {
        Cell[] as; long b, v; HashCode hc; Cell a; int n;
        if ((as = cells) != null ||
            (b = base) < x && !casBase(b, x)) {
            boolean uncontended = true;
            int h = (hc = threadHashCode.get()).code;
            if (as == null || (n = as.length) < 1 ||
                (a = as[(n - 1) & h]) == null ||
                ((v = a.value) < x && !(uncontended = a.cas(v, x))))
                retryUpdate(x, hc, uncontended);
        }
    }

    /**
     * Returns the current maximum.  The returned value is
     * <em>NOT</em> an atomic snapshot: Invocation in the absence of
     * concurrent updates returns an accurate result, but concurrent
     * updates that occur while the value is being calculated might
     * not be incorporated.
     *
     * @return the maximum
     */
    public long max() {
        Cell[] as = cells;
        long max = base;
        if (as != null) {
            int n = as.length;
            long v;
            for (int i = 0; i < n; ++i) {
                Cell a = as[i];
                if (a != null && (v = a.value) > max)
                    max = v;
            }
        }
        return max;
    }

    /**
     * Resets variables maintaining updates to {@code Long.MIN_VALUE}.
     * This method may be a useful alternative to creating a new
     * updater, but is only effective if there are no concurrent
     * updates.  Because this method is intrinsically racy, it should
     * only be used when it is known that no threads are concurrently
     * updating.
     */
    public void reset() {
        internalReset(Long.MIN_VALUE);
    }

    /**
     * Equivalent in effect to {@link #max} followed by {@link
     * #reset}. This method may apply for example during quiescent
     * points between multithreaded computations.  If there are
     * updates concurrent with this method, the returned value is
     * <em>not</em> guaranteed to be the final value occurring before
     * the reset.
     *
     * @return the maximum
     */
    public long maxThenReset() {
        Cell[] as = cells;
        long max = base;
        base = Long.MIN_VALUE;
        if (as != null) {
            int n = as.length;
            for (int i = 0; i < n; ++i) {
                Cell a = as[i];
                if (a != null) {
                    long v = a.value;
                    a.value = Long.MIN_VALUE;
                    if (v > max)
                        max = v;
                }
            }
        }
        return max;
    }

    /**
     * Returns the String representation of the {@link #max}.
     * @return the String representation of the {@link #max}
     */
    @Override
    public String toString() {
        return Long.toString(max());
    }

    /**
     * Equivalent to {@link #max}.
     *
     * @return the maximum
     */
    @Override
    public long longValue() {
        return max();
    }

    /**
     * Returns the {@link #max} as an {@code int} after a narrowing
     * primitive conversion.
     */
    @Override
    public int intValue() {
        return (int)max();
    }

    /**
     * Returns the {@link #max} as a {@code float}
     * after a widening primitive conversion.
     */
    @Override
    public float floatValue() {
        return (float)max();
    }

    /**
     * Returns the {@link #max} as a {@code double} after a widening
     * primitive conversion.
     */
    @Override
    public double doubleValue() {
        return (double)max();
    }

    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        s.defaultWriteObject();
        s.writeLong(max());
    }

    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        busy = 0;
        cells = null;
        base = s.readLong();
    }

}