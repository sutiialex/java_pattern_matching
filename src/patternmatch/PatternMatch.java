package patternmatch;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PatternMatch {
    public static <T, V1 extends T, V2 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Default<R> defaultF) {
        if (c1.type.equals(t.getClass())) {
            return c1.f.apply((V1) t);
        } else if (c2.type.equals(t.getClass())) {
            return c2.f.apply((V2) t);
        } else {
            return defaultF.f.get();
        }
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Default<R> defaultF) {
        if (c1.type.equals(t.getClass())) {
            return c1.f.apply((V1) t);
        } else if (c2.type.equals(t.getClass())) {
            return c2.f.apply((V2) t);
        } else if (c3.type.equals(t.getClass())) {
            return c3.f.apply((V3) t);
        } else {
            return defaultF.f.get();
        }
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, V4 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Case<V4, R> c4, final Default<R> defaultF) {
        if (c1.type.equals(t.getClass())) {
            return c1.f.apply((V1) t);
        } else if (c2.type.equals(t.getClass())) {
            return c2.f.apply((V2) t);
        } else if (c3.type.equals(t.getClass())) {
            return c3.f.apply((V3) t);
        } else if (c4.type.equals(t.getClass())) {
            return c4.f.apply((V4) t);
        } else {
            return defaultF.f.get();
        }
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, V4 extends T, V5 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Case<V4, R> c4, final Case<V5, R> c5, final Default<R> defaultF) {
        if (c1.type.equals(t.getClass())) {
            return c1.f.apply((V1) t);
        } else if (c2.type.equals(t.getClass())) {
            return c2.f.apply((V2) t);
        } else if (c3.type.equals(t.getClass())) {
            return c3.f.apply((V3) t);
        } else if (c4.type.equals(t.getClass())) {
            return c4.f.apply((V4) t);
        } else if (c5.type.equals(t.getClass())) {
            return c5.f.apply((V5) t);
        } else {
            return defaultF.f.get();
        }
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, V4 extends T, V5 extends T, V6 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Case<V4, R> c4, final Case<V5, R> c5, final Case<V6, R> c6, final Default<R> defaultF) {
        if (c1.type.equals(t.getClass())) {
            return c1.f.apply((V1) t);
        } else if (c2.type.equals(t.getClass())) {
            return c2.f.apply((V2) t);
        } else if (c3.type.equals(t.getClass())) {
            return c3.f.apply((V3) t);
        } else if (c4.type.equals(t.getClass())) {
            return c4.f.apply((V4) t);
        } else if (c5.type.equals(t.getClass())) {
            return c5.f.apply((V5) t);
        } else if (c6.type.equals(t.getClass())) {
            return c6.f.apply((V6) t);
        } else {
            return defaultF.f.get();
        }
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, V4 extends T, V5 extends T, V6 extends T, V7 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Case<V4, R> c4, final Case<V5, R> c5, final Case<V6, R> c6, final Case<V7, R> c7, final Default<R> defaultF) {
        if (c1.type.equals(t.getClass())) {
            return c1.f.apply((V1) t);
        } else if (c2.type.equals(t.getClass())) {
            return c2.f.apply((V2) t);
        } else if (c3.type.equals(t.getClass())) {
            return c3.f.apply((V3) t);
        } else if (c4.type.equals(t.getClass())) {
            return c4.f.apply((V4) t);
        } else if (c5.type.equals(t.getClass())) {
            return c5.f.apply((V5) t);
        } else if (c6.type.equals(t.getClass())) {
            return c6.f.apply((V6) t);
        } else if (c7.type.equals(t.getClass())) {
            return c7.f.apply((V7) t);
        } else {
            return defaultF.f.get();
        }
    }

    public static class Case<V, R> {
        final Class<V> type;
        final Function<V, R> f;

        private Case(final Class<V> t, final Function<V, R> f) {
            this.type = t;
            this.f = f;
        }

        public static <V, R> Case<V, R> exprCase(final Class<V> t, final Function<V, R> f) {
            return new Case<>(t, f);
        }

        public static <V> Case<V, Void> stmtCase(final Class<V> t, final Consumer<V> f) {
            return exprCase(t, v -> {
                f.accept(v);
                return null;
            });
        }
    }

    public static class Default<R> {
        final Supplier<R> f;

        private Default(final Supplier<R> f) {
            this.f = f;
        }

        public static <R> Default<R> exprDefault(final Supplier<R> f) {
            return new Default<>(f);
        }

        public static Default<Void> stmtDefault(final Runnable f) {
            return exprDefault(() -> { f.run(); return null;});
        }
    }
}
