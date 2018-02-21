package ru.task1.cvfinder.model;

import com.google.gson.*;
import org.apache.commons.lang.StringEscapeUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bumur on 18.02.2018.
 */
public class JsonResumeConverter implements JsonDeserializer<List>{
    @Override
    public List deserialize(JsonElement jsonElement, Type type,
                                    JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonArray resumesArray = jsonElement.getAsJsonObject().get("resumes").getAsJsonArray();
        List<Resume> resumesArrayList = new ArrayList<Resume>();
        Resume resume;
        int id;
        String owner_id;
        String age;
        String header;
        String experience;
        String url;
        String birthday;
        String sex;
        JsonElement element;
        for (JsonElement resumeElement : resumesArray) {
            id = Integer.parseInt(StringEscapeUtils.unescapeJava(resumeElement.getAsJsonObject().get("id").getAsString()));
            owner_id = StringEscapeUtils.unescapeJava(resumeElement.getAsJsonObject().get("owner_id").getAsString());
            element = resumeElement.getAsJsonObject().get("age");
            age = element == null ? "" : element.getAsString();
            header = StringEscapeUtils.unescapeJava(resumeElement.getAsJsonObject().get("header").getAsString());
            experience = StringEscapeUtils.unescapeJava(resumeElement.getAsJsonObject().get("experience").getAsString());
            url = StringEscapeUtils.unescapeJava(resumeElement.getAsJsonObject().get("url").getAsString());
            element = resumeElement.getAsJsonObject().get("birthday");
            birthday = element == null ? "" : element.getAsString();
            sex = StringEscapeUtils.unescapeJava(resumeElement.getAsJsonObject().get("sex").getAsString());
            resume = new Resume(id, owner_id, age, header, experience, url, birthday, sex);
            resumesArrayList.add(resume);
        }

        return resumesArrayList;
    }
}
