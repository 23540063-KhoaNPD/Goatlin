package com.cx.goatlin.helpers

object PasswordHelper {

    fun strength(password: String): Boolean {
        var complexityRulesMatches: Int = 0

        if (!length(password)) {
            return false
        }

        if (hasAtleastOneUppercaseLetter(password)) {
            complexityRulesMatches++
        }

        if (hasAtleastOneLowercaseLetter(password)) {
            complexityRulesMatches++
        }

        if (hasAtleastOneDigitLetter(password)) {
            complexityRulesMatches++
        }

        if (hasAtleastOneSpecialChar(password)) {
            complexityRulesMatches++
        }

        if (complexityRulesMatches < 3) {
            return false
        }

        return true;
    }

    private fun hasAtleastOneUppercaseLetter(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }

    private fun hasAtleastOneLowercaseLetter(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }

    private fun hasAtleastOneDigitLetter(password: String): Boolean {
        return password.any { it.isDigit() }
    }

    private fun hasAtleastOneSpecialChar(password: String): Boolean {
        return password.any { !it.isLetterOrDigit() }
    }

    private fun length(password: String): Boolean {
        return password.length >= 8;
    }
}