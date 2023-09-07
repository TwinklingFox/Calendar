package com.example.calendar.entity;

import com.example.calendar.domain.ApiRequestResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest implements IRequestService.ApiMethods {
    public ApiRequestResult getApiResult() throws IOException {
        URL url = new URL(Data.URL.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream result;

        int status = connection.getResponseCode();

        if (status != HttpURLConnection.HTTP_OK) {
            result = connection.getErrorStream();
        } else {
            result = connection.getInputStream();
        }

        connection.disconnect();

        return new ApiRequestResult(result);
    }
}
