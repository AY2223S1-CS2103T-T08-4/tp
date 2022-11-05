package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlySETA;

/**
 * A class to access SETA data stored as a json file on the hard disk.
 */
public class JsonSETAStorage implements SETAStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSETAStorage.class);

    private Path filePath;

    public JsonSETAStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSETAFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySETA> readSETA() throws DataConversionException {
        return readSETA(filePath);
    }

    /**
     * Similar to {@link #readSETA()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySETA> readSETA(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSETA> jsonSETA = JsonUtil.readJsonFile(
                filePath, JsonSerializableSETA.class);
        if (!jsonSETA.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSETA.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSETA(ReadOnlySETA seta) throws IOException {
        saveSETA(seta, filePath);
    }

    /**
     * Similar to {@link #saveSETA(ReadOnlySETA)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSETA(ReadOnlySETA seta, Path filePath) throws IOException {
        requireNonNull(seta);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSETA(seta), filePath);
    }

}
