/**
 * @author Thibault Helsmoortel
 */
module DataStreamBrowser {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.sun.jna;
    requires org.apache.commons.lang3;
    requires org.apache.logging.log4j;
    requires org.slf4j;
    requires static org.mapstruct.processor;
    requires static lombok;
    exports com.github.thibstars.datastreambrowser.model;
    exports com.github.thibstars.datastreambrowser.view;
    exports com.github.thibstars.datastreambrowser.view.components;
    opens com.github.thibstars.datastreambrowser.view;
    opens com.github.thibstars.datastreambrowser.view.components;
}