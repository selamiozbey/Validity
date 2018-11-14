package com.validitytest.validity.optimizer;

import com.validitytest.validity.Constants;
import com.validitytest.validity.model.User;

import java.util.*;

public class Metaphone_Levenshtein_Optimizer extends AbstractOptimizer implements IOptimizer {

    public List<List> getUserDataDuplication(List<User> list){
        List <List> listArr = new ArrayList<List>();
        String ss;

        for(int j=0; j<list.size(); j++){
            User user = list.get(j).clone();
            user.setFirst_name(MetaphoneCalculation.getMetaphoneEncode(user.getFirst_name()));
            user.setLast_name(MetaphoneCalculation.getMetaphoneEncode(user.getLast_name()));
            user.setPhone(MetaphoneCalculation.getMetaphoneEncode(user.getPhone()));
            user.setEmail(MetaphoneCalculation.getMetaphoneEncode(user.getEmail()));

            for(int i =j+1; i< list.size();i++){
                User tmpUser = list.get(i).clone();
                User mUser = new User();
                mUser.setFirst_name(MetaphoneCalculation.getMetaphoneEncode(tmpUser.getFirst_name()));
                mUser.setLast_name(MetaphoneCalculation.getMetaphoneEncode(tmpUser.getLast_name()));
                mUser.setPhone(MetaphoneCalculation.getMetaphoneEncode(tmpUser.getPhone()));
                mUser.setEmail(MetaphoneCalculation.getMetaphoneEncode(tmpUser.getEmail()));

                boolean status = comparaFieldsWithLevenshtein(user, mUser, Constants.METAPHONE_SIMILARITY_FACTOR);
                if(status){
                    duplicatedHM.put(list.get(j).hashCode(),list.get(j));
                    duplicatedHM.put(list.get(i).hashCode(),list.get(i));
                }
            }
            if(!duplicatedHM.containsKey(list.get(j).hashCode())) {
                noneDuplicatedHM.put(list.get(j).hashCode(),list.get(j));
            }
        }

        listArr.add(new ArrayList<User> (getSortedList(duplicatedHM)));
        listArr.add(new ArrayList<User> (getSortedList(noneDuplicatedHM)));
        return listArr;
    }


}
