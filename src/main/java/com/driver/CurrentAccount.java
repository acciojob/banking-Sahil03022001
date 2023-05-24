package com.driver;

import java.util.Arrays;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(!validate(tradeLicenseId.toCharArray())) {
            char[] newLicenseId = new char[tradeLicenseId.length()];
            char[] oldLicenseId = tradeLicenseId.toCharArray();
            Arrays.sort(oldLicenseId);
            int pointer = 0;
            for(int i = 0; i < tradeLicenseId.length(); i++) {
                newLicenseId[pointer] = oldLicenseId[i];
                pointer = (pointer + 2) % tradeLicenseId.length();
            }
            if(!validate(newLicenseId)) {
                throw new Exception("Valid License can not be generated");
            } else {
                this.tradeLicenseId = new String(newLicenseId);
            }
        }
    }

    public boolean validate(char[] license) {
        for(int i = 0; i < tradeLicenseId.length() - 1; i++) {
            if (tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
