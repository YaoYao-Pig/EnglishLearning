package com.example.englishlearning.chatgpt;





import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import cz.msebera.android.httpclient.util.EntityUtils;

public class ChatGptController {



    public static String generateText(String apiKey, String prompt) throws IOException {

        HttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.openai.com/v1/engines/text-davinci-003/completions");

        httpPost.setHeader("Content-Type", "application/json");

        httpPost.setHeader("Authorization", "Bearer " + apiKey);

        String jsonInput = "{"

                + "\"prompt\": \"" + prompt + "\","

                + "\"max_tokens\": 500,"

                + "\"n\": 1,"

                + "\"stop\": null,"

                + "\"temperature\": 1.0"

                + "}";

        httpPost.setEntity(new StringEntity(jsonInput));

        HttpResponse response = httpClient.execute(httpPost);

        String jsonResponse = EntityUtils.toString(response.getEntity());

        return jsonResponse;

    }

}