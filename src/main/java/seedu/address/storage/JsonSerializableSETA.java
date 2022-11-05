package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.SETA;
import seedu.address.model.ReadOnlySETA;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * An Immutable SETA that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableSETA {

    public static final String MESSAGE_DUPLICATE_QUESTION = "Questions list contains duplicate question(s).";
    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "Tutorials list contains duplicate tutorials(s).";

    private final List<JsonAdaptedQuestion> questions = new ArrayList<>();
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTutorial> tutorials = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableSETA} with the given persons questions tutorials.
     */
    @JsonCreator
    public JsonSerializableSETA(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                @JsonProperty("questions") List<JsonAdaptedQuestion> questions,
                                @JsonProperty("tutorials") List<JsonAdaptedTutorial> tutorials) {
        this.questions.addAll(questions);
        this.tutorials.addAll(tutorials);
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlySETA} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSETA}.
     */
    public JsonSerializableSETA(ReadOnlySETA source) {
        questions.addAll(source.getQuestionList().stream().map(JsonAdaptedQuestion::new).collect(Collectors.toList()));
        tutorials.addAll(source.getTutorialList().stream().map(JsonAdaptedTutorial::new).collect(Collectors.toList()));
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code SETA} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SETA toModelType() throws IllegalValueException {
        SETA SETA = new SETA();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (SETA.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            SETA.addStudent(student);
        }
        for (JsonAdaptedQuestion jsonAdaptedQuestion : questions) {
            Question question = jsonAdaptedQuestion.toModelType();
            if (SETA.hasQuestion(question)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_QUESTION);
            }
            SETA.addQuestion(question);
        }
        for (JsonAdaptedTutorial jsonAdaptedTutorial : tutorials) {
            Tutorial tutorial = jsonAdaptedTutorial.toModelType();
            if (SETA.hasTutorial(tutorial)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTORIAL);
            }
            SETA.addTutorial(tutorial);
        }
        return SETA;
    }
}
