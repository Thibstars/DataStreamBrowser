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
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * @author Thibault Helsmoortel
 */
public class StreamCellController {

    @FXML
    @Getter
    private HBox hBox;

    @FXML
    private Label streamLabel;

    private DataStream dataStream;

    @FXML
    private void openStream() {
        Stage stage = new Stage();
        stage.setTitle(dataStream.getName());
        stage.setScene(new Scene(new TextArea(dataStream.getContent())));
        stage.show();
    }

    public void setDataStream(DataStream dataStream) {
        streamLabel.setText(dataStream.getName());
        this.dataStream = dataStream;
    }
}
