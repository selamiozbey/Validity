package com.validitytest.validity.fileUtillity;

import com.validitytest.validity.Constants;

public class ParseFactory {

    public static IFileParser getParser(String parserType) throws Exception {
        if (parserType.equals(Constants.CVS_USER_PARSER))
            return new CVSUserParser();
        else
            throw new Exception("Unsupported Parser");
    }
}
