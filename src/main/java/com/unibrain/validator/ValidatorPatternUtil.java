package com.unibrain.validator;

import java.util.regex.Pattern;

public class ValidatorPatternUtil {

	// Format : DD-MM-YYYY
	public Pattern getDatePattern() {
		Pattern datePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}", Pattern.CASE_INSENSITIVE);

		return datePattern;
	}

	// Format : DD-MM-YYYY HH:MM:SS
	public Pattern getDateAndTimeWithMiliSecondsPattern() {
		Pattern dateAndTimeWithMiliSecondsPattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}",
				Pattern.CASE_INSENSITIVE);

		return dateAndTimeWithMiliSecondsPattern;
	}

	// Format : DD-MM-YYYY HH:MM
	public Pattern getDateAndTimePattern() {
		Pattern dateAndTimePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}", Pattern.CASE_INSENSITIVE);

		return dateAndTimePattern;
	}

	// Format : MM-YYYY
	public Pattern getMonthAndYearPattern() {
		Pattern monthAndYearPattern = Pattern.compile("\\d{2}-\\d{4}", Pattern.CASE_INSENSITIVE);

		return monthAndYearPattern;
	}

	// Format HH:MM:SS
	public Pattern getTimePattern() {
		Pattern timePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}", Pattern.CASE_INSENSITIVE);
		return timePattern;
	}

	// Format HH:MM
	public Pattern getDurationPattern() {
		Pattern timePattern = Pattern.compile("\\d{2}:\\d{2}", Pattern.CASE_INSENSITIVE);
		return timePattern;
	}

	// User Id,Login Id all pattern same allows (Alphanumeric and Underscore)
	// (min:3,max:20)
	public Pattern getLoginIdPattern() {
		Pattern loginIdPattern = Pattern.compile("[a-zA-Z0-9_]{3,20}+", Pattern.CASE_INSENSITIVE);

		return loginIdPattern;
	}

	// Username, contact name, First Name, Student name etc all Allow (Alphabet
	// and
	// space)
	// (min:3,max:50)
	public Pattern getNamePattern() {
		Pattern namePattern = Pattern.compile("[a-zA-Z ]{3,50}+", Pattern.CASE_INSENSITIVE);

		return namePattern;
	}

	// Middle Name, Last Name etc all Allow (Alphabet and space)
	// (min:1,max:50)
	public Pattern getInitialsPattern() {
		Pattern initialsPattern = Pattern.compile("[a-zA-Z ]{1,50}+", Pattern.CASE_INSENSITIVE);

		return initialsPattern;
	}

	// Alphanumeric, special characters : at, underscore, full stop,
	// hyphen(min:6,max:50)
	public Pattern getEmailIdPattern() {
		Pattern emailIdPattern = Pattern.compile("^(?=.{6,50}$)[a-zA-Z0-9.@_-]+@[a-zA-Z0-9.@_-]+\\.[a-zA-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);

		return emailIdPattern;
	}

	// Numbers and special characters : Space, hyphen (min:5,max:20)
	public Pattern getPhoneNumberPattern() {
		Pattern phoneNumberPattern = Pattern.compile("[0-9 -]{5,20}", Pattern.CASE_INSENSITIVE);
		return phoneNumberPattern;
	}

	// Numbers (min:10,max:10)
	public Pattern getIndianMobileNumberPattern() {
		Pattern mobileNumberPattern = Pattern.compile("[0-9]{10,10}", Pattern.CASE_INSENSITIVE);

		return mobileNumberPattern;
	}

	// Numbers and special characters : Space, hyphen (min:5,max:20)
	public Pattern getForiegnMobileNumberPattern() {
		Pattern mobileNumberPattern = Pattern.compile("[0-9 -]{5,20}", Pattern.CASE_INSENSITIVE);

		return mobileNumberPattern;
	}

	// Alphanumeric and Special characters : Hash, comma, space, slash, hyphen,
	// Full
	// stop, left and right parenthesis "(" ")"
	// (min:2,max:50)
	public Pattern getTextFieldOnePattern() {

		Pattern textFieldOnePattern = Pattern.compile("[a-z A-Z0-9#-/,.()\\s]{2,50}+", Pattern.CASE_INSENSITIVE);

		return textFieldOnePattern;
	}

	// Alphanumeric and Special characters : Hash, comma, space, slash, hyphen,
	// Full
	// stop, left and right parenthesis "(" ")"
	// (min:3,max:150)
	public Pattern getTextFieldTwoPattern() {

		Pattern textFieldTwoPattern = Pattern.compile("[a-z A-Z0-9#-/,.()\\s]{3,150}+", Pattern.CASE_INSENSITIVE);

		return textFieldTwoPattern;
	}

	// Alphanumeric and Special characters : Hash, comma, space, slash, hyphen,
	// Full
	// stop, left and right parenthesis "(" ")"
	// (min:3,max:200)
	public Pattern getTextAreaOnePattern() {

		Pattern textAreaOnePattern = Pattern.compile("[a-z A-Z0-9#-/,.()\\s]{3,200}+", Pattern.CASE_INSENSITIVE);

		return textAreaOnePattern;
	}

	// Alphanumeric and Special characters : Hash, comma, space, slash, hyphen,
	// Full
	// stop, left and right parenthesis "(" ")"
	// (min:3,max:500)
	public Pattern getTextAreaTwoPattern() {

		Pattern textAreaTwoPattern = Pattern.compile("[a-z A-Z0-9#-/,.()\\s]{3,500}+", Pattern.CASE_INSENSITIVE);

		return textAreaTwoPattern;
	}

	// Only Numbers (min:6,max:6)

	public Pattern getPinCodePattern() {

		Pattern pinCodePattern = Pattern.compile("[0-9]{6}", Pattern.CASE_INSENSITIVE);

		return pinCodePattern;
	}

	// Alphanumeric, Special character : Hyphen (min3,max:12)
	public Pattern getPostalCodePattern() {

		Pattern postalCodePattern = Pattern.compile("[a-z A-Z0-9-]{3,12}+", Pattern.CASE_INSENSITIVE);
		return postalCodePattern;
	}

	// Numbers and full stop only(min:1,max:15)
	public Pattern getAmountPattern() {

		Pattern amountPattern = Pattern.compile("[0-9.]{1,15}", Pattern.CASE_INSENSITIVE);
		return amountPattern;
	}

	// This will work more efficiently.. 1. For number ranging 00 - 99.99
	// (decimal
	// inclusive)
	// (min:1,max:5)
	// NUmbers and full stop only; must be less than or equal to 100
	public Pattern getPercentagePattern() {

		Pattern percentagePattern = Pattern.compile("^((100\\.00|100\\.0|100)|(\\d{1,2}(.\\d{1,2})?))$",
				Pattern.CASE_INSENSITIVE);
		return percentagePattern;
	}

	// Only Numbers (min:9,max:18)

	public Pattern getBankAccountNumberPattern() {

		Pattern bankAccountNumberPattern = Pattern.compile("[0-9]{9,18}", Pattern.CASE_INSENSITIVE);

		return bankAccountNumberPattern;
	}

	// Alphanumeric (min:6,max:6)

	public Pattern getIFSCPattern() {

		Pattern IFSCPattern = Pattern.compile("^[A-Za-z]{4}[a-zA-Z0-9]{7}$", Pattern.CASE_INSENSITIVE);

		return IFSCPattern;
	}

	// Global Pan Number Pattern First 5 upper-case Alphabets
	// followed by 4 digits numbers and
	// followed by 1 upper-case Alphabets
	public Pattern getPanNumberPattern() {

		Pattern panNumberPattern = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");

		return panNumberPattern;
	}

	// Global GSTIN Pattern
	public Pattern getGSTINPattern() {

		Pattern gstInPattern = Pattern.compile("^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$");

		return gstInPattern;
	}

	// Alphanumeric and Special characters : Hash, comma, space, slash, hyphen,
	// Full stop, left and right parenthesis "(" ")"
	// (min:5,max:9)
	public Pattern getTextFieldOneMin5Max9Pattern() {

		Pattern textFieldOne5to9Pattern = Pattern.compile("[a-z A-Z0-9#-/,.()\\s]{5,9}+", Pattern.CASE_INSENSITIVE);

		return textFieldOne5to9Pattern;
	}

	// Alphanumeric and Special characters : Hash, comma, space, slash, hyphen,
	// Full
	// stop, left and right parenthesis "(" ")"
	// (min:4,max:6)
	public Pattern getEntryFieldOnePattern4_6() {

		Pattern entryFieldOnePattern = Pattern.compile("[a-z A-Z0-9#-/,.()\\s]{4,6}+", Pattern.CASE_INSENSITIVE);

		return entryFieldOnePattern;
	}

	// Account Code Allow (Numbers,hiphen
	// and
	// space)
	// (min:3,max:15)
	public Pattern getAccountCodePattern() {
		Pattern accountCodePattern = Pattern.compile("[a-z A-Z0-9#-/,.()\\s]{3,15}+", Pattern.CASE_INSENSITIVE);

		return accountCodePattern;
	}

	// Account Code Allow (Alphabet,numbers,hiphen
	// and
	// space)
	// (min:3,max:50)
	public Pattern getAccountNamePattern() {
		Pattern accountNamePattern = Pattern.compile("[a-zA-Z0-9- ]{3,50}+", Pattern.CASE_INSENSITIVE);

		return accountNamePattern;
	}

	// Alphanumeric : Hyphen (min:1,max:12)
	public Pattern getRoomNumberPattern() {

		Pattern roomNumberPattern = Pattern.compile("[a-z A-Z0-9]{1,15}+", Pattern.CASE_INSENSITIVE);
		return roomNumberPattern;
	}

	public Pattern getFileNamePattern() {

		Pattern fileNamePattern = Pattern.compile("([a-zA-Z0-9 ()._-])+(.pdf)$", Pattern.CASE_INSENSITIVE);

		return fileNamePattern;
	}

	// Alphanumeric and Special characters : Hash, comma, space, slash, hyphen,
	// Full
	// stop, left and right parenthesis "(" ")"
	// (min:1,max:50)
	public Pattern getTextFieldOneWithMinOneCharPattern() {

		Pattern textFieldOnePattern = Pattern.compile("[a-z A-Z0-9#-/,.()\\s]{1,50}+", Pattern.CASE_INSENSITIVE);

		return textFieldOnePattern;
	}

	public Pattern getPasswordPattern() {
		Pattern passwordPattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,64})", Pattern.CASE_INSENSITIVE);
		return passwordPattern;
	}

}
