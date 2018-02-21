package ru.task1.cvfinder.model.threads;

import ru.task1.cvfinder.Controller;
import ru.task1.cvfinder.model.Model;
import ru.task1.cvfinder.model.Resume;
import javafx.application.Platform;

import java.io.IOException;
import java.util.List;

/**
 * Created by bumur on 18.02.2018.
 */
public class StartThread extends Thread {
    private Model model;
    private Controller controller;
    private String maxAge, minAge, salary;

    public StartThread(Controller controller, String maxAge,
                       String minAge, String salary) {
        model = new Model();
        this.controller = controller;
        this.maxAge = maxAge;
        this.minAge = minAge;
        this.salary = salary;
    }

    @Override
    public void run() {
        try {
            List<Resume> resumeList = model.getResumes(maxAge, minAge, salary);
            model.getResumeDao().putResumesIntoDB(resumeList);
            for (Resume resume : resumeList) {
                String itemLine = String.format("%s%nOwner id: %s, Owner sex: %s%n" +
                                (resume.getAge() == null ? "" : "Owner age: " + resume.getAge() + "%n") +
                                (resume.getBirthday() == null ? "" : "Owner birthday: " + resume.getBirthday() + "%n") +
                                ("Owner experience: %s%nResume url: https://ekb.zarplata.ru%s"),
                        resume.getHeader(), resume.getOwner_id(),
                        resume.getSex(), resume.getExperience(), resume.getUrl());

                Platform.runLater(() -> controller.addItemListView(itemLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Platform.runLater(() -> {
                controller.getStartButton().setDisable(false);
                controller.getDeleteFromDBButton().setDisable(false);
                controller.getLoadFromDBButton().setDisable(false);
            });
        }
    }
}
