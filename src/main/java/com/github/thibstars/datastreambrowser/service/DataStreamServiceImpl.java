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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thibault Helsmoortel
 */
@Slf4j
public class DataStreamServiceImpl implements DataStreamService {

    @Override
    public Set<DataStream> parseDataStreamsFromFile(File file) {
        Set<DataStream> dataStreams = new HashSet<>();

        String command = "powershell.exe Get-item -path " + file.getAbsolutePath() + " -stream *";

        try {
            Process powerShellProcess = Runtime.getRuntime().exec(command);

            powerShellProcess.getOutputStream().close();
            String line;
            log.debug("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                powerShellProcess.getInputStream()));
            StringBuilder out = new StringBuilder();
            while ((line = stdout.readLine()) != null) {
                out.append(line).append("\n");
                log.debug(line);
            }
            stdout.close();
            if (out.toString().contains("Stream")) {
                String[] lines = out.toString().split("\n");
                dataStreams = Arrays.stream(lines)
                    .filter(sentence -> sentence.contains("Stream"))
                    .map(sentence -> sentence.substring(sentence.indexOf(":") + 1).trim())
                    .map(stream -> {
                        try {
                            File dataStreamFile = new File(file.getAbsolutePath() + ":" + stream);
                            try (BufferedReader bf = new BufferedReader(new FileReader(dataStreamFile))) {
                                log.debug("Stream data:");
                                StringBuilder dataStreamContent = new StringBuilder();
                                String sentence;
                                while ((sentence = bf.readLine()) != null) {
                                    dataStreamContent.append(sentence).append("\n");
                                    log.debug(sentence);
                                }

                                DataStream dataStream = new DataStream();
                                dataStream.setName(stream);
                                dataStream.setContent(dataStreamContent.toString());

                                return dataStream;
                            }
                        } catch (IOException e) {
                            log.error(e.getMessage(), e);

                            return null;
                        }
                    })
                    .collect(Collectors.toSet());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return dataStreams;
    }
}
