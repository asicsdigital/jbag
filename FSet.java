/**
 * From "Subtyping, Sublcassing, and Trouble with OOP"
 * http://okmij.org/ftp/Computation/Subtyping
 */


/**
 * <code>FSet</code> implements a functional set
 */
public class FSet extends FBag
{
   /**
    * Constructs an empty set
    */
   public FSet()
   {
   }

   /**
    * Constructs a set from a bag
    */
   public FSet(final FBag bag)
   {
      super(removeDuplicates(bag));
   }

   /**
    * Test whether an element is a member of this set
    */
   public static boolean memberOf(final FSet set, final Integer elem)
   {
      return count(set, elem) > 0;
   }
   
   ////////////////////////////////////////////////////////////////////////////
   //
   // Private implementation
   //
   private static FBag removeDuplicates(final FBag bag)
   {
      return reduce(bag, new FBag(), (result, elem) -> (count(result, elem) > 0) ? result : put(result, elem));
   }
}
