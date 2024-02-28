package event;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import event.Printing;

public class Printing {
    private static final Logger LOGGER = Logger.getLogger(Printing.class.getName());

    static {
        // Create a console handler with a custom formatter that only prints the message
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                String message = super.formatMessage(record);
                String ANSI_RED = "\u001B[36m";
                String ANSI_RESET = "\u001B[0m";
                return ANSI_RED + message + ANSI_RESET;
            }
        });

        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(consoleHandler);
    }

    public void printSomething( String msg ) {
        LOGGER.log(Level.INFO, msg);
    }
}