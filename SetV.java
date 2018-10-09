/**
 * From "Subtyping, Sublcassing, and Trouble with OOP"
 * http://okmij.org/ftp/Computation/Subtyping
 */


/**
 * <code>SetV</code> implements an immutable set.
 */
public class SetV extends BagV
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
    * Overrides SetV::put
    */
   @Override
   public SetV put(final Integer elem)
   {
      if (!memberOf(elem))
      {
         return (SetV) super.put(elem);
      }

      else
      {
         return this;
      }
   }

   /**
    * Make a copy of this set
    */
   public SetV clone()
   {
      return this;
   }
}
