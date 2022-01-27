package tests.model;

import java.io.Serializable;

public class AnswerDTO implements Serializable {
    private int qID;
    private boolean answer;

    public AnswerDTO() {
    }

    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "qID=" + qID +
                ", answer=" + answer +
                '}';
    }
}
