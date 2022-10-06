package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Content;
import seedu.address.model.tutorial.Group;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;


/**
 * Adds a tutorial in the address book.
 */
public class AddTutorialCommand extends Command {

    public static final String COMMAND_WORD = "addtut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial to the address book. "
            + "Parameters: "
            + PREFIX_GROUP + "GROUP NUMBER "
            + PREFIX_CONTENT + "CONTENT "
            + PREFIX_TIME + "TIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "T08 "
            + PREFIX_CONTENT + "UML Diagram "
            + PREFIX_TIME + "2022-10-01T08:00:00";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Addtut command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Group: %1$s, Content: %2$s, Time: %3$s";
    // I may need to change this later. The time format might need to be specified clearer.

    private final Tutorial tutorial;

    /**
     * Creates an AddTutorialCommand to add the specified {@code Tutorial}
     */
    public AddTutorialCommand(Tutorial tutorial) {
        requireAllNonNull(tutorial);

        this.tutorial = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, tutorial.getGroup(),
                tutorial.getContent(), tutorial.getTime()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTutorialCommand)) {
            return false;
        }

        // state check
        AddTutorialCommand e = (AddTutorialCommand) other;
        return tutorial.equals(e);
    }
}
