package ru.task1.cvfinder.model;

import ru.task1.cvfinder.model.dao.ResumeDaoImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Created by bumur on 18.02.2018.
 */
public class Model {
    private static final String AGE_MAX = "age_max";
    private static final String AGE_MIN = "age_min";
    private static final String AVERAGE_SALARY = "average_salary";
    private static final String GEO_ID = "geo_id";
    private static final String SALARY = "salary";
    private static final String EK_GEO_ID = "994";

    private List<Resume> resumes;
    private ResumeDaoImpl resumeDao;

    public Model() {
        resumeDao = new ResumeDaoImpl();
    }

    public List<Resume> getResumes(String age_max, String age_min, String salary) throws IOException {
        String answer = HttpMethodUtils.getMethod(
                String.format("resumes/?age_max=%s&age_min=%s&average_salary=1&geo_id=994&salary=%s",
                        age_max, age_min, salary));
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(List.class, new JsonResumeConverter());
        Gson gson = builder.create();
        resumes = gson.fromJson(answer, List.class);
        return resumes;
    }

    public ResumeDaoImpl getResumeDao() {
        return resumeDao;
    }

}
