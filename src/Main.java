import javafx.application.Application;
import javafx.stage.Stage;
import utils.Utils;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
        Utils.setPrimaryStage(primaryStage);
        Utils.loadScreen("EnterOrRegisterScreen");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
