package com.validitytest.validity.fileUtillity;

import com.validitytest.validity.model.User;

import java.util.List;

public interface IFileParser {
    List<User> getList(String filePath, String delimiter);
}
