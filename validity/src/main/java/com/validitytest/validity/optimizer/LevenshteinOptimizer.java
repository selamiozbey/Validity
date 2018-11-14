package com.validitytest.validity.optimizer;

import com.validitytest.validity.Constants;
import com.validitytest.validity.model.User;

import java.util.ArrayList;
import java.util.List;

public class LevenshteinOptimizer extends AbstractOptimizer implements IOptimizer {

    public List<List> getUserDataDuplication(List<User> list){
        List <List> listArr = new ArrayList<List>();

        for(int j=0; j<list.size(); j++){
            User user = list.get(j);

            for(int i =j+1; i< list.size();i++){
                boolean status = comparaFieldsWithLevenshtein(user, list.get(i), Constants.LEVENSHTEIN_SIMILARITY_FACTOR);
                if(status){
                    duplicatedHM.put(user.hashCode(),user);
                    duplicatedHM.put(list.get(i).hashCode(),list.get(i));
                }
            }
            if(!duplicatedHM.containsKey(user.hashCode()))
                noneDuplicatedHM.put(user.hashCode(),user);

        }
        listArr.add(new ArrayList<User> (getSortedList(duplicatedHM)));
        listArr.add(new ArrayList<User> (getSortedList(noneDuplicatedHM)));
        return listArr;
    }



}
