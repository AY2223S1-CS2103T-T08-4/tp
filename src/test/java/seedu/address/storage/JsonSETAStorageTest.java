package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.SETA;
import seedu.address.model.ReadOnlySETA;

public class JsonSETAStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSETAStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSETA_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSETA(null));
    }

    private java.util.Optional<ReadOnlySETA> readSETA(String filePath) throws Exception {
        return new JsonSETAStorage(Paths.get(filePath)).readSETA(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSETA("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSETA("notJsonFormatSETA.json"));
    }

    @Test
    public void readSETA_invalidPersonSETA_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSETA("invalidStudentSETA.json"));
    }

    @Test
    public void readSETA_invalidAndValidPersonSETA_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSETA("invalidAndValidPersonSETA.json"));
    }

    /*@Test
    public void readAndSaveSETA_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSETA.json");
        SETA original = getTypicalSETA();
        JsonSETAStorage jsonSETAStorage = new JsonSETAStorage(filePath);

        // Save in new file and read back
        jsonSETAStorage.saveSETA(original, filePath);
        ReadOnlySETA readBack = jsonSETAStorage.readSETA(filePath).get();
        assertEquals(original, new SETA(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonSETAStorage.saveSETA(original, filePath);
        readBack = jsonSETAStorage.readSETA(filePath).get();
        assertEquals(original, new SETA(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonSETAStorage.saveSETA(original); // file path not specified
        readBack = jsonSETAStorage.readSETA().get(); // file path not specified
        assertEquals(original, new SETA(readBack));

    }*/
    // todo: figure out the storage

    @Test
    public void saveSETA_nullSETA_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSETA(null, "SomeFile.json"));
    }

    /**
     * Saves {@code seta} at the specified {@code filePath}.
     */
    private void saveSETA(ReadOnlySETA seta, String filePath) {
        try {
            new JsonSETAStorage(Paths.get(filePath))
                    .saveSETA(seta, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSETA_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSETA(new SETA(), null));
    }
}
