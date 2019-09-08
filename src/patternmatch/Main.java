package patternmatch;

import patternmatch.Node.InternalNode;
import patternmatch.Node.LeafNode;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.max;
import static patternmatch.patternmatch.PatternMatch.Case.exprCase;
import static patternmatch.patternmatch.PatternMatch.Case.stmtCase;
import static patternmatch.patternmatch.PatternMatch.Default.exprDefault;
import static patternmatch.patternmatch.PatternMatch.Default.stmtDefault;
import static patternmatch.patternmatch.PatternMatch.match;

public class Main {
    public static void main(final String[] args) {
        final Node<Integer> tree =
                new InternalNode<>(
                    new InternalNode<>(
                            new LeafNode<>(3),
                            new InternalNode<>(
                                    new LeafNode<>(4),
                                    new LeafNode<>(6))),
                    new LeafNode<>(8));

        printTree(tree);
        System.out.println();
        System.out.println("Tree depth: " + treeDepth(tree));
        System.out.println("Leave values: " + getLeaves(tree));
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
                stmtCase(LeafNode.class, l -> System.out.print(l.value)),
                stmtDefault(() -> { }));
    }

    private static <V> int treeDepth(final Node<V> node) {
        return match(node,
                exprCase(InternalNode.class, n -> 1 + max(treeDepth(n.left), treeDepth(n.right))),
                exprCase(LeafNode.class, n -> 1),
                exprDefault(() -> { throw new RuntimeException("node not known: " + node); }));
    }

    private static <V> List<V> getLeaves(final Node<V> node) {
        return match(node,
                exprCase(InternalNode.class, n ->
                        // Because of the type erasure in Java, classes with generics have to be casted explicitly.
                        Stream.of(getLeaves(((InternalNode<V>) n).left), getLeaves(((InternalNode<V>) n).right))
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList())),
                exprCase(LeafNode.class, l -> Collections.singletonList(((LeafNode<V>) l).value)),
                exprDefault(() -> { throw new RuntimeException(); }));
    }
}
