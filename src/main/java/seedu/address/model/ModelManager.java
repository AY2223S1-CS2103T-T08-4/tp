package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SETA SETA;
    private final UserPrefs userPrefs;
    private final FilteredList<Question> filteredQuestions;
    private final FilteredList<Tutorial> filteredTutorials;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given seta and userPrefs.
     */
    public ModelManager(ReadOnlySETA seta, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(seta, userPrefs);

        logger.fine("Initializing with address book: " + seta + " and user prefs " + userPrefs);

        this.SETA = new SETA(seta);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredQuestions = new FilteredList<>(this.SETA.getQuestionList());
        filteredTutorials = new FilteredList<>(this.SETA.getTutorialList());
        filteredStudents = new FilteredList<>(this.SETA.getStudentList());
    }

    public ModelManager() {
        this(new SETA(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getSETAFilePath() {
        return userPrefs.getSETAFilePath();
    }

    @Override
    public void setSETAFilePath(Path setaFilePath) {
        requireNonNull(setaFilePath);
        userPrefs.setSETAFilePath(setaFilePath);
    }

    //=========== SETA ================================================================================

    @Override
    public void setSETA(ReadOnlySETA seta) {
        this.SETA.resetData(seta);
    }

    @Override
    public ReadOnlySETA getSETA() {
        return SETA;
    }

    //=========== Student ================================================================================

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return SETA.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        SETA.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        SETA.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        SETA.setStudent(target, editedStudent);
    }


    //=========== Question ================================================================================

    @Override
    public boolean hasQuestion(Question question) {
        requireNonNull(question);
        return SETA.hasQuestion(question);
    }

    @Override
    public void deleteQuestion(Question target) {
        SETA.removeQuestion(target);
    }

    @Override
    public void addQuestion(Question question) {
        SETA.addQuestion(question);
        updateFilteredQuestionList(PREDICATE_SHOW_ALL_QUESTIONS);
    }

    @Override
    public void setQuestion(Question target, Question editedQuestion) {
        requireAllNonNull(target, editedQuestion);

        SETA.setQuestion(target, editedQuestion);
    }

    //=========== Tutorial ================================================================================

    @Override
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return SETA.hasTutorial(tutorial);
    }

    @Override
    public void deleteTutorial(Tutorial target) {
        SETA.removeTutorial(target);
    }

    @Override
    public void addTutorial(Tutorial tutorial) {
        SETA.addTutorial(tutorial);
        updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);
    }

    @Override
    public void setTutorial(Tutorial target, Tutorial editedTutorial) {
        requireAllNonNull(target, editedTutorial);

        SETA.setTutorial(target, editedTutorial);
    }


    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedSETA}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== Filtered Question List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Question} backed by the internal list of
     * {@code versionedSETA}
     */
    @Override
    public ObservableList<Question> getFilteredQuestionList() {
        return filteredQuestions;
    }

    @Override
    public void updateFilteredQuestionList(Predicate<Question> predicate) {
        requireNonNull(predicate);
        filteredQuestions.setPredicate(predicate);
    }
    //=========== Filtered Tutorial List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Tutorial} backed by the internal list of
     * {@code versionedSETA}
     */
    @Override
    public ObservableList<Tutorial> getFilteredTutorialList() {
        return filteredTutorials;
    }

    @Override
    public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
        requireNonNull(predicate);
        filteredTutorials.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return SETA.equals(other.SETA)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredQuestions.equals(other.filteredQuestions)
                && filteredTutorials.equals(other.filteredTutorials);
    }

}
