package com.validitytest.validity.optimizer;

import com.validitytest.validity.Constants;
import com.validitytest.validity.model.User;

import java.util.*;

public abstract class AbstractOptimizer{
/*    protected List<User> duplicatedList = new ArrayList<>();
    protected List<User> noneDuplicatedList = new ArrayList<>();
    protected List <List> listArr = new ArrayList<List>();*/

//    protected TreeSet<User> duplicatedTS = new TreeSet<>();
//    protected TreeSet<User> noneDuplicatedTS = new TreeSet<>();

    protected HashMap<Integer,User> duplicatedHM = new HashMap<>();
    protected HashMap<Integer,User> noneDuplicatedHM = new HashMap<>();

    protected boolean isSimilar(int distance, int similarityFactor) {
        return distance <= similarityFactor ? true : false;
    }
    protected boolean comparaFieldsWithLevenshtein(User user, User tmpUser, int similarityFactor) {
        int totalCost=0;
        int counter=0;

        if(!user.getFirst_name().isEmpty() && !tmpUser.getFirst_name().isEmpty()){
            totalCost += LevenshteinCalculation.getLevenshteinDistance(user.getFirst_name(),tmpUser.getFirst_name());
            counter +=1;
        }
        if(!user.getLast_name().isEmpty() && !tmpUser.getLast_name().isEmpty()){
            totalCost += LevenshteinCalculation.getLevenshteinDistance(user.getLast_name(),tmpUser.getLast_name());
            counter +=1;
        }
        if(!user.getPhone().isEmpty() && !tmpUser.getPhone().isEmpty()){
            totalCost += LevenshteinCalculation.getLevenshteinDistance(user.getPhone(),tmpUser.getPhone());
            counter +=1;
        }
        if(!user.getEmail().isEmpty() && !tmpUser.getEmail().isEmpty()){
            totalCost += LevenshteinCalculation.getLevenshteinDistance(user.getEmail(),tmpUser.getEmail());
            counter +=1;
        }

        boolean status = isSimilar(totalCost / counter, similarityFactor);
        return status;
    }


    protected List getSortedList(HashMap hashMap){
        List<User> lst = new ArrayList<User>(hashMap.values());
        Collections.sort(lst, new Comparator<User>() {
            public int compare(User a, User b) {
                return String.valueOf(a.getId() + a.getFirst_name()).compareTo(String.valueOf(b.getId() + b.getFirst_name()));
            }
        });
        return lst;
    }

}
