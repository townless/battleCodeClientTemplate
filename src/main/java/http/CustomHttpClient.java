package http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.net.httpserver.Request;
import model.Game;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CustomHttpClient {

    private static String urlApi = "https://battlecode.acensi.ca/api";

    private  String idPlayer;
    private  String token;


    private HttpClient client = null;

    private ObjectMapper mapper = new ObjectMapper();

    public CustomHttpClient(String idPlayer, String token) {

        mapper.registerModule(new JavaTimeModule());

        this.token = token;
        this.idPlayer = idPlayer;
        client = HttpClient.newHttpClient();
    }

    public Game getGame(String gameId) throws IOException, InterruptedException {


        Game game = null;

        URI uri = URI.create(urlApi + "/game/" + gameId + "/allRounds");
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().header("Authorization", token ).build();


        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200 ) {
            game = mapper.readValue(response.body(), Game.class);
        }

        return game;
    }

    public Game lastRound(String gameId) throws IOException, InterruptedException {


        Game game = null;

        URI uri = URI.create(urlApi + "/game/" + gameId + "/lastRound");
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().header("Authorization", token ).build();


        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200 ) {
            game = mapper.readValue(response.body(), Game.class);
        }

        return game;
    }


    public String getState(String gameId) throws IOException, InterruptedException {

        String state = null;

        URI uri = URI.create(urlApi + "/game/" + gameId +"/" + idPlayer + "/state");
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().header("Authorization", token ).build();


        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200 ) {
            ApiResponse apiResponse = mapper.readValue(response.body(), ApiResponse.class);
            state = apiResponse.getContent();
        }

        return state;

    }


    public String jouerCoup(String gameId, String coup) throws IOException, InterruptedException {

        String reponse = null;

        URI uri = URI.create(urlApi + "/game/duel/" + gameId +"/" + idPlayer + "/play/" + coup);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.noBody()).header("Authorization", token ).build();


        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200 ) {
            ApiResponse apiResponse = mapper.readValue(response.body(), ApiResponse.class);
            reponse = apiResponse.getContent();
        }

        return reponse;

    }


    /**
     * Permet de demarrer une partie d'entrainement
     * @return id de la partie, null en cas d'erreur
     * @throws IOException
     * @throws InterruptedException
     */
    public String startTrainning() throws IOException, InterruptedException {

        String idPartie = null;

        URI uri = URI.create(urlApi + "/trainning/new/1/" + idPlayer);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.noBody()).header("Authorization", token ).build();


        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200 ) {
            ApiResponse apiResponse = mapper.readValue(response.body(), ApiResponse.class);
            idPartie = apiResponse.getContent();
        }

        return idPartie;

    }


    /**
     * Permet de recupérer la partie à jouer
     * @return id de la partie, null en cas d'erreur
     * @throws IOException
     * @throws InterruptedException
     */
    public String recuperPartieAjouer() throws IOException, InterruptedException {

        String idPartie = null;

        URI uri = URI.create(urlApi + "/game/next/" + idPlayer);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().header("Authorization", token ).build();


        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200 ) {
            ApiResponse apiResponse = mapper.readValue(response.body(), ApiResponse.class);
            idPartie = apiResponse.getContent();
        }

        return idPartie;

    }
}
