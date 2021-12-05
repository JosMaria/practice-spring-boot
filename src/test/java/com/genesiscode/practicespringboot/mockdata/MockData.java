package com.genesiscode.practicespringboot.mockdata;

import com.genesiscode.practicespringboot.domain.Student;
import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MockData {

    @SuppressWarnings("UnstableApiUsage")
    public static List<Student> getStudents() throws IOException {
        InputStream inputStream = Resources.getResource("students.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Student>>() {}.getType();
        return new Gson().fromJson(json, listType);
    }

}
