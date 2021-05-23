package gui;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: gui
 * Project Name: BankingApplication
 * Date: 23-05-2021
 */

public class MyPreloader extends Preloader {
    private Stage stage;
    private Scene scene;

    public MyPreloader(){}

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    /*
    This method handles the each and every notification coming from application, here from FirstWindow.java class.
     */
    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification) {

    }

    /*
    This method is used to handle the stage change in application, here in FirstWindow.java class.
    Ex. Changing state from init to start.
     */
    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {

    }
}
