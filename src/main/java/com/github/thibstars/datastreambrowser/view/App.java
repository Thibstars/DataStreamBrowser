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
import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene;
        if (DataStreamBrowserController.getInstance().isCorrectSystem()) {
            scene = new Scene(loadFXML());
            scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getClassLoader().getResource("styles.css")).toExternalForm());
        } else {
            scene = new Scene(new StackPane(new Label("Your system does not meet the requirements to browse alternative data streams.")));
        }

        stage.setScene(scene);
        stage.setTitle("DataStreamBrowser");
        stage.show();

    }

    private Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("primary.fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
