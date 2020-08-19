/*
 * Copyright (c) 2020 Thibault Helsmoortel.
 *
 * This file is part of DataStreamBrowser.
 *
 * DataStreamBrowser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DataStreamBrowser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DataStreamBrowser.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.thibstars.datastreambrowser.service;

import com.github.thibstars.datastreambrowser.model.DataStream;
import java.io.File;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Thibault Helsmoortel
 */
class DataStreamServiceImplTest {

    private File multipleStreamsFile;

    private File singleStreamFile;

    private DataStreamService dataStreamService;

    @BeforeEach
    void setUp() {
        this.multipleStreamsFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("with-ads.txt")).getFile());
        this.singleStreamFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("without-ads.txt")).getFile());

        this.dataStreamService = new DataStreamServiceImpl();
    }

    @Test
    void shouldParseMultipleDataStreamsFromFile() {
        checkDataStreamResult(multipleStreamsFile, 2);
    }

    @Test
    void shouldParseSingleDataStreamFromFile() {
        checkDataStreamResult(singleStreamFile, 1);
    }

    private void checkDataStreamResult(File file, int expectedStreams) {
        Set<DataStream> dataStreams = dataStreamService.parseDataStreamsFromFile(file);

        Assertions.assertNotNull(dataStreams, "Result must not be null.");
        Assertions.assertFalse(dataStreams.isEmpty(), "Result must not be empty.");
        Assertions.assertEquals(expectedStreams, dataStreams.size(), "Result must be correct.");
    }
}