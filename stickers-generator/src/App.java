import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
    
        //Make a HTTP Connection and get the top 250 movies from IMDB
        //String url = "https://imdb-api.com/en/API/MostPopularMovies/k_11mvwuw6";
        String url = "https://imdb-api.com/en/API/Top250Movies/k_11mvwuw6";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();     
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //Extract important data (title, image, classification) 
        //***update this section using an specific lib to parse json***
        JsonParser parser = new JsonParser();
        List<Map<String, String>> MoviesList = parser.parse(body);
        System.out.println(MoviesList.get(0));

        int cont = 0;

        //Show and manipulate data

        StickersGenerator generator = new StickersGenerator();

        for (Map<String,String> movie : MoviesList) {

            String urlImage = movie.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String movieName = movie.get("title");
            InputStream inputStream = new URL(urlImage).openStream();

            String fileName = movieName + ".png";

            generator.create(inputStream, fileName);

            System.out.println(movieName);
            System.out.println();       
            
            //
            if (cont == 5){
                break;
            }
            cont++;
        }
    
    
    }
}
