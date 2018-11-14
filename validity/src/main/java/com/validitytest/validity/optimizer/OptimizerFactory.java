package com.validitytest.validity.optimizer;

import com.validitytest.validity.Constants;

public class OptimizerFactory {

    public static IOptimizer getOptimizer(int parserType) throws Exception {
        if (parserType == (Constants.OPTIMIZER_TYPE.METAPHONE_LEVENSHTEIN_OPTIMIZER.getID()))
            return new Metaphone_Levenshtein_Optimizer();
        if (parserType == (Constants.OPTIMIZER_TYPE.NAME_PHONENUMBER_OPTIMIZER.getID()))
            return new NamePhoneNumberDuplicationOptimizer();
        if (parserType == (Constants.OPTIMIZER_TYPE.LEVENSHTEIN_OPTIMIZER.getID()))
            return new LevenshteinOptimizer();

        else
            throw new Exception("Unsupported Parser");
    }
}
