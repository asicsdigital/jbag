import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{

    public static void main(String[] args)
    {

        JBag bag1 = new JBag();
        JBag bag2 = new JBag();
        JBag bag3 = new JBag();

        bag1.put(1);
        bag1.put(2);

        bag1.put(1);
        bag2.put(3);
        bag2.put(4);

        bag3.put(1);
        bag3.put(1);
        bag3.put(2);
        bag3.put(3);
        bag3.put(4);
        bag3.put(5);


        System.out.println("Operations using bags:");
        System.out.println("a+b is a subbag of c using foo:" + foo(bag1,bag2,bag3));
        System.out.println(foo2(bag1,bag2,bag3));

        JSet set1 = new JSet();
        JSet set2 = new JSet();
        JSet set3 = new JSet();

        set1.put(1);
        set1.put(1);
        set1.put(2);

        set2.put(1);
        set2.put(3);
        set2.put(4);

        set3.put(1);
        set3.put(1);
        set3.put(2);
        set3.put(3);
        set3.put(4);
        set3.put(5);

        System.out.println("Operations using sets:");
        System.out.println(foo(set1,set2,set3));
        System.out.println(foo2(set1,set2,set3));

    }


    // A sample function. Given three bags a, b, and c, it decides
// if a+b is a subbag of c
    static boolean foo(JBag a,JBag b,JBag c)
    {
        JBag ab = a.clone();	// Clone a to avoid clobbering it
        JBag.union(ab,b);	// ab is now the union of a and b
        boolean result = JBag.isSubbag(ab,c);
        return result;
    }

    static boolean foo2(JBag a,JBag b,JBag c)
    {
        JBag ab = new JBag();
        JBag.union(ab,a);			// Clone a to avoid clobbering it
        JBag.union(ab, b);			// ab is now the union of a and b
        boolean result = JBag.isSubbag(ab, c);
        return result;
    }
}
