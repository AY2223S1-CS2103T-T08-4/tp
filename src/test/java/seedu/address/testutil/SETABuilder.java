package seedu.address.testutil;

import seedu.address.model.SETA;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * A utility class to help with building SETA objects.
 * Example usage: <br>
 *     {@code SETA ab = new SETABuilder().withPerson("John", "Doe").build();}
 */
public class SETABuilder {

    private SETA SETA;

    public SETABuilder() {
        SETA = new SETA();
    }

    public SETABuilder(SETA SETA) {
        this.SETA = SETA;
    }

    /**
     * Adds a new {@code Student} to the {@code SETA} that we are building.
     */
    public SETABuilder withStudent(Student student) {
        SETA.addStudent(student);
        return this;
    }

    /**
     * Adds a new {@code Question} to the {@code SETA} that we are building.
     */
    public SETABuilder withQuestion(Question question) {
        SETA.addQuestion(question);
        return this;
    }

    /**
     * Adds a new {@code Tutorial} to the {@code SETA} that we are building.
     */
    public SETABuilder withTutorial(Tutorial tutorial) {
        SETA.addTutorial(tutorial);
        return this;
    }

    public SETA build() {
        return SETA;
    }
}
