package ru.task1.cvfinder.model.threads;

import ru.task1.cvfinder.Controller;
import ru.task1.cvfinder.model.Model;
import javafx.application.Platform;

/**
 * Created by bumur on 18.02.2018.
 */
public class DeletingDromDBThread extends Thread{
    private Model model;
    private Controller controller;

    public DeletingDromDBThread(Controller controller) {
        model = new Model();
        this.controller = controller;
    }

    @Override
    public void run() {
        model.getResumeDao().deleteAllResumesFromDB();
        Platform.runLater(() -> {
            controller.getStartButton().setDisable(false);
            controller.getDeleteFromDBButton().setDisable(false);
            controller.getLoadFromDBButton().setDisable(false);
        });
    }
}
