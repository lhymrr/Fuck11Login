package com.l.h.y.fucklogin.Utils;

import java.lang.reflect.Array;

public class Utils {

	
	/**
	 * <p>
	 * Adds all the elements of the given arrays into a new array.
	 * </p>
	 * <p>
	 * The new array contains all of the element of <code>array1</code> followed
	 * by all of the elements <code>array2</code>. When an array is returned, it
	 * is always a new array.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(null, null) = null
	 * ArrayUtils.addAll(array1, null) = cloned copy of array1
	 * ArrayUtils.addAll(null, array2) = cloned copy of array2
	 * ArrayUtils.addAll([], []) = []
	 * ArrayUtils.addAll([null], [null]) = [null, null]
	 * ArrayUtils.addAll(["a", "b", "c"], ["1", "2", "3"]) = ["a", "b", "c", "1", "2", "3"]
	 * </pre>
	 * 
	 * @param array1
	 *            the first array whose elements are added to the new array, may
	 *            be <code>null</code>
	 * @param array2
	 *            the second array whose elements are added to the new array,
	 *            may be <code>null</code>
	 * @return The new array, <code>null</code> if <code>null</code> array
	 *         inputs. The type of the new array is the type of the first array.
	 * @since 2.1
	 */
	public static Object[] addAllArrays(Object[] array1, Object[] array2) {
		if (array1 == null) {
			return clone(array2);
		} else if (array2 == null) {
			return clone(array1);
		}
		Object[] joinedArray = (Object[]) Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length);
		System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}
	
	public static char[] addAllArrays(char[] array1, char[] array2) {
		if (array1 == null) {
			return clone(array2);
		} else if (array2 == null) {
			return clone(array1);
		}
		char[] joinedArray = (char[]) Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length);
		System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}
	
	/**
     * <p>Shallow clones an array returning a typecast result and handling
     * <code>null</code>.</p>
     *
     * <p>The objects in the array are not cloned, thus there is no special
     * handling for multi-dimensional arrays.</p>
     * 
     * <p>This method returns <code>null</code> if <code>null</code> array input.</p>
     * 
     * @param array the array to shallow clone, may be <code>null</code>
     * @return the cloned array, <code>null</code> if <code>null</code> input
     */
    public static Object  [] clone(Object  [] array) {
        if (array == null) {
            return null;
        }
        return (Object  []) array.clone();
    }
    
    public static char  [] clone(char  [] array) {
        if (array == null) {
            return null;
        }
        return (char  []) array.clone();
    }
}
