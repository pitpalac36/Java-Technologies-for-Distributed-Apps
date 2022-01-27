package tests.model;

import java.io.Serializable;

public class TestDTO implements Serializable {
    private int testID;
    private Question[] questions;

    public TestDTO() {
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
