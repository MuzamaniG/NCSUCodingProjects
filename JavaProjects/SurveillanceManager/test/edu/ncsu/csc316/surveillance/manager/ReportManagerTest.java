package edu.ncsu.csc316.surveillance.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Tests ReportManager.java
 * 
 * @author Muzamani Gausi
 * @author Jacob Phillips
 */
public class ReportManagerTest {

	/**
	 * Tests getCallsByPerson()
	 */
	@Test
	public void testGetCallsByPerson() {
		ReportManager rm;
		try {
			rm = new ReportManager("input/2-10p.txt", "input/2-15c.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
			return;
		}

		assertEquals("Calls involving 000-000-5555555 (No Calls) [\n" + //
				"   (none)\n" + //
				"]\nCalls involving 000-699-2972537 (Tordoff Eduardo) [\n" + //
				"   1/27/1971 at 1:36:59 AM involving 1 other number(s):\n" + //
				"      928-618-6022384 (Howell Daniel)\n" + //
				"   1/27/1971 at 1:36:59 AM involving 1 other number(s):\n" + //
				"      556-206-6472197 (Mastropietro Frances)\n" + //
				"   1/11/1984 at 0:47:58 AM involving 2 other number(s):\n" + //
				"      157-089-1159597 (McKendry Christopher)\n" + //
				"      810-634-0039046 (Bensel Bart)\n" + //
				"   8/4/1984 at 16:10:29 PM involving 1 other number(s):\n" + //
				"      157-089-1159597 (McKendry Christopher)\n" + //
				"   11/29/2003 at 15:01:35 PM involving 1 other number(s):\n" + //
				"      157-089-1159597 (McKendry Christopher)\n" + //
				"   3/16/2017 at 8:27:17 AM involving 1 other number(s):\n" + //
				"      928-618-6022384 (Howell Daniel)\n" + //
				"]\n" + //
				"Calls involving 148-757-6582249 (Rude Jaime) [\n" + //
				"   9/22/2009 at 0:34:53 AM involving 1 other number(s):\n" + //
				"      556-206-6472197 (Mastropietro Frances)\n" + //
				"   5/12/2020 at 8:11:48 AM involving 1 other number(s):\n" + //
				"      452-400-6023258 (Purvis Amy)\n" + //
				"]\n" + //
				"Calls involving 157-089-1159597 (McKendry Christopher) [\n" + //
				"   9/27/1974 at 13:29:42 PM involving 1 other number(s):\n" + //
				"      518-052-2121645 (Sowards Juan)\n" + //
				"   5/12/1979 at 17:05:38 PM involving 1 other number(s):\n" + //
				"      556-206-6472197 (Mastropietro Frances)\n" + //
				"   1/11/1984 at 0:47:58 AM involving 2 other number(s):\n" + //
				"      000-699-2972537 (Tordoff Eduardo)\n" + //
				"      810-634-0039046 (Bensel Bart)\n" + //
				"   8/4/1984 at 16:10:29 PM involving 1 other number(s):\n" + //
				"      000-699-2972537 (Tordoff Eduardo)\n" + //
				"   11/29/2003 at 15:01:35 PM involving 1 other number(s):\n" + //
				"      000-699-2972537 (Tordoff Eduardo)\n" + //
				"]\n" + //
				"Calls involving 418-364-0653421 (Defoe Michael) [\n" + //
				"   9/5/1970 at 3:06:12 AM involving 1 other number(s):\n" + //
				"      810-634-0039046 (Bensel Bart)\n" + //
				"   4/13/1973 at 9:07:40 AM involving 1 other number(s):\n" + //
				"      810-634-0039046 (Bensel Bart)\n" + //
				"]\n" + //
				"Calls involving 452-400-6023258 (Purvis Amy) [\n" + //
				"   10/22/1981 at 1:19:36 AM involving 1 other number(s):\n" + //
				"      928-618-6022384 (Howell Daniel)\n" + //
				"   4/12/2004 at 14:50:52 PM involving 1 other number(s):\n" + //
				"      556-206-6472197 (Mastropietro Frances)\n" + //
				"   5/12/2020 at 8:11:48 AM involving 1 other number(s):\n" + //
				"      148-757-6582249 (Rude Jaime)\n" + //
				"]\n" + //
				"Calls involving 518-052-2121645 (Sowards Juan) [\n" + //
				"   9/27/1974 at 13:29:42 PM involving 1 other number(s):\n" + //
				"      157-089-1159597 (McKendry Christopher)\n" + //
				"]\n" + //
				"Calls involving 555-555-5555555 (No Calls) [\n" + //
				"   (none)\n" + //
				"]\n" +
				"Calls involving 556-206-6472197 (Mastropietro Frances) [\n" + //
				"   1/27/1971 at 1:36:59 AM involving 1 other number(s):\n" + //
				"      000-699-2972537 (Tordoff Eduardo)\n" + //
				"   5/12/1979 at 17:05:38 PM involving 1 other number(s):\n" + //
				"      157-089-1159597 (McKendry Christopher)\n" + //
				"   4/12/2004 at 14:50:52 PM involving 1 other number(s):\n" + //
				"      452-400-6023258 (Purvis Amy)\n" + //
				"   9/22/2009 at 0:34:53 AM involving 1 other number(s):\n" + //
				"      148-757-6582249 (Rude Jaime)\n" + //
				"]\n" + //
				"Calls involving 810-634-0039046 (Bensel Bart) [\n" + //
				"   9/5/1970 at 3:06:12 AM involving 1 other number(s):\n" + //
				"      418-364-0653421 (Defoe Michael)\n" + //
				"   4/13/1973 at 9:07:40 AM involving 1 other number(s):\n" + //
				"      418-364-0653421 (Defoe Michael)\n" + //
				"   1/11/1984 at 0:47:58 AM involving 2 other number(s):\n" + //
				"      000-699-2972537 (Tordoff Eduardo)\n" + //
				"      157-089-1159597 (McKendry Christopher)\n" + //
				"   7/8/2022 at 23:32:32 PM involving 1 other number(s):\n" + //
				"      928-618-6022384 (Howell Daniel)\n" + //
				"]\n" + //
				"Calls involving 928-618-6022384 (Howell Daniel) [\n" + //
				"   1/27/1971 at 1:36:59 AM involving 1 other number(s):\n" + //
				"      000-699-2972537 (Tordoff Eduardo)\n" + //
				"   10/22/1981 at 1:19:36 AM involving 1 other number(s):\n" + //
				"      452-400-6023258 (Purvis Amy)\n" + //
				"   3/16/2017 at 8:27:17 AM involving 1 other number(s):\n" + //
				"      000-699-2972537 (Tordoff Eduardo)\n" + //
				"   7/8/2022 at 23:32:32 PM involving 1 other number(s):\n" + //
				"      810-634-0039046 (Bensel Bart)\n" + //
				"]", rm.getCallsByPerson());
	}

	/**
	 * Tests getPeopleCoveredByWarrant()
	 */
	@Test
	public void testGetPeopleCoveredByWarrant() {
		ReportManager rm;
		try {
			rm = new ReportManager("input/4-10p.txt", "input/4-15c.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
			return;
		}

		assertEquals("Phone numbers covered by a 5-hop warrant originating from 148-757-6582249 (Rude Jaime) [\n" + //
				"   1-hop: 452-400-6023258 (Purvis Amy)\n" + //
				"   1-hop: 556-206-6472197 (Mastropietro Frances)\n" + //
				"   2-hop: 928-618-6022384 (McKendry Christopher)\n" + //
				"   2-hop: 000-699-2972537 (McKendry Eduardo)\n" + //
				"   2-hop: 157-089-1159597 (McKendry Eduardo)\n" +
				"   2-hop: 928-618-6022386 (Defoe Shaq)\n" +
				"   2-hop: 928-618-6022385 (McKendry Shaq)\n" + //
				"   3-hop: 810-634-0039046 (Bensel Bart)\n" + //
				"   3-hop: 518-052-2121645 (Sowards Juan)\n" + //
				"   4-hop: 418-364-0653421 (Defoe Michael)\n]", rm.getPeopleCoveredByWarrant(5, "148-757-6582249"));

		assertEquals("Number of hops must be greater than 0.", rm.getPeopleCoveredByWarrant(-1, ""));
		assertEquals("Phone number 148-757-6582240 does not exist.",
				rm.getPeopleCoveredByWarrant(1, "148-757-6582240"));

	}

	/**
	 * Tests an example
	 */
	@Test
	public void testExample() {
		ReportManager rm;
		try {
			rm = new ReportManager("input/exp.txt", "input/exc.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
			return;
		}

		assertEquals("Calls involving 134-530-7421728 (Roseanna Herman) [\n" + //
				"   9/2/2019 at 10:11:43 AM involving 1 other number(s):\n" + //
				"      541-777-4740981 (Brett Mueller)\n" + //
				"   9/9/2019 at 11:10:34 AM involving 1 other number(s):\n" + //
				"      289-378-3996038 (Daniel Walker)\n" + //
				"]\n" + //
				"Calls involving 289-378-3996038 (Daniel Walker) [\n" + //
				"   9/22/2014 at 14:03:37 PM involving 1 other number(s):\n" + //
				"      881-633-0099232 (Enoch Quitzon)\n" + //
				"   9/9/2019 at 11:10:34 AM involving 1 other number(s):\n" + //
				"      134-530-7421728 (Roseanna Herman)\n" + //
				"]\n" + //
				"Calls involving 358-721-0140950 (Tereasa Kuphal) [\n" + //
				"   9/29/2014 at 14:03:37 PM involving 1 other number(s):\n" + //
				"      541-777-4740981 (Brett Mueller)\n" + //
				"   4/22/2020 at 14:56:45 PM involving 1 other number(s):\n" + //
				"      663-879-6377778 (Rudolph Buckridge)\n" + //
				"]\n" + //
				"Calls involving 442-000-9865092 (Albertina Braun) [\n" + //
				"   4/29/2020 at 19:26:35 PM involving 2 other number(s):\n" + //
				"      663-879-6377778 (Rudolph Buckridge)\n" + //
				"      903-282-4112077 (Tomas Nguyen)\n" + //
				"]\n" + //
				"Calls involving 541-777-4740981 (Brett Mueller) [\n" + //
				"   9/29/2014 at 14:03:37 PM involving 1 other number(s):\n" + //
				"      358-721-0140950 (Tereasa Kuphal)\n" + //
				"   5/14/2017 at 10:52:47 AM involving 1 other number(s):\n" + //
				"      663-879-6377778 (Rudolph Buckridge)\n" + //
				"   9/2/2019 at 10:11:43 AM involving 1 other number(s):\n" + //
				"      134-530-7421728 (Roseanna Herman)\n" + //
				"]\n" + //
				"Calls involving 663-879-6377778 (Rudolph Buckridge) [\n" + //
				"   5/14/2017 at 10:52:47 AM involving 1 other number(s):\n" + //
				"      541-777-4740981 (Brett Mueller)\n" + //
				"   4/22/2020 at 14:56:45 PM involving 1 other number(s):\n" + //
				"      358-721-0140950 (Tereasa Kuphal)\n" + //
				"   4/29/2020 at 19:26:35 PM involving 2 other number(s):\n" + //
				"      442-000-9865092 (Albertina Braun)\n" + //
				"      903-282-4112077 (Tomas Nguyen)\n" + //
				"]\n" + //
				"Calls involving 853-257-0109509 (Sarai Rodriguez) [\n" + //
				"   (none)\n" + //
				"]\n" +
				"Calls involving 881-633-0099232 (Enoch Quitzon) [\n" + //
				"   9/22/2014 at 14:03:37 PM involving 1 other number(s):\n" + //
				"      289-378-3996038 (Daniel Walker)\n" + //
				"]\n" + //
				"Calls involving 903-282-4112077 (Tomas Nguyen) [\n" + //
				"   4/29/2020 at 19:26:35 PM involving 2 other number(s):\n" + //
				"      442-000-9865092 (Albertina Braun)\n" + //
				"      663-879-6377778 (Rudolph Buckridge)\n" + //
				"]", rm.getCallsByPerson());

		assertEquals("Phone numbers covered by a 2-hop warrant originating from 358-721-0140950 (Tereasa Kuphal) [\n" + //
				"   1-hop: 663-879-6377778 (Rudolph Buckridge)\n" + //
				"   1-hop: 541-777-4740981 (Brett Mueller)\n" + //
				"   2-hop: 442-000-9865092 (Albertina Braun)\n" + //
				"   2-hop: 134-530-7421728 (Roseanna Herman)\n" + //
				"   2-hop: 903-282-4112077 (Tomas Nguyen)\n" + //
				"]", rm.getPeopleCoveredByWarrant(2, "358-721-0140950"));

		assertEquals("Phone numbers covered by a 4-hop warrant originating from 358-721-0140950 (Tereasa Kuphal) [\n" + //
				"   1-hop: 663-879-6377778 (Rudolph Buckridge)\n" + //
				"   1-hop: 541-777-4740981 (Brett Mueller)\n" + //
				"   2-hop: 442-000-9865092 (Albertina Braun)\n" + //
				"   2-hop: 134-530-7421728 (Roseanna Herman)\n" + //
				"   2-hop: 903-282-4112077 (Tomas Nguyen)\n" + //
				"   3-hop: 289-378-3996038 (Daniel Walker)\n" + //
				"   4-hop: 881-633-0099232 (Enoch Quitzon)\n" + //
				"]", rm.getPeopleCoveredByWarrant(4, "358-721-0140950"));
	}

	/**
	 * Tests empty files
	 */
	@Test
	public void testEmptyFiles() {
		ReportManager rm;

		try {
			rm = new ReportManager("input/5-ep.txt", "input/2-15c.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
			return;
		}

		assertEquals("No people information was provided.", rm.getPeopleCoveredByWarrant(1, "148-757-6582249"));
		assertEquals("No people information was provided.", rm.getCallsByPerson());

		try {
			rm = new ReportManager("input/2-10p.txt", "input/5-ec.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		assertEquals("No calls exist in the call logs.", rm.getPeopleCoveredByWarrant(1, "148-757-6582249"));
		assertEquals("No calls exist in the call logs.", rm.getCallsByPerson());

		try {
			rm = new ReportManager("input/5-ep.txt", "input/5-ec.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		assertEquals("No people information was provided.", rm.getPeopleCoveredByWarrant(1, "148-757-6582249"));
		assertEquals("No people information was provided.", rm.getCallsByPerson());
	}
}
