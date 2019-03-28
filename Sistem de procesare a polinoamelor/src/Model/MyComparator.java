package Model;

import java.util.Comparator;

public class MyComparator implements Comparator<Monom> {

        @Override
        public int compare(Monom o1, Monom o2) {
            Integer a = (int)o1.getExponent();
            Integer b = (int)o2.getExponent();
            return b.compareTo(a);
        }
}


