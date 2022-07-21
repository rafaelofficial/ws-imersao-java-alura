package domain.controllers;

import domain.entities.Content;
import domain.interfaces.ContentExtractorI;
import domain.utils.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorOfNasaController implements ContentExtractorI {

    public List<Content> contentsExtract(String json) {

        // extract only the data then interesting (title, poster, rating);
        var parser = new JsonParser();
        List<Map<String, String>> listOfAttributes = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // populate the contents list
        for (Map<String, String> attributes : listOfAttributes) {
            String title = attributes.get("title");
            String urlImage = attributes.get("url");

            var content = new Content(title, urlImage);
            contents.add(content);
        }

        return contents;
    }
}
