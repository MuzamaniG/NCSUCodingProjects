package edu.ncsu.csc316.surveillance.manager;

import java.util.Comparator;

import edu.ncsu.csc316.surveillance.data.Call;

/**
 * Compares 2 calls
 * 
 * @author Muzamani Gausi
 * @author Jacob Phillips
 */
public class CallComparator implements Comparator<Call> {

    @Override
    public int compare(Call c1, Call c2) {
        int t = c1.getTimestamp().compareTo(c2.getTimestamp());
        if (t != 0)
            return t;
        return c1.getId().compareTo(c2.getId());
    }

}
