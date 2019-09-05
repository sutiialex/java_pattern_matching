package patternmatch;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PatternMatch {
    public static <T, V1 extends T, R> R match(final T t, final Case<V1, R> c1, final Default<R> defaultF) {
        return oMatch(t, c1).orElseGet(defaultF.f);
    }

    public static <T, V1 extends T, V2 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Default<R> defaultF) {
        return oMatch(t, c1).orElseGet(() -> match(t, c2, defaultF));
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Default<R> defaultF) {
        return oMatch(t, c1).orElseGet(() -> match(t, c2, c3, defaultF));
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, V4 extends T, R> R match(final T t, final Case
            <V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Case<V4, R> c4, final Default<R> defaultF) {
        return oMatch(t, c1).orElseGet(() -> match(t, c2, c3, c4, defaultF));
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, V4 extends T, V5 extends T, R> R match(
            final T t, final Case<V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Case<V4, R> c4,
            final Case<V5, R> c5, final Default<R> defaultF) {
        return oMatch(t, c1).orElseGet(() -> match(t, c2, c3, c4, c5, defaultF));
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, V4 extends T, V5 extends T, V6 extends T, R> R match(
            final T t, final Case<V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Case<V4, R> c4,
            final Case<V5, R> c5, final Case<V6, R> c6, final Default<R> defaultF) {
        return oMatch(t, c1).orElseGet(() -> match(t, c2, c3, c4, c5, c6, defaultF));
    }

    public static <T, V1 extends T, V2 extends T, V3 extends T, V4 extends T, V5 extends T, V6 extends T, V7 extends T, R> R match(
            final T t, final Case<V1, R> c1, final Case<V2, R> c2, final Case<V3, R> c3, final Case<V4, R> c4,
            final Case<V5, R> c5, final Case<V6, R> c6, final Case<V7, R> c7, final Default<R> defaultF) {
        return oMatch(t, c1).orElseGet(() -> match(t, c2, c3, c4, c5, c6, c7, defaultF));
    }

    private static <T, V extends T, R> Optional<R> oMatch(final T t, final Case<V, R> c) {
        return c.type.equals(t.getClass()) ? Optional.of(c.f.apply(c.type.cast(t))) : Optional.empty();
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

        public static <V> Case<V, Unit> stmtCase(final Class<V> t, final Consumer<V> f) {
            return exprCase(t, v -> {
                f.accept(v);
                return new Unit();
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

        public static Default<Unit> stmtDefault(final Runnable f) {
            return exprDefault(() -> {
                f.run();
                return new Unit();
            });
        }
    }

    private static class Unit {
    }
}
