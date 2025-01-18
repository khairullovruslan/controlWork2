module ru.itis.khairullovruslan.controlwork {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires com.fasterxml.jackson.databind;

    opens ru.itis.khairullovruslan.controlwork.controller to javafx.fxml;
    opens ru.itis.khairullovruslan.controlwork to javafx.fxml;
    exports ru.itis.khairullovruslan.controlwork.dto;
    exports ru.itis.khairullovruslan.controlwork;
}