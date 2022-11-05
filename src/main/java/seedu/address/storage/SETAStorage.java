package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySETA;
import seedu.address.model.SETA;

/**
 * Represents a storage for {@link SETA}.
 */
public interface SETAStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSETAFilePath();

    /**
     * Returns SETA data as a {@link ReadOnlySETA}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySETA> readSETA() throws DataConversionException, IOException;

    /**
     * @see #getSETAFilePath()
     */
    Optional<ReadOnlySETA> readSETA(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySETA} to the storage.
     * @param seta cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSETA(ReadOnlySETA seta) throws IOException;

    /**
     * @see #saveSETA(ReadOnlySETA)
     */
    void saveSETA(ReadOnlySETA seta, Path filePath) throws IOException;

}
