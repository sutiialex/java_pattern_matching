package patternmatch;

import patternmatch.Node.InternalNode;
import patternmatch.Node.Leaf;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.max;
import static patternmatch.PatternMatch.Case.exprCase;
import static patternmatch.PatternMatch.Case.stmtCase;
import static patternmatch.PatternMatch.Default.exprDefault;
import static patternmatch.PatternMatch.Default.stmtDefault;
import static patternmatch.PatternMatch.match;

public class Foo {
    public static void main(final String[] args) {
        final Node<Integer> tree =
                new InternalNode<>(
                    new InternalNode<>(
                            new Leaf<>(3),
                            new InternalNode<>(
                                    new Leaf<>(4),
                                    new Leaf<>(6))),
                    new Leaf<>(8));

        printTree(tree);
        System.out.println();
        System.out.println("Depth: " + countDepth(tree));
        System.out.println("Leaves: " + getLeaves(tree));
    }

    private static <V> int countDepth(final Node<V> node) {
        return match(node,
                exprCase(InternalNode.class, n -> 1 + max(countDepth(n.left), countDepth(n.right))),
                exprCase(Leaf.class, n -> 1),
                exprDefault(() -> { throw new RuntimeException("node not known: " + node); }));
    }

    private static <V> List<V> getLeaves(final Node<V> node) {
        return match(node,
                exprCase(InternalNode.class, n -> Stream.of(getLeaves(((InternalNode<V>) n).left),
                        getLeaves(((InternalNode<V>) n).right))
                        .flatMap(Collection::stream).collect(Collectors.toList())),
                exprCase(Leaf.class, l -> Collections.singletonList(((Leaf<V>) l).value)),
                exprDefault(() -> { throw new RuntimeException(); }));
    }

    private static <V> void printTree(final Node<V> node) {
        match(node,
                stmtCase(InternalNode.class, n -> {
                    System.out.print("(");
                    printTree(n.left);
                    System.out.print(", ");
                    printTree(n.right);
                    System.out.print(")");
                }),
                stmtCase(Leaf.class, l -> {
                    System.out.print(l.value);
                }),
                stmtDefault(() -> { }));
    }
}
