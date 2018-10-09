/**
 * From "Subtyping, Sublcassing, and Trouble with OOP"
 * http://okmij.org/ftp/Computation/Subtyping
 */
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <code>FBag</code> implements a functional multiset
 */
public class FBag
{
   /**
    * Constructs an empty bag
    */
   public FBag()
   {
      this(Collections.emptyList());
   }

   /**
    * Constructs a copy of a bag
    */
   public FBag(final FBag another)
   {
      this(another.elements);
   }

   /**
    * Folds over the elements in this bag
    */
   public static <T> T reduce(final FBag bag, final T identity, final BiFunction<T, Integer,T > accumulator, BinaryOperator<T> combiner)
   {
      return bag.elements.stream().reduce(identity, accumulator,combiner);
   }

   /**
    * The number of elements in the bag
    */
   public static int size(final FBag bag)
   {
      return bag.elements.size();
   }

   /**
    * Put an element into the bag
    */
   public static FBag put(final FBag bag, final Integer elem)
   {
      return new FBag(Stream.concat(bag.elements.stream(), Stream.of(elem)).collect(Collectors.toList()));
   }

   /**
    * Count the number of occurrences of a particular element in the bag
    */
   public static int count(final FBag bag, final Integer elem)
   {
      return (int) bag.elements.stream().filter(elem::equals).count();
   }

   /**
    * Remove an element from the bag
    */
   public static FBag del(final FBag bag, final Integer elem)
   {
      final List<Integer> newElements = new ArrayList<Integer>(bag.elements);
      newElements.remove(elem);

      return new FBag(newElements);
   }

   /**
    * Standard "print-on" operation
    */
   public static void write(final OutputStream os, final FBag bag)
   {
      new PrintStream(os, true).println(bag.elements.stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]")));
   }

   /**
    * Union (merge) of the two bags
    */
   public static FBag union(final FBag to, final FBag from)
   {
      return from.elements.stream().reduce(to, FBag::put, FBag::union);
   }

   /**
    * Determine whether BagV a is a subbag of BagV b
    */
   public static boolean isSubbag(final FBag a, final FBag b)
   {
      return a.elements.stream().map(elem -> a.count(a, elem) == b.count(b,elem)).reduce(true, Boolean::logicalAnd);
   }

   /**
    * Determine whether BagV a is a superbag of BagV b
    */
   public static boolean isSuperbag(final FBag a, final FBag b)
   {
      return isSubbag(b, a);
   }

   /**
    * Structural equivalence of the bags
    * Two bags are equal if they contain the same number of the same elements
    */
   public static boolean equals(final FBag a, final FBag b)
   {
      return isSubbag(a, b) && isSuperbag(a, b);
   }

   ////////////////////////////////////////////////////////////////////////////
   //
   // Private implementation
   //
   private FBag(final List<Integer> elements)
   {
      this.elements = Collections.unmodifiableList(elements);
   }

   private List<Integer> elements;
}
