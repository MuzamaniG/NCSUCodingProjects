package edu.ncsu.csc316.surveillance.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.surveillance.data.Call;
import edu.ncsu.csc316.surveillance.data.Person;
import edu.ncsu.csc316.surveillance.dsa.Algorithm;
import edu.ncsu.csc316.surveillance.dsa.DSAFactory;
import edu.ncsu.csc316.surveillance.dsa.DataStructure;
import edu.ncsu.csc316.surveillance.io.InputReader;

/**
 * Manages and processes logs of people and phone calls to trace interactions.
 * Provides methods to analyze connections between people based on call logs.
 * 
 * @author Muzamani Gausi
 * @author Jacob Phillips
 */
public class SurveillanceManager {

    /** Stores all people data in memory */
    private List<Person> people;
    /** Stores all call data in memory */
    private List<Call> calls;

    /**
     * Creates a new SurveillanceManager with default data structure settings.
     * 
     * @param peopleFile the file containing person data
     * @param callFile   the file containing call data
     * @throws FileNotFoundException if either file is not found
     */
    public SurveillanceManager(String peopleFile, String callFile) throws FileNotFoundException {
        this(peopleFile, callFile, DataStructure.SKIPLIST);
    }

    /**
     * Creates a new SurveillanceManager with a specified map type.
     * 
     * @param peopleFile the file containing person data
     * @param callFile   the file containing call data
     * @param mapType    the type of map to use for processing
     * @throws FileNotFoundException if either file is not found
     */
    public SurveillanceManager(String peopleFile, String callFile, DataStructure mapType)
            throws FileNotFoundException {
        DSAFactory.setListType(DataStructure.SINGLYLINKEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.QUICKSORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.RADIX_SORT);
        DSAFactory.setMapType(mapType);

        // Read input files and initialize data
        people = InputReader.readPersonData(peopleFile);
        calls = InputReader.readCallData(callFile);
    }

    /**
     * Retrieves all people from the logs and maps them by phone number.
     * 
     * @return a map of phone numbers to Person objects
     */
    public Map<String, Person> getPeople() {
        Map<String, Person> ret = DSAFactory.getMap(null);
        for (Person p : people) {
            ret.put(p.getPhoneNumber(), p);
        }
        return ret;
    }

    /**
     * Groups calls by each person based on phone numbers involved.
     * 
     * @return a map of phone numbers to lists of calls
     */
    public Map<String, List<Call>> getCallsByPerson() {
        if (calls.isEmpty() || people.isEmpty()) {
            return DSAFactory.getMap(null);
        }

        Map<String, List<Call>> ret = DSAFactory.getMap(null);
        Sorter<String> involvedSorter = DSAFactory.getComparisonSorter(null);

        List<Call> eachPersonsList;
        for (Call eachCall : calls) {
            involvedSorter.sort(eachCall.getPhoneNumbers());
            for (String eachPersonFromAllCalls : eachCall.getPhoneNumbers()) {
                eachPersonsList = ret.get(eachPersonFromAllCalls);
                if (eachPersonsList == null) {
                    eachPersonsList = DSAFactory.getIndexedList();
                    ret.put(eachPersonFromAllCalls, eachPersonsList);
                }
                eachPersonsList.addFirst(eachCall);
            }
        }

        return ret;
    }

    /**
     * Calculates the number of hops required to reach each phone number connected
     * to the provided origin phone number.
     * 
     * @param originPhoneNumber the starting phone number
     * @return a map of phone numbers to the number of hops required to reach them
     */
    public Map<String, Integer> getPeopleByHop(String originPhoneNumber) {
        if (calls.isEmpty() || people.isEmpty()) {
            return DSAFactory.getMap(null);
        }

        Map<String, List<String>> peopleToNext = DSAFactory.getMap(null);

        List<String> eachNextList;
        String[] eachPersonInCall;
        for (Call eachCall : calls) {
            eachPersonInCall = eachCall.getPhoneNumbers();
            for (String eachPersonFromAllCalls : eachPersonInCall) {
                eachNextList = peopleToNext.get(eachPersonFromAllCalls);
                if (eachNextList == null) {
                    eachNextList = DSAFactory.getIndexedList();
                    peopleToNext.put(eachPersonFromAllCalls, eachNextList);
                }
                for (String everyOtherPersonFromAllCalls : eachPersonInCall) {
                    if (!everyOtherPersonFromAllCalls.equals(eachPersonFromAllCalls)) {
                        eachNextList.addFirst(everyOtherPersonFromAllCalls);
                    }
                }
            }
        }

        Map<String, Integer> ret = DSAFactory.getMap(null);
        Map<String, Boolean> alreadyFound = DSAFactory.getMap(null);
        Queue<LevelEntry> find = DSAFactory.getQueue();
        find.enqueue(new LevelEntry(originPhoneNumber, 0));

        LevelEntry current;
        Integer a;
        List<String> nextLinks;
        while (!find.isEmpty()) {
            current = find.dequeue();

            nextLinks = peopleToNext.get(current.phone);
            if (nextLinks != null) {
                for (String next : nextLinks) {
                    if (alreadyFound.get(next) == null) {
                        find.enqueue(new LevelEntry(next, current.level + 1));
                    }
                }
            }

            alreadyFound.put(current.phone, true);

            a = ret.get(current.phone);
            if (a == null || a > current.level) {
                ret.put(current.phone, current.level);
            }
        }

        ret.remove(originPhoneNumber);

        return ret;
    }

    /**
     * Represents a phone number and the number of hops required to reach it.
     */
    private class LevelEntry {
        /** The phone number */
        String phone;
        /** The number of hops */
        int level;

        /**
         * Constructs a LevelEntry.
         * 
         * @param n the phone number
         * @param l the number of hops
         */
        public LevelEntry(String n, int l) {
            phone = n;
            level = l;
        }
    }
}
