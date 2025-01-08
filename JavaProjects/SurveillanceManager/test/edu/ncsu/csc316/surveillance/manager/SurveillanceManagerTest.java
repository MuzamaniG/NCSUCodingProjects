package edu.ncsu.csc316.surveillance.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.surveillance.data.Call;
import edu.ncsu.csc316.surveillance.data.Person;

/**
 * Test SurveillanceManager.java
 * 
 * @author Muzamani Gausi
 * @author Jacob Phillips
 */
public class SurveillanceManagerTest {

    /** SurveillanceManager instance*/
    private SurveillanceManager sm;

    /**
     * Tests getPeople()
     */
    @Test
    public void testGetPeople() {
        assertThrows(FileNotFoundException.class, () -> new SurveillanceManager("a", "b"));

        try {
            sm = new SurveillanceManager("input/1-256p.txt", "input/1-1024c.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Person> result = sm.getPeople();
        Person i;
        i = result.get("452-400-6023258");
        assertEquals("452-400-6023258", i.getPhoneNumber());
        assertEquals("Purvis", i.getFirst());
        assertEquals("Amy", i.getLast());

        i = result.get("518-052-2121645");
        assertEquals("518-052-2121645", i.getPhoneNumber());
        assertEquals("Sowards", i.getFirst());
        assertEquals("Juan", i.getLast());

        i = result.get("810-634-0039046");
        assertEquals("810-634-0039046", i.getPhoneNumber());
        assertEquals("Bensel", i.getFirst());
        assertEquals("Bart", i.getLast());

        i = result.get("000-699-2972537");
        assertEquals("000-699-2972537", i.getPhoneNumber());
        assertEquals("Tordoff", i.getFirst());
        assertEquals("Eduardo", i.getLast());

        i = result.get("157-089-1159597");
        assertEquals("157-089-1159597", i.getPhoneNumber());
        assertEquals("McKendry", i.getFirst());
        assertEquals("Christopher", i.getLast());

        i = result.get("928-618-6022384");
        assertEquals("928-618-6022384", i.getPhoneNumber());
        assertEquals("Howell", i.getFirst());
        assertEquals("Daniel", i.getLast());

        i = result.get("418-364-0653421");
        assertEquals("418-364-0653421", i.getPhoneNumber());
        assertEquals("Defoe", i.getFirst());
        assertEquals("Michael", i.getLast());

        i = result.get("148-757-6582249");
        assertEquals("148-757-6582249", i.getPhoneNumber());
        assertEquals("Rude", i.getFirst());
        assertEquals("Jaime", i.getLast());

        i = result.get("556-206-6472197");
        assertEquals("556-206-6472197", i.getPhoneNumber());
        assertEquals("Mastropietro", i.getFirst());
        assertEquals("Frances", i.getLast());

        i = result.get("279-929-7548142");
        assertEquals("279-929-7548142", i.getPhoneNumber());
        assertEquals("Lewis", i.getFirst());
        assertEquals("Oscar", i.getLast());

        i = result.get("515-056-3174571");
        assertEquals("515-056-3174571", i.getPhoneNumber());
        assertEquals("Campos", i.getFirst());
        assertEquals("Mariano", i.getLast());

        i = result.get("748-197-7119618");
        assertEquals("748-197-7119618", i.getPhoneNumber());
        assertEquals("Hodson", i.getFirst());
        assertEquals("Malinda", i.getLast());

        i = result.get("976-649-0727829");
        assertEquals("976-649-0727829", i.getPhoneNumber());
        assertEquals("Urbaniak", i.getFirst());
        assertEquals("Charlie", i.getLast());

        i = result.get("371-972-9479012");
        assertEquals("371-972-9479012", i.getPhoneNumber());
        assertEquals("Bass", i.getFirst());
        assertEquals("Tracy", i.getLast());

        i = result.get("605-849-1371960");
        assertEquals("605-849-1371960", i.getPhoneNumber());
        assertEquals("Grace", i.getFirst());
        assertEquals("Fred", i.getLast());

        i = result.get("346-132-7752108");
        assertEquals("346-132-7752108", i.getPhoneNumber());
        assertEquals("Booker", i.getFirst());
        assertEquals("Linda", i.getLast());

        i = result.get("524-449-5412955");
        assertEquals("524-449-5412955", i.getPhoneNumber());
        assertEquals("Gerber", i.getFirst());
        assertEquals("Jamie", i.getLast());

        i = result.get("217-051-9794473");
        assertEquals("217-051-9794473", i.getPhoneNumber());
        assertEquals("McManus", i.getFirst());
        assertEquals("Donna", i.getLast());

        i = result.get("442-699-4803782");
        assertEquals("442-699-4803782", i.getPhoneNumber());
        assertEquals("Didiego", i.getFirst());
        assertEquals("Cynthia", i.getLast());

        i = result.get("386-655-0034903");
        assertEquals("386-655-0034903", i.getPhoneNumber());
        assertEquals("Healy", i.getFirst());
        assertEquals("Sean", i.getLast());

        i = result.get("340-870-9662149");
        assertEquals("340-870-9662149", i.getPhoneNumber());
        assertEquals("Pryor", i.getFirst());
        assertEquals("Linda", i.getLast());

        i = result.get("939-284-7448131");
        assertEquals("939-284-7448131", i.getPhoneNumber());
        assertEquals("Allen", i.getFirst());
        assertEquals("Gladys", i.getLast());

        i = result.get("707-463-6674599");
        assertEquals("707-463-6674599", i.getPhoneNumber());
        assertEquals("Weatherford", i.getFirst());
        assertEquals("Janis", i.getLast());

        i = result.get("290-585-9275376");
        assertEquals("290-585-9275376", i.getPhoneNumber());
        assertEquals("Smith", i.getFirst());
        assertEquals("Raymond", i.getLast());

        i = result.get("851-826-5384567");
        assertEquals("851-826-5384567", i.getPhoneNumber());
        assertEquals("Holloway", i.getFirst());
        assertEquals("Charles", i.getLast());

        i = result.get("141-056-9547360");
        assertEquals("141-056-9547360", i.getPhoneNumber());
        assertEquals("Hanks", i.getFirst());
        assertEquals("Tony", i.getLast());

        i = result.get("023-741-3371714");
        assertEquals("023-741-3371714", i.getPhoneNumber());
        assertEquals("Cortez", i.getFirst());
        assertEquals("Willie", i.getLast());

        i = result.get("111-753-8198404");
        assertEquals("111-753-8198404", i.getPhoneNumber());
        assertEquals("Delossantos", i.getFirst());
        assertEquals("Jean", i.getLast());

        i = result.get("025-506-6740778");
        assertEquals("025-506-6740778", i.getPhoneNumber());
        assertEquals("Martinez", i.getFirst());
        assertEquals("Mary", i.getLast());

        i = result.get("739-319-8611336");
        assertEquals("739-319-8611336", i.getPhoneNumber());
        assertEquals("Harmening", i.getFirst());
        assertEquals("Penny", i.getLast());

        i = result.get("499-576-3484193");
        assertEquals("499-576-3484193", i.getPhoneNumber());
        assertEquals("Scott", i.getFirst());
        assertEquals("Charlotte", i.getLast());

        i = result.get("308-485-7242672");
        assertEquals("308-485-7242672", i.getPhoneNumber());
        assertEquals("Turley", i.getFirst());
        assertEquals("Frances", i.getLast());

        i = result.get("060-575-4845936");
        assertEquals("060-575-4845936", i.getPhoneNumber());
        assertEquals("Tucker", i.getFirst());
        assertEquals("Christi", i.getLast());

        i = result.get("451-454-3165252");
        assertEquals("451-454-3165252", i.getPhoneNumber());
        assertEquals("Kirkish", i.getFirst());
        assertEquals("Jessica", i.getLast());

        i = result.get("019-692-2446050");
        assertEquals("019-692-2446050", i.getPhoneNumber());
        assertEquals("Teem", i.getFirst());
        assertEquals("Donna", i.getLast());

        i = result.get("173-719-3192693");
        assertEquals("173-719-3192693", i.getPhoneNumber());
        assertEquals("Starke", i.getFirst());
        assertEquals("Sara", i.getLast());

        i = result.get("594-730-7507885");
        assertEquals("594-730-7507885", i.getPhoneNumber());
        assertEquals("Velasquez", i.getFirst());
        assertEquals("Kristen", i.getLast());
    }

    /**
     * Tests getCallsByPerson()
     */
    @Test
    public void testGetCallsByPerson() {
        try {
            sm = new SurveillanceManager("input/2-10p.txt", "input/2-15c.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, List<Call>> result = sm.getCallsByPerson();
        List<Call> i;
        Call j;

        i = result.remove("452-400-6023258"); // Amy
        j = i.removeFirst();
        assertEquals("DFVD2854", j.getId());
        j = i.removeFirst();
        assertEquals("YFZS9257", j.getId());
        j = i.removeFirst();
        assertEquals("JVUQ5899", j.getId());
        assertTrue(i.isEmpty());
        i = result.remove("518-052-2121645"); // Juan
        j = i.removeFirst();
        assertEquals("PNSG8983", j.getId());
        assertTrue(i.isEmpty());
        i = result.remove("810-634-0039046"); // Bart
        j = i.removeFirst();
        assertEquals("MGQP8092", j.getId());
        j = i.removeFirst();
        assertEquals("JBUQ9313", j.getId());
        j = i.removeFirst();
        assertEquals("CUTB1176", j.getId());
        j = i.removeFirst();
        assertEquals("QOTM6378", j.getId());
        assertTrue(i.isEmpty());
        i = result.remove("000-699-2972537"); // Eduardo
        j = i.removeFirst();
        assertEquals("AFTC9304", j.getId());
        j = i.removeFirst();
        assertEquals("FKYP1741", j.getId());
        j = i.removeFirst();
        assertEquals("CUTB1176", j.getId());
        j = i.removeFirst();
        assertEquals("QTCQ6800", j.getId());
        j = i.removeFirst();
        assertEquals("LLLP9108", j.getId());
        j = i.removeFirst();
        assertEquals("AUIO2544", j.getId());
        assertTrue(i.isEmpty());
        i = result.remove("157-089-1159597"); // Christopher
        j = i.removeFirst();
        assertEquals("CUTB1176", j.getId());
        j = i.removeFirst();
        assertEquals("PNSG8983", j.getId());
        j = i.removeFirst();
        assertEquals("QTCQ6800", j.getId());
        j = i.removeFirst();
        assertEquals("QMNY2198", j.getId());
        j = i.removeFirst();
        assertEquals("AUIO2544", j.getId());
        assertTrue(i.isEmpty());
        i = result.remove("928-618-6022384"); // Daniel
        j = i.removeFirst();
        assertEquals("MGQP8092", j.getId());
        j = i.removeFirst();
        assertEquals("AFTC9304", j.getId());
        j = i.removeFirst();
        assertEquals("FKYP1741", j.getId());
        j = i.removeFirst();
        assertEquals("JVUQ5899", j.getId());
        assertTrue(i.isEmpty());
        i = result.remove("418-364-0653421"); // Michael
        j = i.removeFirst();
        assertEquals("JBUQ9313", j.getId());
        j = i.removeFirst();
        assertEquals("QOTM6378", j.getId());
        assertTrue(i.isEmpty());
        i = result.remove("148-757-6582249"); // Jaime
        j = i.removeFirst();
        assertEquals("OXDN3467", j.getId());
        j = i.removeFirst();
        assertEquals("YFZS9257", j.getId());
        assertTrue(i.isEmpty());
        i = result.remove("556-206-6472197"); // Frances
        j = i.removeFirst();
        assertEquals("DFVD2854", j.getId());
        j = i.removeFirst();
        assertEquals("OXDN3467", j.getId());
        j = i.removeFirst();
        assertEquals("LLLP9108", j.getId());
        j = i.removeFirst();
        assertEquals("QMNY2198", j.getId());
        assertTrue(i.isEmpty());
        assertTrue(result.isEmpty());
    }

    /**
     * test that the function getPeopleByHop works
     */
    @Test
    public void testGetPeopleByHop() {
        try {
            sm = new SurveillanceManager("input/2-10p.txt", "input/2-15c.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Integer> result = sm.getPeopleByHop("148-757-6582249");
        assertEquals(1, (int) result.get("556-206-6472197"));
        assertEquals(1, (int) result.get("452-400-6023258"));
        assertEquals(2, (int) result.get("157-089-1159597"));
        assertEquals(2, (int) result.get("928-618-6022384"));
        assertEquals(2, (int) result.get("000-699-2972537"));
        assertEquals(3, (int) result.get("518-052-2121645"));
        assertEquals(3, (int) result.get("810-634-0039046"));
        assertEquals(4, (int) result.get("418-364-0653421"));

    }

    /**
     * Tests empty files
     */
    @Test
    public void testEmptyFiles() {
        try {
            sm = new SurveillanceManager("input/5-ep.txt", "input/2-15c.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Integer> resultByHop = sm.getPeopleByHop("148-757-6582249");
        assertTrue(resultByHop.isEmpty());
        assertEquals(0, resultByHop.size());

        Map<String, List<Call>> resultByPerson = sm.getCallsByPerson();
        assertTrue(resultByPerson.isEmpty());
        assertEquals(0, resultByPerson.size());

        try {
            sm = new SurveillanceManager("input/2-10p.txt", "input/5-ec.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        resultByHop = sm.getPeopleByHop("148-757-6582249");
        assertTrue(resultByHop.isEmpty());
        assertEquals(0, resultByHop.size());

        resultByPerson = sm.getCallsByPerson();
        assertTrue(resultByPerson.isEmpty());
        assertEquals(0, resultByPerson.size());

        try {
            sm = new SurveillanceManager("input/5-ep.txt", "input/5-ec.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        resultByHop = sm.getPeopleByHop("148-757-6582249");
        assertTrue(resultByHop.isEmpty());
        assertEquals(0, resultByHop.size());

        resultByPerson = sm.getCallsByPerson();
        assertTrue(resultByPerson.isEmpty());
        assertEquals(0, resultByPerson.size());
    }
}
