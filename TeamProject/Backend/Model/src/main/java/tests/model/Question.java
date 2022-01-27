package tests.model;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {
    private int ID;
    private String description;
    private boolean answer;

    public Question() {}

    public Question(int ID, String description, boolean answer) {
        this.ID = ID;
        this.description = description;
        this.answer = answer;
    }

    public Question(String description, boolean answer) {
        this.description = description;
        this.answer = answer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return ID == question.ID && answer == question.answer && Objects.equals(description, question.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, description, answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", answer=" + answer +
                '}';
    }
}
