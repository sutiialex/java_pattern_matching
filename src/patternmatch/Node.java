package patternmatch;

interface Node<T> {
    class InternalNode<T> implements Node<T> {
        public final Node<T> left;
        public final Node<T> right;

        public InternalNode(final Node<T> left, final Node<T> right) {
            this.left = left;
            this.right = right;
        }
    }

    class Leaf<T> implements Node<T> {
        public final T value;

        public Leaf(final T value) {
            this.value = value;
        }
    }
}
