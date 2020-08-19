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

package com.github.thibstars.datastreambrowser.model;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Thibault Helsmoortel
 */
public interface Kernel32 extends StdCallLibrary {

    final static Map<String, Object> WIN32API_OPTIONS = new HashMap<>() {
        {
            put(Library.OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
            put(Library.OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
        }
    };

    public Kernel32 INSTANCE = Native.load("Kernel32", Kernel32.class, WIN32API_OPTIONS);

    /*
    BOOL WINAPI GetVolumeInformation(
            __in_opt   LPCTSTR lpRootPathName,
            __out      LPTSTR lpVolumeNameBuffer,
            __in       DWORD nVolumeNameSize,
            __out_opt  LPDWORD lpVolumeSerialNumber,
            __out_opt  LPDWORD lpMaximumComponentLength,
            __out_opt  LPDWORD lpFileSystemFlags,
            __out      LPTSTR lpFileSystemNameBuffer,
            __in       DWORD nFileSystemNameSize
            );
     */
    public boolean GetVolumeInformation(
        String lpRootPathName,
        char[] lpVolumeNameBuffer,
        int nVolumeNameSize,
        IntByReference lpVolumeSerialNumber,
        IntByReference lpMaximumComponentLength,
        IntByReference lpFileSystemFlags,
        char[] lpFileSystemNameBuffer,
        int nFileSystemNameSize
    );

    public int GetLastError();
}
