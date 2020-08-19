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

package com.github.thibstars.datastreambrowser.controller;

import com.github.thibstars.datastreambrowser.model.DataStream;
import com.github.thibstars.datastreambrowser.service.DataStreamService;
import com.github.thibstars.datastreambrowser.service.DataStreamServiceImpl;
import com.github.thibstars.datastreambrowser.service.SystemService;
import com.github.thibstars.datastreambrowser.service.SystemServiceImpl;
import java.io.File;
import java.util.Set;

/**
 * @author Thibault Helsmoortel
 */
public class DataStreamBrowserController {

    private static DataStreamBrowserController instance;

    private final SystemService systemService;

    private final DataStreamService dataStreamService;

    private DataStreamBrowserController() {
        // Hide implicit default constructor
        this.systemService = new SystemServiceImpl();
        this.dataStreamService = new DataStreamServiceImpl();
    }

    public static DataStreamBrowserController getInstance() {
        if (instance == null) {
            instance = new DataStreamBrowserController();
        }

        return instance;
    }

    public boolean isCorrectSystem() {
        return systemService.isCorrectSystem();
    }

    public Set<DataStream> parseADSFromFile(File file) {
        return dataStreamService.parseDataStreamsFromFile(file);
    }
}
