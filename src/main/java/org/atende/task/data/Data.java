package org.atende.task.data;

public class Data {

    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;

    public Data(int a, int b, int c, int d, int e, int f, int g, int h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }

    public int getE() {
        return e;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public static class DataBuilder {
        private int a;
        private int b;
        private int c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;

        public DataBuilder setA(int a) {
            this.a = a;
            return this;
        }

        public DataBuilder setB(int b) {
            this.b = b;
            return this;
        }

        public DataBuilder setC(int c) {
            this.c = c;
            return this;
        }

        public DataBuilder setD(int d) {
            this.d = d;
            return this;
        }

        public DataBuilder setE(int e) {
            this.e = e;
            return this;
        }

        public DataBuilder setF(int f) {
            this.f = f;
            return this;
        }

        public DataBuilder setG(int g) {
            this.g = g;
            return this;
        }

        public DataBuilder setH(int h) {
            this.h = h;
            return this;
        }

        public Data createData() {
            return new Data(a, b, c, d, e, f, g, h);
        }
    }
}
