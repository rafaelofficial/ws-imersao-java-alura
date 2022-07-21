import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {

    public static Properties getProps() {
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/main/resources/properties/configurations.properties");
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Not found file " + e.getMessage());
        }
        return properties;
    }

    public static void main(String[] args) throws Exception {
        // get URL in configurations.properties
        Properties properties = getProps();

        // to do a connection HTTP and get data the top 250 movies from Imdb: https://imdb-api.com/en/API/Top250TVs/
        var url = properties.getProperty("url");
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // extract only the data then interesting (title, poster, rating);
        var parser = new JsonParser();
        List<Map<String, String>> listOfMovies = parser.parse(body);

        // show and manipulated the data;
        var generator = new StickerGeneratorFactory();
        for (Map<String, String> movie : listOfMovies) {

            String urlImage =  movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = "src/main/resources/output/" + title + ".png";

            generator.create(inputStream, fileName);

            System.out.println("\u001b[33mTitle:\033[m " + title);
            System.out.println("\u001b[30;1m\u001b[43;1m Rating: " + movie.get("imDbRating") + " \u001b[m");
            System.out.println();
        }
    }
}
