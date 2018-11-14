package com.validitytest.validity.optimizer;

import com.validitytest.validity.model.User;

import java.util.List;

public interface IOptimizer {
    public List<List> getUserDataDuplication(List<User> list);
}
