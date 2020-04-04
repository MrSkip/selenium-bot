package com.sombrainc.memsourcebot;

import org.apache.commons.cli.*;

public class PropertyUtils {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CHROME_DRIVER_PATH = "chrome_driver_path";

    private static final Options OPTIONS;
    private static CommandLine commandLine;

    static {
        OPTIONS = new Options();
        OPTIONS.addRequiredOption(USERNAME, USERNAME, true, "The sername");
        OPTIONS.addRequiredOption(PASSWORD, PASSWORD, true, "User password");
        OPTIONS.addRequiredOption(CHROME_DRIVER_PATH, CHROME_DRIVER_PATH, true, "Path to chrome driver");
    }

    private PropertyUtils() {
    }

    public static void load(final String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        commandLine = parser.parse(OPTIONS, args);
    }

    public static String getUsername() {
        return commandLine.getOptionValue(USERNAME);
    }

    public static String getPassword() {
        return commandLine.getOptionValue(PASSWORD);
    }

    public static String getChromeDriverPath() {
        return commandLine.getOptionValue(CHROME_DRIVER_PATH);
    }

}
