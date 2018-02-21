package ru.task1.cvfinder;

import ru.task1.cvfinder.model.threads.DeletingDromDBThread;
import ru.task1.cvfinder.model.threads.LoadingFromDBThread;
import ru.task1.cvfinder.model.threads.StartThread;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Created by bumur on 18.02.2018.
 */
public class Controller {
    @FXML
    private TextField minAgeTextField;
    @FXML
    private TextField maxAgeTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private Button startButton;
    @FXML
    private Button loadFromDBButton;
    @FXML
    private Button deleteFromDBButton;
    @FXML
    private ListView<String> resumeListView;

    private Main main;
    @FXML
    public void onStartButtonListener() {
        if (minAgeTextField.getText().isEmpty() || maxAgeTextField.getText().isEmpty()
                || salaryTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("One of the text fields is empty");
            alert.show();
            return;
        }
        startButton.setDisable(true);
        loadFromDBButton.setDisable(true);
        deleteFromDBButton.setDisable(true);
        resumeListView.getItems().clear();
        new StartThread(this, maxAgeTextField.getText(),
                minAgeTextField.getText(), salaryTextField.getText()).start();

    }
    @FXML
    public void onDeleteFromDBButtonListener() {
        startButton.setDisable(true);
        loadFromDBButton.setDisable(true);
        deleteFromDBButton.setDisable(true);
        resumeListView.getItems().clear();
        new DeletingDromDBThread(this).start();
    }
    @FXML
    public void onLoadFromDBButtonListener() {
        startButton.setDisable(true);
        loadFromDBButton.setDisable(true);
        deleteFromDBButton.setDisable(true);
        resumeListView.getItems().clear();

        new LoadingFromDBThread(this).start();
    }
    @FXML
    public void onItemListViewListener() {
        String[] splittedItem = resumeListView.getSelectionModel().getSelectedItem().split("\n");
        String url = splittedItem[splittedItem.length - 1].substring(12);
        main.getHostServices().showDocument(url);
    }
    public void addItemListView(String item) {
        resumeListView.getItems().add(item);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getLoadFromDBButton() {
        return loadFromDBButton;
    }

    public Button getDeleteFromDBButton() {
        return deleteFromDBButton;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}

