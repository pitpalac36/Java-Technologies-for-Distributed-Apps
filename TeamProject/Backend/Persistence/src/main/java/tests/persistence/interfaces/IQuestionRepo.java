package tests.persistence.interfaces;

import tests.model.Question;

import java.util.List;

public interface IQuestionRepo {
    List<Question> findAll();
}