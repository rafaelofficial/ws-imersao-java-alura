import domain.controllers.ContentExtractorOfNasaController;
import domain.controllers.StickerGeneratorFactoryController;
import domain.entities.Content;
import domain.interfaces.ContentExtractorI;
import domain.services.ClientHttp;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static domain.utils.Configuration.getProps;

public class App {

    public static void main(String[] args) throws Exception {

        // to do a connection HTTP and get data the API
        var properties = getProps();

        // get URL in config.properties
        //var url = properties.getProperty("mock_imdb_api");
        //ContentExtractorI extractor = new ContentExtractorOfIMDBController();

        var url = properties.getProperty("nasa_key_api");
        ContentExtractorI extractor = new ContentExtractorOfNasaController();

        // ClientHttp instance
        var http = new ClientHttp();
        String json = http.getData(url);

        // show and manipulated the data;
        List<Content> contents = extractor.contentsExtract(json);

        var generator = new StickerGeneratorFactoryController();
        for (int i = 0; i < 4; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String fileName = "src/main/resources/output/" + content.getTitle() + ".png";

            generator.create(inputStream, fileName);

            System.out.println("\u001b[33mTitle:\033[m " + content.getTitle());
            System.out.println();
        }
    }
}
