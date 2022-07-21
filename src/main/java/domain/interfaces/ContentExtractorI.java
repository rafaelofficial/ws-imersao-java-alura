package domain.interfaces;

import domain.entities.Content;

import java.util.List;

public interface ContentExtractorI {

    List<Content> contentsExtract(String json);
}
