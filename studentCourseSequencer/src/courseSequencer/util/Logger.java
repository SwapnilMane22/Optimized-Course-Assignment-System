package courseSequencer.util;

/**
 * The Logger class provides a simple way to manage and display debug messages
 * with different levels.
 */
public class Logger {

    /**
     * Enum to define debug levels.
     */
    public static enum DebugLevel {
        CONSTRUCTOR, FILE_PROCESSOR, DEBUG, NONE
    };

    /**
     * Represents the current debug level.
     */
    public static DebugLevel debugLevel;

    /**
     * Gets the current debug level.
     *
     * @return The current debug level.
     */
    public static DebugLevel getDebugLevel() {
        return debugLevel;
    }

    /**
     * Sets the debug level based on an integer value.
     *
     * @param levelIn The integer value representing the desired debug level.
     */
    public static void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 1:
                debugLevel = DebugLevel.CONSTRUCTOR;
                break;
            case 2:
                debugLevel = DebugLevel.FILE_PROCESSOR;
                break;
            case 3:
                debugLevel = DebugLevel.DEBUG;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    /**
     * Sets the debug level based on a DebugLevel enum.
     *
     * @param levelIn The DebugLevel enum representing the desired debug level.
     */
    public static void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    /**
     * Writes a debug message to the console and a file if the specified debug level
     * matches the set debug level.
     *
     * @param messageIn The message to be displayed.
     * @param levelIn   The debug level of the message.
     * @param fIn       The file processor for writing messages to a file.
     */
    public static void writeMessage(String messageIn, DebugLevel levelIn, FileProcessorInterface fIn) {
        try {
            // System.out.println("Level: " + levelIn);
            switch (levelIn) {
                case CONSTRUCTOR:
                    System.out.println("Constructor message: " + messageIn);
                    fIn.writeToFile("Constructor message: " + messageIn);
                    break;
                case FILE_PROCESSOR:
                    System.err.println("File Processor message: " + messageIn);
                    fIn.writeToFile("File Processor message: " + messageIn);
                    break;
                case DEBUG:
                    System.out.println(messageIn + "\n");
                    fIn.writeToFile("\n" + messageIn);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Provides a string representation of the current debug level.
     *
     * @return A string indicating the current debug level.
     */
    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}