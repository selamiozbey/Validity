package com.validitytest.validity.optimizer;

import com.validitytest.validity.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class NamePhoneNumberDuplicationOptimizer implements IOptimizer {
    public List<List> getUserDataDuplication(List<User> list) {
        List<User> duplicatedList = new ArrayList<>();
        List<User> noneDuplicatedList = new ArrayList<>();
        List<List> listArr = new ArrayList<List>();

        HashMap<String, User> userHM = new HashMap<>();

        for (User user : list) {
            StringBuilder sb = new StringBuilder();
            sb.append(user.getFirst_name());
            sb.append(user.getLast_name());
            sb.append(user.getPhone());
            if (userHM.containsKey(sb.toString())) {
                User userRemoved = userHM.remove(sb.toString());
                duplicatedList.add(userRemoved);
                duplicatedList.add(user);
            } else
                userHM.put(sb.toString(), user);
        }
        noneDuplicatedList = userHM.values().stream().collect(Collectors.toList());
        listArr.add(duplicatedList);
        listArr.add(noneDuplicatedList);

        return listArr;
    }
}
