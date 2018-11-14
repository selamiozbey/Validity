package com.validitytest.validity.service;


import com.validitytest.validity.Constants;
import com.validitytest.validity.ValidityException;
import com.validitytest.validity.fileUtillity.IFileParser;
import com.validitytest.validity.fileUtillity.ParseFactory;
import com.validitytest.validity.model.User;
import com.validitytest.validity.optimizer.IOptimizer;
import com.validitytest.validity.optimizer.Metaphone_Levenshtein_Optimizer;
import com.validitytest.validity.optimizer.OptimizerFactory;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService {
    String normalCVSFilePath = "normal.csv";
    String advanceCVSFilePath = "../java/com/validitytest/validity/sampleFiles/advance.csv";

    public List getDuplicateUserList() throws ValidityException {
        return (List) getUserList().get(0);
    }

    public List getNoneDuplicateUserList() throws ValidityException {
        return (List) getUserList().get(1);
    }

    private List getUserList() throws ValidityException {
        List<User> list;
        List<List> userListArray;
        try {
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource("advanced.csv").toURI());
            IFileParser parser = ParseFactory.getParser(Constants.CVS_USER_PARSER);
            list = parser.getList(path.toString(), Constants.DELIMITER_COMMA);
        } catch (URISyntaxException e) {
            throw new ValidityException(Constants.FILEPATH_NOT_FOUND_ERROR);
        } catch (Exception e) {
            throw new ValidityException(Constants.PARSER_NOT_FOUND_ERROR);
        }

        try {
            IOptimizer optimizer= OptimizerFactory.getOptimizer(Constants.OPTIMIZER_TYPE.LEVENSHTEIN_OPTIMIZER.getID());
//            IdDuplicationOptimizer optimizer = new IdDuplicationOptimizer();
            userListArray = optimizer.getUserDataDuplication(list);


        } catch (Exception e) {
            throw new ValidityException(Constants.OPTIMIZER_NOT_FOUND_ERROR);
        }
        return userListArray;
    }

    public static void main(String[] args) throws ValidityException {
        List<User> list;
        List<List> userListArray;
        try {
            String path = "D:\\myWorks\\MY_SPRING_works\\validity\\src\\main\\resources\\normal.csv";

            IFileParser parser = ParseFactory.getParser(Constants.CVS_USER_PARSER);
            list = parser.getList(path, Constants.DELIMITER_COMMA);
        } catch (URISyntaxException e) {
            throw new ValidityException(Constants.FILEPATH_NOT_FOUND_ERROR);
        } catch (Exception e) {
            throw new ValidityException(Constants.PARSER_NOT_FOUND_ERROR);
        }

        try {
            Metaphone_Levenshtein_Optimizer optimizer = new Metaphone_Levenshtein_Optimizer();
            userListArray = optimizer.getUserDataDuplication(list);


        } catch (Exception e) {
            throw new ValidityException(Constants.OPTIMIZER_NOT_FOUND_ERROR);
        }
    }
}
