package courseSequencer.util;

import java.io.File;

/**
 * The `FileProcessorInterface` interface defines methods for reading and
 * writing files.
 */
public interface FileProcessorInterface {

    /**
     * Opens a file for reading.
     *
     * @param f        The File object to be opened for reading.
     * @param errorFIn The FileProcessorInterface object for error handling
     */
    public void openFile(File fIn, FileProcessorInterface errorFIn);

    /**
     * Checks if a file exists and can be accessed.
     *
     * @param f        The File object to be checked for existence.
     * @param errorFIn The FileProcessorInterface object for error handling
     * @return The same File object if it exists, or null if it doesn't.
     */
    public File checkFileExists(File f, FileProcessorInterface errorFIn);

    /**
     * Parses a file path into a File object and checks if it exists.
     *
     * @param path     The path of the file to be parsed and checked for existence.
     * @param errorFIn The FileProcessorInterface object for error handling
     * @return The File object if it exists, or null if it doesn't.
     */
    public File parseArgument(String path, FileProcessorInterface errorFIn);

    /**
     * Reads the next line from the currently open input file.
     *
     * @return The next line of text from the input file, or null if there are no
     *         more lines.
     */
    public String readLine();

    /**
     * Checks if there is another line to be read from the input file.
     *
     * @return true if there is another line to be read, false otherwise.
     */
    public boolean hasNextLine();

    /**
     * Closes the currently open input file.
     */
    public void closeFile();

    /**
     * Opens a file for writing. If the file already exists, it is deleted and
     * recreated.
     *
     * @param path The path of the file to be opened for writing.
     */
    public void openFileWriting(String path);

    /**
     * Writes a line of text to the currently open output file.
     *
     * @param body The text to be written to the file.
     */
    public void writeToFile(String body);

    /**
     * Closes the currently open output file.
     */
    public void closeFileWriting();
}