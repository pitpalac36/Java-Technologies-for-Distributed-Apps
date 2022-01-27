package tests.persistence.interfaces;

import tests.model.Test;

public interface ITestRepo {
    Test findOne(int id);
}
