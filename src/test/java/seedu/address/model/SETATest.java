package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalSETA;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.StudentBuilder;

public class SETATest {

    private final SETA SETA = new SETA();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), SETA.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SETA.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySETA_replacesData() {
        SETA newData = getTypicalSETA();
        SETA.resetData(newData);
        assertEquals(newData, SETA);
    }

    /*@Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder().withName("Alice Pauline")
                .withTelegram("@AlicePauline").withEmail("alice@example.com")
                .withResponse("2")
                .withAttendance("1").build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        SETAStub newData = new SETAStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> seta.resetData(newData));
    }*/

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SETA.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInSETA_returnsFalse() {
        assertFalse(SETA.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInSETA_returnsTrue() {
        SETA.addStudent(ALICE);
        assertTrue(SETA.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInSETA_returnsTrue() {
        SETA.addStudent(ALICE);
        Student editedAlice = new StudentBuilder().withName("Alice Pauline")
                .withTelegram("@AlicePauline").withEmail("alice@example.com")
                .withResponse("2")
                .withAttendance("1").build();
        assertTrue(SETA.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> SETA.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlySETA whose persons list can violate interface constraints.
     */
    private static class SETAStub implements ReadOnlySETA {
        private final ObservableList<Tutorial> tutorials = FXCollections.observableArrayList();
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        SETAStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        SETAStub(Collection<Student> students, Collection<Tutorial> tutorials) {
            this.students.setAll(students);
            this.tutorials.setAll(tutorials);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Question> getQuestionList() {
            return null;
        }

        @Override
        public ObservableList<Tutorial> getTutorialList() {
            return tutorials;
        }
    }

}
