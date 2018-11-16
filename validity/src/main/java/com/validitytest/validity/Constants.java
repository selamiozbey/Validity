package com.validitytest.validity;

public class Constants {

    public static final String DELIMITER_COMMA = ",";
    public static final String CVS_USER_PARSER = "cvs_user_parser";

    public enum OPTIMIZER_TYPE {
        METAPHONE_LEVENSHTEIN_OPTIMIZER(0),
        NAME_PHONENUMBER_OPTIMIZER(1),
        LEVENSHTEIN_OPTIMIZER(2);
        private int id;
        OPTIMIZER_TYPE(int id) {
            this.id = id;
        }
        public int getID() {
            return id;
        }

    }


    public static  final  String OPTIMIZER_NOT_FOUND_ERROR = "Optimizer not found";
    public static  final  String PARSER_NOT_FOUND_ERROR = "Parser not found";
    public static  final  String FILEPATH_NOT_FOUND_ERROR = "File path not found";

    public static final  int LEVENSHTEIN_SIMILARITY_FACTOR = 3;
    public static final  int METAPHONE_SIMILARITY_FACTOR = 0;
    


}
