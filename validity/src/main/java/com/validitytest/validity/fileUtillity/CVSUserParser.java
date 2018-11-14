package com.validitytest.validity.fileUtillity;

import com.validitytest.validity.Constants;
import com.validitytest.validity.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CVSUserParser implements IFileParser {

    public List<User> getList(String filePath, String delimiter) {
        List<User> list = new ArrayList();
        String fileToParse = filePath;
        BufferedReader fileReader = null;

        final String DELIMITER = delimiter;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileToParse));
            int fieldNumber = fileReader.readLine().split(Constants.DELIMITER_COMMA).length;//read first line
            while ((line = fileReader.readLine()) != null) {
                StringBuilder item = new StringBuilder();
                List<String> lineArray = new ArrayList();
                boolean state = false;
                for (char c : line.toCharArray()) {
                    if (c == ',') {
                        if (state) {
                            item.append(c);
                        } else {
                            lineArray.add(item.toString());
                            item = new StringBuilder();
                        }
                    } else if (c == '"') {
                        state = !state;
                    } else
                        item.append(c);
                }
                lineArray.add(item.toString());

                //lineArray = Arrays.asList(line.split(Constants.DELIMITER_COMMA));
                list.add(createUser(lineArray));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    private User createUser(List<String> list) {
        User user = new User();
        //id,first_name,last_name,company,email,address1,address2,zip,city,state_long,state,phone

        user.setId(Integer.valueOf(list.get(0)));
        user.setFirst_name((list.get(1)));
        user.setLast_name((list.get(2)));
        user.setCompany((list.get(3)));
        user.setEmail((list.get(4)));
        user.setAddress1((list.get(5)));
        user.setAddress2((list.get(6)));
        user.setZip((list.get(7)));
        user.setCity((list.get(8)));
        user.setState_long((list.get(9)));
        user.setState((list.get(10)));
        user.setPhone((list.get(11)));
        return user;
    }

    private String nullCheck(String s) {
        if (s == null || s.isEmpty())
            return "";
        return s;
    }


}
