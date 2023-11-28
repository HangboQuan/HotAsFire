/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.org.apache.xpath.internal.compiler;

import java.util.Arrays;

/**
 * XPath tokens
 */
public final class Token {
    static final char EM = '!';
    static final char EQ = '=';
    static final char LT = '<';
    static final char GT = '>';
    static final char PLUS = '+';
    static final char MINUS = '-';
    static final char STAR = '*';
    static final char VBAR = '|';
    static final char SLASH = '/';
    static final char LBRACK = '[';
    static final char RBRACK = ']';
    static final char LPAREN = '(';
    static final char RPAREN = ')';
    static final char COMMA = ',';
    static final char DOT = '.';
    static final char AT = '@';
    static final char US = '_';
    static final char COLON = ':';
    static final char SQ = '\'';
    static final char DQ = '"';
    static final char DOLLAR = '$';

    static final String OR = "or";
    static final String AND = "and";
    static final String DIV = "div";
    static final String MOD = "mod";
    static final String QUO = "quo";
    static final String DOT_STR = ".";
    static final String DDOT = "..";
    static final String DCOLON = "::";
    static final String ATTR = "attribute";
    static final String CHILD = "child";

    static final String[] OPERATORS = {OR, AND, DIV, MOD, QUO,
        DDOT, DCOLON, ATTR, CHILD};

    public static boolean contains(String str) {
        for (String operator : OPERATORS) {
            if (operator.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private Token() {
        //to prevent instantiation
    }
}
