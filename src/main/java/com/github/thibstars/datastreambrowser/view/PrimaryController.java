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

package com.github.thibstars.datastreambrowser.view;

import com.github.thibstars.datastreambrowser.controller.DataStreamBrowserController;
import com.github.thibstars.datastreambrowser.model.DataStream;
import com.github.thibstars.datastreambrowser.view.components.StreamCell;
import java.io.File;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

/**
 * @author Thibault Helsmoortel
 */
public class PrimaryController {

    @FXML
    private ListView<File> fileListView;

    @FXML
    private ListView<DataStream> streamListView;

    @FXML
    private Button addFileButton;

    @FXML
    private Label streamViewLabel;

    @FXML
    public void initialize() {
        fileListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            DataStreamBrowserController controller = DataStreamBrowserController.getInstance();
            Set<DataStream> dataStreams = controller.parseADSFromFile(newValue);
            ObservableList<DataStream> items = streamListView.getItems();
            items.clear();
            items.addAll(dataStreams);
        });

        streamListView.setCellFactory(listView -> new StreamCell());

        streamViewLabel.prefHeightProperty().bind(addFileButton.heightProperty());
    }

    @FXML
    public void addFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(fileListView.getParent().getScene().getWindow());

        ObservableList<File> items = fileListView.getItems();
        if (file != null && !items.contains(file)) {
            items.add(file);
            fileListView.getSelectionModel().select(file);
        }
    }
}
