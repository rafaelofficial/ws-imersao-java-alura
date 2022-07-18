import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // to do a connection HTTP and get data the top 250 movies from Imdb:
        String url = "https://imdb-api.com/en/API/Top250TVs/k_p037jo73";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // extract only the data then interesting (title, poster, rating);
        var parser = new JsonParser();
        List<Map<String, String>> listOfMovies = parser.parse(body);
        
        // show and manipulated the data;
        for (Map<String, String> movie : listOfMovies) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}
