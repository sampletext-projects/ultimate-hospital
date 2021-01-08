package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class Utils {

    private static final String PATH_TO_SCREENS = "../screens/";

    private static Stage primaryStage;

    private static Stack<Scene> scenes = new Stack<>();

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void alertAndWait(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static void setScene(Scene scene) {
        primaryStage.setWidth(0);
        primaryStage.setHeight(0);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setX(Screen.getPrimary().getBounds().getWidth() / 2 - scene.getWidth() / 2);
        primaryStage.setY(Screen.getPrimary().getBounds().getHeight() / 2 - scene.getHeight() / 2);
    }

    public static void loadScreen(String screenName) {
        try {
            Parent root = FXMLLoader.load(Utils.class.getResource(PATH_TO_SCREENS + screenName + ".fxml"));
            Scene scene = new Scene(root);
            scenes.push(primaryStage.getScene());
            setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void backScreen() {
        if (scenes.size() > 1) {
            Scene scene = scenes.pop();
            setScene(scene);
        }
    }
}
