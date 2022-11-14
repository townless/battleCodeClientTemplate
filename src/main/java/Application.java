import http.CustomHttpClient;
import lombok.extern.slf4j.Slf4j;
import model.Action;
import model.Game;
import model.Round;
import model.TeamState;

import java.io.IOException;


@Slf4j
public class Application {

    /// REMPLACER PAR VOS IDENTIFIANTS //
    public static final String ID_PLAYER = "";
    public static final String TOKEN = "";

    public static void main(String[] args) throws IOException, InterruptedException {

        boolean trainningMode = true;

        String idPartie = null;

        CustomHttpClient customHttpClient = new CustomHttpClient(ID_PLAYER,TOKEN);

        if(trainningMode) {
            idPartie = customHttpClient.startTrainning();
        } else  {
            idPartie = customHttpClient.recuperPartieAjouer();
        }

        if(idPartie != null ) {
            jouer(customHttpClient,idPartie);
        } else {
            log.error("Aucune partie a jouer, id partie null");
        }

    }


    private static void jouer(CustomHttpClient customHttpClient, String gameId) throws IOException, InterruptedException {

        boolean partieTerminee = false;

        while (!partieTerminee) {

            String status = customHttpClient.getState(gameId);


            while ("CANTPLAY".equals(status)) {
                log.info("STATUS CANTPLAY");
                Thread.sleep(500);
                status = customHttpClient.getState(gameId);
            }

            if("CANPLAY".equals(status)) {

                Game game = customHttpClient.getGame(gameId);

                String coupAJouer = definirCoup(game);

                String play = customHttpClient.jouerCoup(gameId,  coupAJouer);
                log.info("Coup joue " + play);
            } else {
                log.info("ETAT PARTIE: " + status);
                log.info("Fin de la partie");
                partieTerminee = true;
            }
        }
    }
    
    private static String definirCoup(Game game) {
        
        int nbRounds = game.getRoundList().size();

        Round round = game.getRoundList().get(nbRounds - 1);


        // Recupérer mes informations //
        Action maDerniereAction = game.getIdTeamOne().equals(ID_PLAYER) ? round.getActionPlayerOne() : round.getActionPlayerTwo();

        String dernierCoupJoue = maDerniereAction.getHitPlayed().toString();

        TeamState monDernierEtat = maDerniereAction.getTeamState();

        int nbBallesQueJai =  monDernierEtat.getNbBullets();
        int pdvQueJai = monDernierEtat.getPointOfLife();
        int pointProtectionTonneauQueJai = monDernierEtat.getShieldLife();


        // Recupérer les informations de l'adversaire //

        Action adversaireDerniereAction = game.getIdTeamOne().equals(ID_PLAYER) ? round.getActionPlayerTwo() : round.getActionPlayerOne();

        String adversaireDernierCoupJoue = adversaireDerniereAction.getHitPlayed().toString();

        TeamState adversaireDernierEtat = adversaireDerniereAction.getTeamState();

        int nbBallesAdversaire =  adversaireDernierEtat.getNbBullets();
        int pdvAdversaire = adversaireDernierEtat.getPointOfLife();
        int pointProtectionTonneauAdversaire = adversaireDernierEtat.getShieldLife();


        // Prise decision //

        return nbRounds % 2 == 0 ?  "SHOOT_UP" : "RELOAD";
    }
    
}