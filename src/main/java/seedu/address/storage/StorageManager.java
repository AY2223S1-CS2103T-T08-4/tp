package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySETA;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of SETA data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SETAStorage setaStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SETAStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SETAStorage setaStorage, UserPrefsStorage userPrefsStorage) {
        this.setaStorage = setaStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ SETA methods ==============================

    @Override
    public Path getSETAFilePath() {
        return setaStorage.getSETAFilePath();
    }

    @Override
    public Optional<ReadOnlySETA> readSETA() throws DataConversionException, IOException {
        return readSETA(setaStorage.getSETAFilePath());
    }

    @Override
    public Optional<ReadOnlySETA> readSETA(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return setaStorage.readSETA(filePath);
    }

    @Override
    public void saveSETA(ReadOnlySETA seta) throws IOException {
        saveSETA(seta, setaStorage.getSETAFilePath());
    }

    @Override
    public void saveSETA(ReadOnlySETA seta, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        setaStorage.saveSETA(seta, filePath);
    }


}
