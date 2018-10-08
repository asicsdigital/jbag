/**
 * From "Subtyping, Sublcassing, and Trouble with OOP"
 * http://okmij.org/ftp/Computation/Subtyping
 */
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <code>BagV</code> implements an immutable multiset.
 */
public class BagV
{
   /**
    * Constructs an empty bag
    */
   public BagV()
   {
      this(Collections.emptyList());
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
   public BagV put(final Integer elem)
   {
      return new BagV(Stream.concat(elements.stream(), Stream.of(elem)).collect(Collectors.toList()));
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
    */
   public BagV del(final Integer elem)
   {
      final List<Integer> newElements = new ArrayList<Integer>(elements);
      newElements.remove(elem);

      return new BagV(newElements);
   }

   /**
    * Return a stream for the elements in this bag
    */
   public Stream<Integer> stream()
   {
      return elements.stream();
   }

   /**
    * Standard "print-on" operation
    *
    * N.B.: We use only publicly-available methods here
    */
   public static void write(final OutputStream os, final BagV bag)
   {
      new PrintStream(os, true).println(bag.stream().collect(Collectors.joining(",", "[", "]")));
   }

   /**
    * Union (merge) of the two bags
    *
    * N.B.: We use only publicly-available methods here
    */
   public static BagV union(final BagV to, final BagV from)
   {
      return from.reduce(to, (result, elem) -> result.put(elem));
   }

   /**
    * Determine whether BagV a is a subbag of BagV b
    *
    * N.B.: We use only publicly-available methods here
    */
   public static boolean isSubbag(final BagV a, final BagV b)
   {
      return a.stream().map(elem -> a.count(elem) == b.count(elem)).reduce(true, Boolean::logicalAnd);
   }

   /**
    * Determine whether BagV a is a superbag of BagV b
    *
    * N.B.: We use only publicly-available methods here
    */
   public static boolean isSuperbag(final BagV a, final BagV b)
   {
      return isSubbag(b, a);
   }

   /**
    * Structural equivalence of the bags
    * Two bags are equal if they contain the same number of the same elements
    *
    * N.B.: We use only publicly-available methods here
    */
   public static boolean equals(final BagV a, final BagV b)
   {
      return isSubbag(a, b) && isSuperbag(a, b);
   }
   
   ////////////////////////////////////////////////////////////////////////////
   //
   // Private implementation
   //
   private BagV(final List<Integer> elements)
   {
      this.elements = Collections.unmodifiableList(elements);
   }
   
   private final List<Integer> elements;
}
