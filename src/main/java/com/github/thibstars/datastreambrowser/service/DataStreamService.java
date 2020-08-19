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
import java.util.Set;

/**
 * @author Thibault Helsmoortel
 */
public interface DataStreamService {

    Set<DataStream> parseDataStreamsFromFile(File file);

}
