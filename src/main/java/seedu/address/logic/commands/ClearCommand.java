package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.SETA;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "SETA has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSETA(new SETA());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
