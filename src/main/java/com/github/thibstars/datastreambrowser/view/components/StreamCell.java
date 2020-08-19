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

package com.github.thibstars.datastreambrowser.view.components;

import com.github.thibstars.datastreambrowser.model.DataStream;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thibault Helsmoortel
 */
@Slf4j
@SuppressWarnings("java:S110")
public class StreamCell extends ListCell<DataStream> {

    private StreamCellController streamCellController;

    public StreamCell() {
        try {
            this.streamCellController = getLoader().getController();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    protected void updateItem(DataStream item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null && streamCellController != null) {
            streamCellController.setDataStream(item);
            setGraphic(streamCellController.getHBox());
        } else {
            setGraphic(new HBox());
        }
    }

    private FXMLLoader getLoader() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource( "stream-cell.fxml"));

        fxmlLoader.load();

        return fxmlLoader;
    }
}
