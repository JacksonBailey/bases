/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example.greeter.app;

import com.example.greeter.list.LinkedList;

import static com.example.greeter.utilities.StringUtils.join;
import static com.example.greeter.utilities.StringUtils.split;
import static com.example.greeter.app.MessageUtils.getMessage;

import org.apache.commons.text.WordUtils;

public class App {
    public static void main(String[] args) {
        LinkedList tokens;
        tokens = split(getMessage());
        String result = join(tokens);
        System.out.println(WordUtils.capitalize(result));
    }
}
