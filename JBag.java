/**
 * From "Subtyping, Sublcassing, and Trouble with OOP"
 * http://okmij.org/ftp/Computation/Subtyping
 */
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <code>JBag</code> implements a multiset.
 */
public class JBag implements Cloneable, Iterable<Integer>
{
   /**
    * Constructs an empty bag
    */
   public JBag()
   {
      this(new ArrayList<Integer>());
   }
   
   /**
    * The number of elements in the bag
    */
   public int size()
   {
      return elements.size();
   }

   /**
    * Put an element into the bag
    */
   public void put(final Integer elem)
   {
      elements.put(elem);
   }

   /**
    * Count the number of occurrences of a particular element in the bag
    */
   public int count(final Integer elem)
   {
      return elements.stream().filter(elem::equals).count();
   }

   /**
    * Remove an element from the bag
    * Return false if the element didn't exist
    */
   public boolean del(final Integer elem)
   {
      return elements.remove(elem);
   }

   /**
    * Standard iterator interface
    */
   public Iterator<Integer> iterator()
   {
      return elements.iterator();
   }

   /**
    * Make a copy of the bag
    */
   public JBag clone()
   {
      return new JBag(elements.clone());
   }

   /**
    * Standard "print-on" operation
    *
    * N.B.: We use only publicly-available methods here
    */
   public static void write(final OutputStream os, final JBag bag)
   {
      new PrintStream(os, true).println(bag.iterator().stream().collect(Collectors.joining(",", "[", "]")));
   }

   /**
    * Union (merge) of the two bags
    * The return type is void to avoid complications with subclassing
    * (which is incidental to the current example)
    *
    * N.B.: We use only publicly-available methods here
    */
   public static void union(final JBag to, final JBag from)
   {
      from.iterator().stream().forEach(to::put);
   }

   /**
    * Determine whether JBag a is a subbag of JBag b
    *
    * N.B.: We use only publicly-available methods here
    */
   public static boolean isSubbag(final JBag a, final JBag b)
   {
      return a.iterator().stream().map(elem -> a.count(elem) <= b.count(elem)).reduce(true, Boolean::logicalAnd);
   }

   /**
    * Determine whether JBag a is a superbag of JBag b
    *
    * N.B.: We use only publicly-available methods here
    */
   public static boolean isSuperbag(final JBag a, final JBag b)
   {
      return isSubbag(b, a);
   }

   /**
    * Structural equivalence of the bags
    * Two bags are equal if they contain the same number of the same elements
    *
    * N.B.: We use only publicly-available methods here
    */
   public static boolean equals(final JBag a, final JBag b)
   {
      return isSubbag(a, b) && isSuperbag(a, b);
   }

   ////////////////////////////////////////////////////////////////////////////
   //
   // Private implementation
   //
   private JBag(final List<Integer> elements)
   {
      this.elements = elements;
   }
   
   private final List<Integer> elements;
}
