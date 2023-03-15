package ic.doc.strategy;

public class FibonacciTerm implements TermGenerator {
    @Override
    public int positiveTerm(int i) {
        if (i < 2) {
            return 1;
        }
        return positiveTerm(i - 2) + positiveTerm(i - 1);
    }
}
