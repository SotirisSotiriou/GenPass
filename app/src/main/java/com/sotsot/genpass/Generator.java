package com.sotsot.genpass;

import android.os.strictmode.CleartextNetworkViolation;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Generator {
    private static final String ERROR_CODE = "101";

    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "./\\\"?_-+=#$%&*()!";
    private static final String ALL = LOWERCASE_LETTERS.concat(UPPERCASE_LETTERS).concat(NUMBERS).concat(SYMBOLS);

    public static String generate(boolean with_lowercase, boolean with_uppercase, boolean with_numbers, boolean with_symbols, int length){

        PasswordGenerator generator = new PasswordGenerator();

        CharacterData lowercaseChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return ERROR_CODE;
            }

            @Override
            public String getCharacters() {
                return LOWERCASE_LETTERS;
            }
        };

        CharacterRule lowercaseRule = new CharacterRule(lowercaseChars);
        lowercaseRule.setNumberOfCharacters(1);

        CharacterData uppercaseChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return ERROR_CODE;
            }

            @Override
            public String getCharacters() {
                return UPPERCASE_LETTERS;
            }
        };

        CharacterRule uppercaseRule = new CharacterRule(uppercaseChars);
        uppercaseRule.setNumberOfCharacters(1);

        CharacterData numberChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return ERROR_CODE;
            }

            @Override
            public String getCharacters() {
                return NUMBERS;
            }
        };

        CharacterRule numbersRule = new CharacterRule(numberChars);
        numbersRule.setNumberOfCharacters(1);

        CharacterData symbolChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return ERROR_CODE;
            }

            @Override
            public String getCharacters() {
                return SYMBOLS;
            }
        };

        CharacterRule symbolsRule = new CharacterRule(symbolChars);
        symbolsRule.setNumberOfCharacters(1);

        ArrayList<CharacterRule> rules = new ArrayList<>();
        if(with_lowercase) rules.add(lowercaseRule);
        if(with_uppercase) rules.add(uppercaseRule);
        if(with_numbers) rules.add(numbersRule);
        if(with_symbols) rules.add(symbolsRule);

        String password = generator.generatePassword(length, rules);

        return password;
    }
}
