package tests.model;

import java.util.Objects;

public class Test {
    private int ID;
    private Question q1;
    private Question q2;
    private Question q3;
    private Question q4;
    private Question q5;

    public Test() {
    }

    public Test(int ID, Question q1, Question q2, Question q3, Question q4, Question q5) {
        this.ID = ID;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
    }

    public Test(Question q1, Question q2, Question q3, Question q4, Question q5) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Question getQ1() {
        return q1;
    }

    public void setQ1(Question q1) {
        this.q1 = q1;
    }

    public Question getQ2() {
        return q2;
    }

    public void setQ2(Question q2) {
        this.q2 = q2;
    }

    public Question getQ3() {
        return q3;
    }

    public void setQ3(Question q3) {
        this.q3 = q3;
    }

    public Question getQ4() {
        return q4;
    }

    public void setQ4(Question q4) {
        this.q4 = q4;
    }

    public Question getQ5() {
        return q5;
    }

    public void setQ5(Question q5) {
        this.q5 = q5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return ID == test.ID && q1 == test.q1 && q2 == test.q2 && q3 == test.q3 && q4 == test.q4 && q5 == test.q5;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, q1, q2, q3, q4, q5);
    }

    @Override
    public String toString() {
        return "Test{" +
                "ID=" + ID +
                ", q1=" + q1 +
                ", q2=" + q2 +
                ", q3=" + q3 +
                ", q4=" + q4 +
                ", q5=" + q5 +
                '}';
    }
}
