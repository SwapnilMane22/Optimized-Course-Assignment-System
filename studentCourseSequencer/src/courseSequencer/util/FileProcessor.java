package courseSequencer.util;

/**
 * This class represents file handling methods for reading and writing 
 * files. It provides functionality to open, read, and close files for 
 * input and output.
 */
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor implements FileProcessorInterface {

    private Scanner readFile; // Scanner for reading input files
    private PrintWriter writeFile; // PrintWriter for writing output files

    /**
     * Constructs a new FileProcessor with uninitialized input and output streams.
     */
    public FileProcessor() {
        readFile = null;
        writeFile = null;
    }

    /**
     * Opens a file for reading. If a file is already open, it is closed before
     * opening the new one.
     *
     * @param fIn      The File object to be opened for reading.
     * @param errorFIn The FileProcessorInterface object for error handling
     */
    public void openFile(File fIn, FileProcessorInterface errorFIn) {
        if (readFile != null) {
            // Close the previously opened file
            readFile.close();
        }

        try {
            readFile = new Scanner(fIn);
        } catch (FileNotFoundException e) {
            // Handle exception
            System.err.println("Error: File not found: " + fIn);
            errorFIn.writeToFile("Error: File not found: " + fIn);
            e.printStackTrace();
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error: " + e);
            errorFIn.writeToFile("Error: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Checks if a file exists and can be accessed.
     *
     * @param f        The File object to be checked for existence.
     * @param errorFIn The FileProcessorInterface object for error handling
     * @return The same File object if it exists, or null if it doesn't.
     */
    public File checkFileExists(File f, FileProcessorInterface errorFIn) {
        if (!f.exists()) {
            System.err.println("Error: File not found: " + f);
            errorFIn.writeToFile("Error: File not found: " + f);
            System.exit(1);
        }
        return f;
    }

    /**
     * Parses a file path into a File object and checks if it exists.
     *
     * @param path     The path of the file to be parsed and checked for existence.
     * @param errorFIn The FileProcessorInterface object for error handling
     * @return The File object if it exists, or null if it doesn't.
     */
    public File parseArgument(String path, FileProcessorInterface errorFIn) {
        File newFile = new File(path);
        newFile = checkFileExists(newFile, errorFIn);
        return newFile;
    }

    /**
     * Reads the next line from the currently open input file.
     *
     * @return The next line of text from the input file, or null if there are no
     *         more lines.
     */
    public String readLine() {
        if (readFile != null && readFile.hasNextLine()) {
            return readFile.nextLine();
        }
        // closeFile();
        return null;
    }

    /**
     * Checks if there is another line to be read from the input file.
     *
     * @return true if there is another line to be read, false otherwise.
     */
    public boolean hasNextLine() {
        if (!readFile.hasNextLine()) {
            return false;
        }
        return true;
    }

    /**
     * Closes the currently open input file.
     */
    public void closeFile() {
        if (!readFile.hasNextLine()) {
            try {
                readFile.close();
            } catch (Exception e) {
                System.err.println("Error: " + e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Opens a file for writing. If the file already exists, it is deleted and
     * recreated.
     *
     * @param path The path of the file to be opened for writing.
     */
    public void openFileWriting(String path) {
        File writingFile = new File(path);
        try {
            if (writingFile.exists()) {
                writingFile.delete();
            }
            writingFile.createNewFile();
            writeFile = new PrintWriter(new FileWriter(writingFile));
        } catch (IOException e) {
            System.err.println("Error: Not able to open file for writing: " + writingFile);
            e.printStackTrace();
        } catch (Exception e) {
            // Handle exceptions and display an error message
            System.err.println("Error: " + e);
            e.printStackTrace();// Print the stack trace for debugging
        }
    }

    /**
     * Writes a line of text to the currently open output file.
     *
     * @param body The text to be written to the file.
     */
    public void writeToFile(String body) {
        if (writeFile != null) {
            try {
                writeFile.print(body);
                writeFile.flush();
            } catch (Exception e) {
                // Handle exceptions and display an error message
                System.err.println("Error writing to file.");
                e.printStackTrace();// Print the stack trace for debugging
            }
        }
    }

    /**
     * Closes the currently open output file.
     */
    public void closeFileWriting() {
        if (writeFile != null) {
            try {
                writeFile.close();
            } catch (Exception e) {
                // Handle exception when closing file
                System.err.println("Error: " + e);
                e.printStackTrace();// Print the stack trace for debugging
            }
        }
    }
}