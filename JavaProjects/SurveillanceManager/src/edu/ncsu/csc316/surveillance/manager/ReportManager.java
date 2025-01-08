package edu.ncsu.csc316.surveillance.manager;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.surveillance.data.Call;
import edu.ncsu.csc316.surveillance.data.Person;
import edu.ncsu.csc316.surveillance.dsa.Algorithm;
import edu.ncsu.csc316.surveillance.dsa.DSAFactory;
import edu.ncsu.csc316.surveillance.dsa.DataStructure;

/**
 * Manages and formats surveillance data for report generation.
 * Provides methods to retrieve call details and warrant information.
 * 
 * Wraps the SurveillanceManager class for easier report presentation.
 * 
 * @author Muzamani Gausi
 * @author Jacob Phillips 
 */
public class ReportManager {

    /** Instance of SurveillanceManager to process surveillance data */
    private SurveillanceManager manager;
    /** Formatter for date and time in reports */
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("   M/d/yyyy 'at' H:mm:ss a 'involving' ");

    /** Map of all people from the surveillance data */
    private Map<String, Person> people;

    /**
     * Constructs a ReportManager with the default data structure.
     * 
     * @param peopleFile the file containing person data
     * @param callFile   the file containing call data
     * @throws FileNotFoundException if any file is not found
     */
    public ReportManager(String peopleFile, String callFile) throws FileNotFoundException {
        this(peopleFile, callFile, DataStructure.SKIPLIST);
    }

    /**
     * Constructs a ReportManager with a specified data structure.
     * 
     * @param peopleFile the file containing person data
     * @param callFile   the file containing call data
     * @param mapType    the type of map data structure to use
     * @throws FileNotFoundException if any file is not found
     */
    public ReportManager(String peopleFile, String callFile, DataStructure mapType) throws FileNotFoundException {
        manager = new SurveillanceManager(peopleFile, callFile, mapType);
        DSAFactory.setListType(DataStructure.SINGLYLINKEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.QUICKSORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.RADIX_SORT);
        DSAFactory.setMapType(mapType);
        people = manager.getPeople();
    }

    /**
     * A helper class representing a person and their associated calls.
     */
    private class CallEntry implements Comparable<CallEntry> {
        /** The person */
        Person person;
        /** The calls associated with the person */
        Call[] calls;

        /**
         * Constructs a CallEntry.
         * 
         * @param p the person
         * @param c the calls
         */
        public CallEntry(Person p, Call[] c) {
            person = p;
            calls = c;
        }

        @Override
        public int compareTo(CallEntry arg0) {
            return this.person.getPhoneNumber().compareTo(arg0.person.getPhoneNumber());
        }
    }

    /**
     * Comparator for CallEntry objects.
     */
    private class CallEntryComparator implements Comparator<CallEntry> {
        @Override
        public int compare(CallEntry ce1, CallEntry ce2) {
            return ce1.compareTo(ce2);
        }
    }

    /**
     * Retrieves all phone calls grouped by each person and formats them in a plaintext report.
     * 
     * @return a formatted string of calls by person
     */
    public String getCallsByPerson() {
        if (people.size() == 0) {
            return "No people information was provided.";
        }

        Map<String, List<Call>> source = manager.getCallsByPerson();

        if (source.size() == 0) {
            return "No calls exist in the call logs.";
        }

        StringBuilder ret = new StringBuilder();

        for (String p : people) {
            if (source.get(p) == null)
                source.put(p, DSAFactory.getIndexedList());
        }

        CallEntry[] sortedSource = new CallEntry[source.size()];
        List<Call> sourceCalls;
        Call[] sortedSourceCalls;
        Sorter<Call> sourceSorter = DSAFactory.getComparisonSorter(new CallComparator());
        Sorter<CallEntry> sourceCallsSorter = DSAFactory.getComparisonSorter(new CallEntryComparator());

        int i = 0;
        for (Entry<String, List<Call>> entry : source.entrySet()) {
            sourceCalls = entry.getValue();
            sortedSourceCalls = new Call[sourceCalls.size()];
            for (int j = 0; j < sourceCalls.size(); j++) {
                sortedSourceCalls[j] = sourceCalls.get(j);
            }

            sourceSorter.sort(sortedSourceCalls);
            sortedSource[i] = new CallEntry(people.get(entry.getKey()), sortedSourceCalls);
            i++;
        }

        sourceCallsSorter.sort(sortedSource);

        for (CallEntry entry : sortedSource) {
            ret.append("Calls involving ");
            ret.append(entry.person.getPhoneNumber());
            ret.append(" (");
            ret.append(entry.person.getFirst());
            ret.append(" ");
            ret.append(entry.person.getLast());
            ret.append(") [\n");

            if (entry.calls.length == 0) {
                ret.append("   (none)\n]");
                continue;
            }

            for (Call c : entry.calls) {
                ret.append(c.getTimestamp().format(dateFormat));
                String[] numbersList = c.getPhoneNumbers();
                ret.append(numbersList.length - 1);
                ret.append(" other number(s):\n");
                for (String number : numbersList) {
                    if (!number.equals(entry.person.getPhoneNumber())) {
                        Person involved = people.get(number);
                        ret.append("      ");
                        ret.append(number);
                        ret.append(" (");
                        ret.append(involved.getFirst());
                        ret.append(" ");
                        ret.append(involved.getLast());
                        ret.append(")\n");
                    }
                }
            }
            ret.append("]\n");
        }

        return ret.toString();
    }

