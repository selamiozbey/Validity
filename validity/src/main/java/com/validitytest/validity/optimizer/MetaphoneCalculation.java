package com.validitytest.validity.optimizer;

public class MetaphoneCalculation {

    /*Original Metaphone codes use the 16 consonant symbols 0BFHJKLMNPRSTWXY. The '0' represents "th" (as an ASCII approximation of Θ), 'X' represents "sh" or "ch", and the others represent their usual English pronunciations. The vowels AEIOU are also used, but only at the beginning of the code.[2] This table summarizes most of the rules in the original implementation:

    Drop duplicate adjacent letters, except for C.
    If the word begins with 'KN', 'GN', 'PN', 'AE', 'WR', drop the first letter.
    Drop 'B' if after 'M' at the end of the word.
    'C' transforms to 'X' if followed by 'IA' or 'H' (unless in latter case, it is part of '-SCH-', in which case it transforms to 'K'). 'C' transforms to 'S' if followed by 'I', 'E', or 'Y'. Otherwise, 'C' transforms to 'K'.
    'D' transforms to 'J' if followed by 'GE', 'GY', or 'GI'. Otherwise, 'D' transforms to 'T'.
    Drop 'G' if followed by 'H' and 'H' is not at the end or before a vowel. Drop 'G' if followed by 'N' or 'NED' and is at the end.
    'G' transforms to 'J' if before 'I', 'E', or 'Y', and it is not in 'GG'. Otherwise, 'G' transforms to 'K'.
    Drop 'H' if after vowel and not before a vowel.
    'CK' transforms to 'K'.
    'PH' transforms to 'F'.
    'Q' transforms to 'K'.
    'S' transforms to 'X' if followed by 'H', 'IO', or 'IA'.
    'T' transforms to 'X' if followed by 'IA' or 'IO'. 'TH' transforms to '0'. Drop 'T' if followed by 'CH'.
    'V' transforms to 'F'.
    'WH' transforms to 'W' if at the beginning. Drop 'W' if not followed by a vowel.
    'X' transforms to 'S' if at the beginning. Otherwise, 'X' transforms to 'KS'.
    Drop 'Y' if not followed by a vowel.
    'Z' transforms to 'S'.
    Drop all vowels unless it is the beginning.
    It should be noted, however, that this table does not constitute a complete description of the original Metaphone algorithm, and the algorithm cannot be coded correctly from it. Original Metaphone contained many errors and was superseded by Double Metaphone, and in turn Double Metaphone and original Metaphone were superseded by Metaphone 3, which corrects thousands of miscodings that will be produced by the first two versions.

    To implement Metaphone without purchasing a (source code) copy of Metaphone 3, the best guide would be the reference implementation of Double Metaphone, which may be found here.

    */
private static final char[] DEFAULT_MAPPING = "vBKTvFKHvJKLMNvPKRSTvFW*YS".toCharArray();
/*



 //I got this code from
    //https://cs.fit.edu/~ryan/java/programs/soundex/Metaphone-java.html


 */



    private static char map(char c) {
        return DEFAULT_MAPPING[c - 'A'];
    }

    private static int CODE_LENGTH = 6;

