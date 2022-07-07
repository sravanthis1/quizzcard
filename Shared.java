package com.example.quiz;

import java.util.regex.Pattern;

public class Shared {
    protected static final Pattern DATE_REGEX =
            Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");
    protected static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
}
