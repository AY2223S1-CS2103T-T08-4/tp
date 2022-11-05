package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.SETA;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableSETATest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSETATest");
    private static final Path TYPICAL_STUDENT_FILE = TEST_DATA_FOLDER.resolve("typicalStudentSETA.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentSETA.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentSETA.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableSETA dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENT_FILE,
                JsonSerializableSETA.class).get();
        SETA SETAFromFile = dataFromFile.toModelType();
        SETA typicalPersonsSETA = TypicalStudents.getTypicalSETA();
        assertEquals(SETAFromFile, typicalPersonsSETA);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSETA dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableSETA.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableSETA dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableSETA.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSETA.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
