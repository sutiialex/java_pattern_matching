package patternmatch;

import patternmatch.Top.A;
import patternmatch.Top.B;

import static patternmatch.PatternMatch.Case.exprCase;
import static patternmatch.PatternMatch.Case.stmtCase;
import static patternmatch.PatternMatch.Default.*;
import static patternmatch.PatternMatch.match;

public class Foo {
    public static void main(final String[] args) {
        evaluate(new A(5));
        evaluate(new B("frt"));
    }

    private static void evaluate(final Top t) {
        final int r = match(t,
                exprCase(A.class, a -> a.a),
                exprCase(B.class, b -> b.s.length()),
                exprDefault(() -> 10));
        System.out.println("fsdfsd: " + r);

        match(t,
                stmtCase(A.class, a -> System.out.println("" + a.a)),
                stmtCase(B.class, b -> System.out.println("" + b.s)),
                stmtDefault(() -> System.out.println("not known")));
    }
}