    /**
     * A helper class representing a person and the number of hops to reach them.
     */
    private class HopEntry implements Comparable<HopEntry> {
        /** The person */
        Person person;
        /** The number of hops */
        int hops;

        /**
         * Constructs a HopEntry.
         * 
         * @param p the person
         * @param h the number of hops
         */
        public HopEntry(Person p, int h) {
            person = p;
            hops = h;
        }

        @Override
        public int compareTo(HopEntry he2) {
            int r = Integer.compare(this.hops, he2.hops);
            if (r != 0)
                return r;

            r = this.person.getLast().compareTo(he2.person.getLast());
            if (r != 0)
                return r;

            r = this.person.getFirst().compareTo(he2.person.getFirst());
            if (r != 0)
                return r;

            return this.person.getPhoneNumber().compareTo(he2.person.getPhoneNumber());
        }
    }

    /**
     * Comparator for HopEntry objects.
     */
    private class HopEntryComparator implements Comparator<HopEntry> {
        @Override
        public int compare(HopEntry he1, HopEntry he2) {
            return he1.compareTo(he2);
        }
    }

    /**
     * Retrieves people reachable by a given number of hops from a starting phone number.
     * Formats the result as a plaintext report.
     * 
     * @param hops              the maximum number of hops
     * @param originPhoneNumber the starting phone number
     * @return a formatted string of people reachable within the specified hops
     */
    public String getPeopleCoveredByWarrant(int hops, String originPhoneNumber) {
        if (hops < 1) {
            return "Number of hops must be greater than 0.";
        }

        if (people.size() == 0) {
            return "No people information was provided.";
        }

        Person originPerson = people.get(originPhoneNumber);
        if (originPerson == null) {
            return "Phone number " + originPhoneNumber + " does not exist.";
        }

        Map<String, Integer> source = manager.getPeopleByHop(originPhoneNumber);

        if (source.size() == 0) {
            return "No calls exist in the call logs.";
        }

        StringBuilder ret = new StringBuilder();
        ret.append("Phone numbers covered by a ");
        ret.append(hops);
        ret.append("-hop warrant originating from ");
        ret.append(originPhoneNumber);
        ret.append(" (");
        ret.append(originPerson.getFirst());
        ret.append(" ");
        ret.append(originPerson.getLast());
        ret.append(") [\n");

        HopEntry[] sortedSource = new HopEntry[source.size()];
        Sorter<HopEntry> s = DSAFactory.getComparisonSorter(new HopEntryComparator());
        int i = 0;
        for (Entry<String, Integer> entry : source.entrySet()) {
            sortedSource[i] = new HopEntry(people.get(entry.getKey()), entry.getValue());
            i++;
        }

        s.sort(sortedSource);

        for (HopEntry e : sortedSource) {
            if (e.hops > hops) {
                break;
            }
            ret.append("   ");
            ret.append(e.hops);
            ret.append("-hop: ");
            ret.append(e.person.getPhoneNumber());
            ret.append(" (");
            ret.append(e.person.getFirst());
            ret.append(" ");
            ret.append(e.person.getLast());
            ret.append(")\n");
        }

        return ret.append("]").toString();
    }
}
