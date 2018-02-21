package ru.task1.cvfinder.model.dao;

import ru.task1.cvfinder.model.Resume;

import java.util.List;

/**
 * Created by bumur on 18.02.2018.
 */
public interface ResumeDao {

    void putResumesIntoDB(List<Resume> resumeList);
    void deleteAllResumesFromDB();
    List<Resume> getResumesFromDB();
}
