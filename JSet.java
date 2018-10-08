/**
 * From "Subtyping, Sublcassing, and Trouble with OOP"
 * http://okmij.org/ftp/Computation/Subtyping
 */


/**
 * <code>JSet</code> implements a set.
 */
public class JSet extends JBag
{
   /**
    * Test whether an element is a member of this set
    */
   public boolean memberOf(final Integer elem)
   {
      return count(elem) > 0;
   }

   /**
    * Put an element into this set
    * Overrides JBag::put
    */
   @Override
   public void put(final Integer elem)
   {
      if (!memberOf(elem))
      {
         super.put(elem);
      }
   }

   /**
    * Make a copy of this set
    */
   public JSet clone()
   {
      final JSet newSet = new JSet();
      union(newSet, this);

      return newSet;
   }
}
