package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.question.Question;

/**
 * An UI component that displays information of a {@code Question}.
 */
public class QuestionCard extends UiPart<Region> {

    private static final String FXML = "QuestionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Question question;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;


    /**
     * Creates a {@code QuestionCode} with the given {@code Question} and index to display.
     */
    public QuestionCard(Question question, int displayedIndex) {
        super(FXML);
        this.question = question;
        id.setText(displayedIndex + ". ");
        description.setText(question.getDescription().descriptionString);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QuestionCard)) {
            return false;
        }

        // state check
        QuestionCard card = (QuestionCard) other;
        return id.getText().equals(card.id.getText())
                && question.equals(card.question);
    }
}