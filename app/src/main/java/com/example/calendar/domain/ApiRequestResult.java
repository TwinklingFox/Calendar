package com.example.calendar.domain;

import org.apache.commons.text.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ApiRequestResult implements Callable<List<String>> {
    private final List<String> resultList = new ArrayList<>();

    public List<String> getResultList() {
        return resultList;
    }

    public ApiRequestResult(InputStream result) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(result));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        String resultStr = StringEscapeUtils.unescapeJava(String.valueOf(content));
        String[] phrases = resultStr.substring(1, resultStr.length() - 1).split("\", \"");
        String quote = phrases[0].substring(1);
        String author = phrases[1].substring(0, phrases[1].length() - 1);
        resultList.add(quote);
        resultList.add(author);
    }

    @Override
    public List<String> call() {
        return getResultList();
    }
}
