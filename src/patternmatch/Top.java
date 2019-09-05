package patternmatch;

interface Top {
    class A implements Top {
        public int a;

        public A(final int a) {
            this.a = a;
        }
    }

    class B implements Top {
        public String s;

        public B(final String s) {
            this.s = s;
        }
    }
}
