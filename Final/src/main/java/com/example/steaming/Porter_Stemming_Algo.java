package com.example.steaming;
public class Porter_Stemming_Algo {

    Decision decision = new Decision();

    public int calculate_m_value(String word) {
        int m = 0;
        for (int i = 0; i < word.length() - 1; i++) {
            if (decision.is_vowel(word.charAt(i)) && !decision.is_vowel(word.charAt(i + 1))) {
                m++;
                i++;
            }
        }
        return m;
    }

    public String check_and_replace(String word, String end_with, String replace_with, int m)
    {
        String output=word;
        if(word.endsWith(end_with))
        {
            String t_word = word.substring(0,word.length()-end_with.length());
            int t_m = calculate_m_value(t_word);
            if(t_m>m)
            {
                output = t_word+replace_with;
            }
        }
        return output;
    }
    public String step1a(String word) {

        if (word.endsWith("sses")) {
            word = check_and_replace(word,"sses","ss",-1);
        } else if (word.endsWith("ies")) {
            word = check_and_replace(word,"ies","i",-1);
        } else if (word.endsWith("ss")) {
            word = check_and_replace(word,"ss","ss",-1);
        } else if (word.endsWith("s")) {
            word = check_and_replace(word,"s","",-1);
        }

        return word;

    }

    public String step1b(String word){
        String output=word;
        boolean is_step1b_additional_needed = false;

        if (word.endsWith("eed")){
            output =  check_and_replace(word,"eed","ee",0);
        }
        else if (word.endsWith("ed") && decision.is_star_v_star(word)){
            is_step1b_additional_needed = true;
            output =  check_and_replace(word,"ed","",-1);
        }
        else if (word.endsWith("ing") && decision.is_star_v_star(word)){
            is_step1b_additional_needed = true;
            output =  check_and_replace(word,"ing","",-1);
        }

        if(is_step1b_additional_needed){
            if (word.endsWith("at")){
                output =  check_and_replace(word,"at","ate",-1);
            }
            else if (word.endsWith("bl")){
                output =  check_and_replace(word,"bl","ble",-1);
            }
            else if (word.endsWith("iz")){
                output =  check_and_replace(word,"iz","ize",-1);
            }
            else if (word.endsWith("o") && calculate_m_value(word)==1){
                output =  word.substring(0, word.length() - 1) + "e";
            }
        }

        return  output;
    }

    public String step1c(String word) {
        String output = word;
        if (word.endsWith("y") && decision.is_star_v_star(word))
            output = word.substring(0, word.length() - 1) + "i";
        return output;
    }

    public String step2(String word){
        String output = word;
            if (word.endsWith("ational"))
                output = check_and_replace(word,"ational","ate",0);
            else if (word.endsWith("tional"))
                output = check_and_replace(word,"tional","tion",0);
            else if (word.endsWith("enci"))
                output = check_and_replace(word,"enci","ence",0);
            else if (word.endsWith("anci"))
                output = check_and_replace(word,"anci","ance",0);
            else if (word.endsWith("izer"))
                output = check_and_replace(word,"izer","ize",0);
            else if (word.endsWith("abli"))
                output =check_and_replace(word,"abli","able",0);
            else if (word.endsWith("alli"))
                output = check_and_replace(word,"alli","al",0);
            else if (word.endsWith("entli"))
                output = check_and_replace(word,"entli","ent",0);
            else if (word.endsWith("eli"))
                output = check_and_replace(word,"eli","e",0);
            else if (word.endsWith("ousli"))
                output = check_and_replace(word,"ousli","ous",0);
            else if (word.endsWith("ization"))
                output = check_and_replace(word,"ization","ize",0);
            else if (word.endsWith("ation"))
                output = check_and_replace(word,"ation","ate",0);
            else if (word.endsWith("ator"))
                output = check_and_replace(word,"ator","ate",0);
            else if (word.endsWith("alism"))
                output = check_and_replace(word,"alism","al",0);
            else if (word.endsWith("iveness"))
                output = check_and_replace(word,"iveness","ive",0);
            else if (word.endsWith("fulness"))
                output = check_and_replace(word,"fulness","ful",0);
            else if (word.endsWith("ousness"))
                output = check_and_replace(word,"ousness","ous",0);
            else if (word.endsWith("aliti"))
                output = check_and_replace(word,"aliti","al",0);
            else if (word.endsWith("iviti"))
                output = check_and_replace(word,"iviti","ive",0);
            else if (word.endsWith("biliti"))
                output = check_and_replace(word,"biliti","ble",0);

        return output;
    }

