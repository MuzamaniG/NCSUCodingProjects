package edu.ncsu.csc316.surveillance.ui;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.ncsu.csc316.surveillance.dsa.DataStructure;
import edu.ncsu.csc316.surveillance.manager.ReportManager;

/**
 * UI for SurveillanceManager
 */
public class SurveillanceManagerGUI {

    /**
     * ReportManager instance to use
     */
    static ReportManager rm;

    private static final String[] DATA_OPTIONS = {
            "Show Calls Sorted By Person",
            "Show Phone Numbers Sorted By Hops",
            "Exit"
    };

    /**
     * Main method of the program. Can use either GUI or CLI mode.
     * 
     * @param args CLI arguments to use in CLI mode.
     */
    public static void main(String[] args) {
        if (args.length == 4) {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            try {
                System.out.println("Current time before processing: " + Instant.now().toString());

                rm = new ReportManager(args[1].substring(2),
                        args[2].substring(2),
                        getDSFromString(args[0].substring(2)));

                if (args[3].charAt(2) == 'p')
                    System.out.println(rm.getCallsByPerson());
                else
                    System.out.println(rm.getPeopleCoveredByWarrant(
                            Integer.parseInt(args[3].substring(4).split(",")[0]),
                            args[3].substring(4).split(",")[1]));

                System.out.println("Current time after processing: " + Instant.now().toString());

                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
                usage();
            }
        } else if (args.length == 1 && args[0].equals("--help"))
            usage();

        startReportManager(null);

        int loop = JOptionPane.showOptionDialog(null,
                "What to do with call logs?",
                "Operation Choosing", 0,
                JOptionPane.QUESTION_MESSAGE,
                null, DATA_OPTIONS, null);

        JScrollPane operationResult;
        JDialog resultViewer = new JDialog();
        resultViewer.setSize(800, 450);
        resultViewer.setTitle("Operation Result");
        resultViewer.setLocationRelativeTo(null);
        resultViewer.setModal(true);
        do {
            try {
                resultViewer = new JDialog();
                resultViewer.setSize(800, 450);
                resultViewer.setTitle("Operation Result");
                resultViewer.setLocationRelativeTo(null);
                resultViewer.setModal(true);

                if (loop == 0) {

                    operationResult = new JScrollPane(new JTextArea(rm.getCallsByPerson()));
                    operationResult.setMaximumSize(new Dimension(500, 500));
                } else {

                    int hops = Integer.parseInt(JOptionPane.showInputDialog("How many hops to limit the output to?"));
                    String originPhoneNumber = JOptionPane.showInputDialog("What phone number to start from?");

                    operationResult = new JScrollPane(
                            new JTextArea(rm.getPeopleCoveredByWarrant(hops, originPhoneNumber)));
                    operationResult.setMaximumSize(new Dimension(500, 500));
                }

                resultViewer.add(operationResult);
                resultViewer.setVisible(true);

                resultViewer.remove(operationResult);
                loop = JOptionPane.showOptionDialog(null,
                        "What to do with call logs?",
                        "Operation Choosing", 0,
                        JOptionPane.QUESTION_MESSAGE,
                        null, DATA_OPTIONS, null);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getCause() + ": " + e.getMessage());
            }
        } while (loop != 2);

        System.exit(0);
    }

    /**
     * Prints out a usage message to the user such that the user will understand how
     * to properly provide command line input to the program.
     */
    private static void usage() {
        System.out.println(
                "Usage: SurveillanceManagerGUI d=[u|s] p=(PeopleFile.txt) c=(CallFile.txt) o=[p|(h=_,_)]\n" +
                        "\tOrder matters. If all options are not filled out as expected, all options will be ignored. Timestamps will be output before and after processing.\n"
                        +
                        "\tSpecifying map types (d=_):\n" +
                        "\tu for unorderedlinkedmap\n" +
                        "\ts for searchtable\n" +
                        "\tanything else for skiplist (its the default, will be used if d=_ is omitted)\n" +
                        "\tSpecifying Files (p=_ c=_):\n" +
                        "\tp=_ to specify a file containing people to use.\n" +
                        "\tc=_ to specify a file containing calls to use.\n" +
                        "\tSpecifying Output Operation (o=_):\n" +
                        "\tp to get calls by person.\n" +
                        "\th to get people by how many hops away they are from a specified person. (h=_,_)\n" +
                        "\t\tThe first blank should be replaced by a positive integer specifying how many phone numbers to hop to and fro while listing before stopping.\n"
                        + "\t\tThe second blank should be the phone number to look from.\n");
        System.exit(1);
    }

    /**
     * Returns a DataStructure enum based on the given string. For use in CLI.
     * 
     * @param substring string to read from
     * @return DataStructure enum read
     */
    private static DataStructure getDSFromString(String substring) {
        if ("u".equals(substring))
            return DataStructure.UNORDEREDLINKEDMAP;
        else if ("s".equals(substring))
            return DataStructure.SEARCHTABLE;
        else
            return DataStructure.SKIPLIST;

    }

    /**
     * Contains the GUI components that are required to read a file and
     * DataStructure.
     * 
     * @param dS null for default, DataStrucutre enum to change map type otherwise.
     */
    private static void startReportManager(DataStructure dS) {

        boolean foundFiles = false;
        JFileChooser fileOpener;
        File f;
        String peopleFile, callsFile;
        do {
            JOptionPane.showMessageDialog(null,
                    "Please proceed to specify 2 files. The first one will be list of people, and the second will be the log of calls.");

            try {

                fileOpener = new JFileChooser();
                fileOpener.showOpenDialog(null);
                f = fileOpener.getSelectedFile();
                if (f == null)
                    throw new FileNotFoundException();
                peopleFile = f.getPath();

                fileOpener = new JFileChooser();
                fileOpener.showOpenDialog(null);
                f = fileOpener.getSelectedFile();
                if (f == null)
                    throw new FileNotFoundException();
                callsFile = f.getPath();

                if (dS == null)
                    rm = new ReportManager(peopleFile, callsFile);
                else
                    rm = new ReportManager(peopleFile, callsFile, dS);

                foundFiles = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "The specified files were unable to be found or opened.");
            }

        } while (!foundFiles);
    }

}
