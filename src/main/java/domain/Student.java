package domain;

import json.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private final List<Tuple<String, Integer>> examScores = new ArrayList<>();

    @SafeVarargs
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        examScores.addAll(Arrays.asList(exams));
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject student = super.toJsonObject();
        JsonObject[] examsObjs = new JsonObject[examScores.size()];
        for (int i = 0; i < examScores.size(); i++) {
            boolean passed = examScores.get(i).value >= 3;
            examsObjs[i] = new JsonObject(
                    new JsonPair("course", new JsonString(examScores.get(i).key)),
                    new JsonPair("mark", new JsonNumber(examScores.get(i).value)),
                    new JsonPair("passed", new JsonBoolean(passed))
            );
        }
        JsonArray examArray = new JsonArray(examsObjs);
        student.add(new JsonPair("exams", examArray));
        return student;
    }
}