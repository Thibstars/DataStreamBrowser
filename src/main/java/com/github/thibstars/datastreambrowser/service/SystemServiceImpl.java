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

import com.github.thibstars.datastreambrowser.model.Kernel32;
import com.sun.jna.ptr.IntByReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

/**
 * @author Thibault Helsmoortel
 */
@Slf4j
public class SystemServiceImpl implements SystemService {

    private static final String TARGET_FILE_SYSTEM_FORMAT = "NTFS";

    @Override
    public boolean isCorrectSystem() {
        return SystemUtils.IS_OS_WINDOWS && isNTFS();
    }

    private boolean isNTFS() {
        String fileSystemName = getFileSystemFormat();

        return StringUtils.equalsIgnoreCase(fileSystemName, TARGET_FILE_SYSTEM_FORMAT);
    }

    private static String getFileSystemFormat() {
        char[] lpVolumeNameBuffer = new char[256];
        int nVolumeNameSize = 256;
        IntByReference lpVolumeSerialNumber = new IntByReference();
        IntByReference lpMaximumComponentLength = new IntByReference();
        IntByReference lpFileSystemFlags = new IntByReference();

        char[] lpFileSystemNameBuffer = new char[256];
        int nFileSystemNameSize = 256;

        lpVolumeSerialNumber.setValue(0);
        lpMaximumComponentLength.setValue(256);
        lpFileSystemFlags.setValue(0);

        Kernel32.INSTANCE.GetVolumeInformation(
            "C:\\",
            lpVolumeNameBuffer,
            nVolumeNameSize,
            lpVolumeSerialNumber,
            lpMaximumComponentLength,
            lpFileSystemFlags,
            lpFileSystemNameBuffer,
            nFileSystemNameSize);

        log.debug("Last error: {}\n\n", Kernel32.INSTANCE.GetLastError());

        String fs = new String(lpFileSystemNameBuffer);
        log.debug(fs.trim());

        return fs.trim();
    }
}