    public String step3(String word){
        String output = word;

            if (word.endsWith("icate"))
                output = check_and_replace(word,"icate","ic",0);
            else if (word.endsWith("ative"))
                output = check_and_replace(word,"ative","",0);
            else if (word.endsWith("alize"))
                output = check_and_replace(word,"alize","al",0);
            else if (word.endsWith("iciti"))
                output = check_and_replace(word,"iciti","ic",0);
            else if (word.endsWith("ical"))
                output = check_and_replace(word,"ical","ic",0);
            else if (word.endsWith("ful"))
                output = check_and_replace(word,"ful","",0);
            else if (word.endsWith("ness"))
                output = check_and_replace(word,"ness","",0);

        return output;
    }

    public String step4(String word){
        String output = word;
            if (word.endsWith("al"))
                output = check_and_replace(word,"al","",1);
            else if (word.endsWith("ance"))
                output = check_and_replace(word,"ance","",1);
            else if (word.endsWith("ence"))
                output = check_and_replace(word,"ence","",1);
            else if (word.endsWith("ic"))
                output = check_and_replace(word,"ic","",1);
            else if (word.endsWith("able"))
                output = check_and_replace(word,"able","",1);
            else if (word.endsWith("ant"))
                output = check_and_replace(word,"ant","",1);
            else if (word.endsWith("ible"))
                output = check_and_replace(word,"ible","",1);
            else if (word.endsWith("ement"))
                output = check_and_replace(word,"ement","",1);
            else if (word.endsWith("ment"))
                output = check_and_replace(word,"ment","",1);
            else if (word.endsWith("ent"))
                output = check_and_replace(word,"ent","",1);
            else if (word.endsWith("sion"))
                output = check_and_replace(word,"ion","",1);
            else if (word.endsWith("tion"))
                output = check_and_replace(word,"ion","",1);
            else if (word.endsWith("ou"))
                output = check_and_replace(word,"ou","",1);
            else if (word.endsWith("ism"))
                output = check_and_replace(word,"ism","",1);
            else if (word.endsWith("ate"))
                output = check_and_replace(word,"ate","",1);
            else if (word.endsWith("iti"))
                output = check_and_replace(word,"iti","",1);
            else if (word.endsWith("ous"))
                output = check_and_replace(word,"ous","",1);
            else if (word.endsWith("ive"))
                output = check_and_replace(word,"ive","",1);
            else if (word.endsWith("ize"))
                output = check_and_replace(word,"ize","",1);

        /*if(calculate_m_value(word)>1) {
            if (word.endsWith("al"))
                output = word.substring(0, word.length() - 2);
            else if (word.endsWith("ance"))
                output = word.substring(0, word.length() - 4);
            else if (word.endsWith("ence"))
                output = word.substring(0, word.length() - 4);
            else if (word.endsWith("ic"))
                output = word.substring(0, word.length() - 2);
            else if (word.endsWith("able"))
                output = word.substring(0, word.length() - 4);
            else if (word.endsWith("ant"))
                output = word.substring(0, word.length() - 3);
            else if (word.endsWith("ible"))
                output = word.substring(0, word.length() - 4);
            else if (word.endsWith("ement"))
                output = word.substring(0, word.length() - 5);
            else if (word.endsWith("ment"))
                output = word.substring(0, word.length() - 4);
            else if (word.endsWith("sion"))
                output = word.substring(0, word.length() - 4);
            else if (word.endsWith("tion"))
                output = word.substring(0, word.length() - 4);
            else if (word.endsWith("ou"))
                output = word.substring(0, word.length() - 2);
            else if (word.endsWith("ism"))
                output = word.substring(0, word.length() - 3);
            else if (word.endsWith("ate"))
                output = word.substring(0, word.length() - 3);
            else if (word.endsWith("iti"))
                output = word.substring(0, word.length() - 3);
            else if (word.endsWith("ous"))
                output = word.substring(0, word.length() - 3);
            else if (word.endsWith("ive"))
                output = word.substring(0, word.length() - 3);
            else if (word.endsWith("ize"))
                output = word.substring(0, word.length() - 3);
        }*/
        return output;
    }

    public String step5(String word){
        String output = word;
        if (word.endsWith("e"))
            output = check_and_replace(word,"e","",1);
        if (word.endsWith("e") && calculate_m_value(word.substring(0, word.length() - 1))==1 && word.charAt(word.length()-1)!='o')
            output = word.substring(0, word.length() - 1);
        return  output;
    }
    public String apply_porter_stemming(String word){
        word = step1a(word);
        word = step1b(word);
        word = step1c(word);
        word = step2(word);
        word = step3(word);
        word = step4(word);
        word = step5(word);
        return word;
    }
}