import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * This class is a helper class for calculating statistics
 * based on a list of integer values. In both Question 1 and
 * Question 2, you will need to calculate the min, max,
 * mean, Q1, Q2, and Q3 of a list of integers, as well as
 * count the frequency of each integer.
 *
 * IMPORTANT: This class is not yet complete, so must be
 * completed by you.
 *
 * To accomplish this, you should fill in the missing parts
 * of this code. Project 3 builds upon Projects 1 and 2
 * here! Finding the top N foods by calorie count is similar
 * to finding the first second and third quartiles. I
 * recommend you consider using a min heap (via the
 * PriorityQueue class) to complete this code. However, you
 * may use whatever means you prefer (sorting, your solution
 * to Project 1, etc.).
 *
 * @author Mark Hancock
 * @author <Annie Yuan> - you are going to author one of the
 *         methods
 *
 */
public class Statistics {
    private HashMap<Integer, Integer> counts = new HashMap<>();
    private int min;//get minimum
    private int max;//get maximum
    private double mean;//get average
    private double q1;//get first quartile
    private double q2;//get second quartile
    private double q3;//third quartile

    /**
     * Constructs a new Statistics object based on a list of
     * integers. This class doesn't store the integers, but
     * instead calculates the min, max, Q1-Q3, and counts
     * the number of occurrences of each value.
     *
     * @param list the list of integers to collect
     *             statistics from.
     */
    public Statistics(List<Integer> list) {
        calculateFromList(list);
    }

    /**
     * This method calculates the min, max, mean, Q1, Q2,
     * and Q3 values from a list of integers, and also
     * counts the frequency (number of occurrences) of each
     * repeated value in the list.
     *
     * @param list a list of unsorted integers, with
     *             possible repeated values.
     */
    private void calculateFromList(List<Integer> list) {
        /*
         * TODO: complete this method!
         * 
         * By the end of this method, you should have
         * calculated the min, max, mean, Q1, Q2, and Q3
         * values, as well as populated the counts HashMap
         * with the counts of each value.
         */
    	
    	 min = list.get(0);//initialize the first value in the list as min
         max = list.get(0);//initialize max
        for (int i = 1; i < list.size(); i++) {//go through every element in the list
            if (list.get(i) < min) {//the new min
                min = list.get(i);
            } else if (list.get(i) > max) {//new max
                max = list.get(i);
            }
        }
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
             sum += list.get(i);//add up all values in list
        }
         mean = sum / list.size();//mean is total divided by number of elements

    	List<Integer> sortedlist = new ArrayList<>(list);//sort the list using collections class
    	Collections.sort(sortedlist);
    	 if((sortedlist.size()+1)%2==0) {//odd number size, to calculate the quartile, we need to add 1 then time percentage
    		int q1Index=(int)((sortedlist.size()+1)*0.25);//this is where seperates q1 and q2
 		  	int q2Index=(int)((sortedlist.size()+1)*0.5);//separates q2 and q3
 		  	int q3Index=(int)((sortedlist.size()+1)*0.75);//separates q3 and 4
 		  	if(q2Index%2==0) {//the middle of the list is even
			  	q1=(sortedlist.get(q1Index-1)+sortedlist.get(q1Index))/2.0;
			  	q2=sortedlist.get(q2Index-1);
			  	q3=(sortedlist.get(q3Index-1)+sortedlist.get(q3Index))/2.0;
 		  	}else {
 		  		q1=sortedlist.get(q1Index);
			  	q2=sortedlist.get(q2Index-1);
			  	q3=sortedlist.get(q3Index);
 		  	}

    	 }
    	 else {//the list has even number size, we can just get the quartiles by timing 0.25
    		int q1Index=(int)((sortedlist.size())*0.25);
		  	int q2Index=(int)((sortedlist.size())*0.5);
		  	int q3Index=(int)((sortedlist.size())*0.75);
		  	if(q2Index%2==0) {//the middle of the list is even
		  	q1=(sortedlist.get(q1Index-1)+sortedlist.get(q1Index))/2.0;
		  	q2=(sortedlist.get(q2Index-1)+sortedlist.get(q2Index))/2.0;
		  	q3=(sortedlist.get(q3Index-1)+sortedlist.get(q3Index))/2.0;
		  	}else {
			  	q1=sortedlist.get(q1Index-1);
			  	q2=(sortedlist.get(q2Index-1)+sortedlist.get(q2Index))/2.0;
			  	q3=sortedlist.get(q3Index-1);
		  	}
    	 }
    }

    /**
     * Returns a list of ordered unique values based on the
     * original list with duplicates.
     *
     * @return a list of ordered unique values based on the
     *         original list with duplicates
     */
    public List<Integer> getSortedUniqueKeys() {
        ArrayList<Integer> keys = new ArrayList<>(counts.keySet());
        Collections.sort(keys);
        return keys;
    }

    /**
     * Returns the number of occurrences of a specific
     * value.
     *
     * @param value the value to check for occurrences
     * @return the number of occurrences
     */
    public int getCountOf(int value) {
        return counts.get(value);
    }

    /**
     * Returns the minimum value from the list.
     *
     * @return the minimum value from the list
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the maximum value from the list.
     *
     * @return the maximum value from the list
     */
    public int getMax() {
        return max;
    }

    /**
     * Returns the mean value from the list.
     *
     * @return the mean value from the list
     */
    public double getMean() {
        return mean;
    }

    /**
     * Returns the first quartile value from the list.
     *
     * @return the first quartile value from the list
     */
    public double getQ1() {
        return q1;
    }

    /**
     * Returns the second quartile (a.k.a. median) value
     * from the list.
     *
     * @return the first quartile value from the list
     */
    public double getQ2() {
        return q2;
    }

    /**
     * Returns the third quartile value from the list.
     *
     * @return the third quartile value from the list
     */
    public double getQ3() {
        return q3;
    }
}