    public static String getMetaphoneEncode(String string) {
        String word = string.toUpperCase();
        word = word.replaceAll("[^A-Z]", "");
        if (word.length() == 0) {
            return "";
        } else if (word.length() == 1) {
            return word;
        }
        word = word.replaceFirst("^KN", "N");
        word = word.replaceFirst("^GN", "N");

        word = word.replaceFirst("^WR", "R");
        word = word.replaceFirst("^AE", "E");
        word = word.replaceFirst("^PF", "F");
        word = word.replaceFirst("^WH", "W");
        word = word.replaceFirst("^X", "S");

        // Transform input string to all caps
        final char[] input = word.toCharArray();

        int code_index = 0;
        final char[] code = new char[CODE_LENGTH];

        char prev_c = '?';

        for (int i = 0; i < input.length && code_index < CODE_LENGTH; i++) {
            final char c = input[i];

            if (c == prev_c) {
                // Especial rule for double letters
                if (c == 'C') {
                    // We have "cc".  The first "c" has already been mapped
                    // to "K".
                    if (i < input.length - 1 && "EIY".indexOf(input[i + 1]) >= 0) {
                        // Do nothing and let it do to cc[eiy] -> KS
                    } else {
                        // This "cc" is just one sound
                        continue;
                    }
                } else {
                    // It is not "cc", so ignore the second letter
                    continue;
                }
            }
            switch (c) {

                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    // Keep a vowel only if it is the first letter
                    if (i == 0) code[code_index++] = c;
                    break;

                case 'F':
                case 'J':
                case 'L':
                case 'M':
                case 'N':
                case 'R':
                    code[code_index++] = c;
                    break;

                case 'Q':
                case 'V':
                case 'Z':
                    code[code_index++] = map(c);
                    break;

                // B -> B   only if NOT  MB$
                case 'B':
                    if (!(i == input.length - 1 && code_index > 0 && code[code_index - 1] == 'M'))
                        code[code_index++] = c;
                    break;

                case 'C':
                    if (i < input.length - 2 && input[i + 1] == 'I' && input[i + 2] == 'A') code[code_index++] = 'X';
                    else if (i < input.length - 1 && input[i + 1] == 'H' && i > 0 && input[i - 1] != 'S')
                        code[code_index++] = 'X';
                    else if (i < input.length - 1 && "EIY".indexOf(input[i + 1]) >= 0) code[code_index++] = 'S';
                    else code[code_index++] = 'K';
                    break;

                case 'D':
                    if (i < input.length - 2 && input[i + 1] == 'G' && "EIY".indexOf(input[i + 2]) >= 0)
                        code[code_index++] = 'J';
                    else code[code_index++] = 'T';
                    break;

                case 'G':
                    if (i < input.length - 1 && input[i + 1] == 'N') ;  // GN -> N  [GNED -> NED]
                    else if (i > 0 && input[i - 1] == 'D' && i < input.length - 1 && "EIY".indexOf(input[i + 1]) >= 0)
                        ; // DG[IEY] -> D[IEY]
                    else if (i < input.length - 1 && input[i + 1] == 'H' && (i + 2 == input.length || "AEIOU".indexOf(input[i + 2]) < 0))
                        ;
                    else if (i < input.length - 1 && "EIY".indexOf(input[i + 1]) >= 0) code[code_index++] = 'J';
                    else code[code_index++] = map(c);
                    break;

                case 'H':
                    if (i > 0 && "AEIOUCGPST".indexOf(input[i - 1]) >= 0) ; // vH -> v
                    else if (i < input.length - 1 && "AEIOU".indexOf(input[i + 1]) < 0) ; // Hc -> c
                    else code[code_index++] = c;
                    break;

                case 'K':
                    if (i > 0 && input[i - 1] == 'C') ; // CK -> K
                    else code[code_index++] = map(c);
                    break;

                case 'P':
                    if (i < input.length - 1 && input[i + 1] == 'H') code[code_index++] = 'F';
                    else code[code_index++] = map(c);
                    break;

                case 'S':
                    if (i < input.length - 2 && input[i + 1] == 'I' && (input[i + 2] == 'A' || input[i + 2] == 'O'))
                        code[code_index++] = 'X';
                    else if (i < input.length - 1 && input[i + 1] == 'H') code[code_index++] = 'X';
                    else code[code_index++] = 'S';
                    break;

                case 'T':
                    // -TI[AO]- -> -XI[AO]-
                    // -TCH- -> -CH-
                    // -TH- -> -0-
                    // -T- -> -T-
                    if (i < input.length - 2 && input[i + 1] == 'I' && (input[i + 2] == 'A' || input[i + 2] == 'O'))
                        code[code_index++] = 'X';
                    else if (i < input.length - 1 && input[i + 1] == 'H') code[code_index++] = '0';
                    else if (i < input.length - 2 && input[i + 1] == 'C' && input[i + 2] == 'H') ; // drop letter
                    else code[code_index++] = 'T';
                    break;

                case 'W':
                case 'Y':
                    // -Wv- -> -Wv-;  -Wc- -> -c-
                    // -Yv- -> -Yv-;  -Yc- -> -c-
                    if (i < input.length - 1 && "AEIOU".indexOf(input[i + 1]) >= 0) code[code_index++] = map(c);
                    break;

                case 'X':
                    // -X- -> -KS-
                    code[code_index++] = 'K';
                    if (code_index < code.length) code[code_index++] = 'S';
                    break;

                default:
                    assert (false);
            }
            prev_c = c;
        }
        return new String(code, 0, code_index);
    }

    public static void main(String[] args) {
        final String s = "maximillian";
        System.out.format("%-6s    %s%n", getMetaphoneEncode(s), s);

    }
}