package com.sakhshop.backend._helper.characters;

import java.util.ArrayList;
import java.util.List;

public class GetCharacterFromStrings {

    private GetCharacterFromStrings() {
    }

    public static List<String> charList (String fileName) {

        String charAtZero = String.valueOf(fileName.charAt(0));

        String charAtOne = String.valueOf(fileName.charAt(1));
        String charAtTwo = String.valueOf(fileName.charAt(2));
        String charGroup1 = charAtOne + charAtTwo;

        String charAtThree = String.valueOf(fileName.charAt(3));
        String charAtFour = String.valueOf(fileName.charAt(4));
        String charGroup2 = charAtThree + charAtFour;

        String charAtFive = String.valueOf(fileName.charAt(5));
        String charAtSix = String.valueOf(fileName.charAt(6));
        String charGroup3 = charAtFive + charAtSix;

        String folderGroup3 = charAtZero + "/" + charGroup1 + "/" + charGroup2 + "/" + charGroup3;
        String folderGroup2 = charAtZero + "/" + charGroup1 + "/" + charGroup2;
        String folderGroup1 = charAtZero + "/" + charGroup1;

        List<String> charList = new ArrayList<>();
        charList.add(0, charAtZero);
        charList.add(1, folderGroup1);
        charList.add(2, folderGroup2);
        charList.add(3, folderGroup3);


        return charList;
    }

}
