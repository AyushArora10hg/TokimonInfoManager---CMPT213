package cmpt213.asn5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class ClientRequestManager{

    private static final String BaseURL = "http://localhost:8080/api/tokimon/";

    public static void displayData(String data, String img) {
        Stage stage = new Stage();
        stage.setTitle("Data Display");


        TextArea textArea = new TextArea(data);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        ImageView imageView = new ImageView(new Image(img));
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);

        VBox vbox = new VBox(10, textArea, imageView);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static EventHandler <MouseEvent> getAllRequest() {

        return mouseEvent -> {

            try {
                URL getAll = (new URI (BaseURL + "all")).toURL();
                HttpURLConnection connection = (HttpURLConnection) getAll.openConnection();
                connection.setRequestMethod("GET");
                connection.getInputStream();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                JSONParser parser = new JSONParser();
                String output = response.toString();

                JSONObject obj1 = (JSONObject) parser.parse(output);
                JSONArray array = (JSONArray) obj1.get("cards");
                StringBuilder formattedData = new StringBuilder();

                for (Object obj : array) {
                    JSONObject card = (JSONObject) obj;
                    formattedData.append(formatInfo(card)).append("\n");
                }

                displayData(formattedData.toString(), "file:src/images/delete.png");

                connection.disconnect();

            }
            catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }

        };
    }

    public static EventHandler <ActionEvent> getRequest (String id) {

        return actionEvent -> {

            try {

                URL get = (new URI (BaseURL + id)).toURL();
                HttpURLConnection connection = (HttpURLConnection) get.openConnection();
                connection.setRequestMethod("GET");
                connection.getInputStream();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();
                String output = response.toString();
                String formatted;
                JSONObject obj1 =  new JSONObject();
                if (!output.isEmpty()){
                    JSONParser parser = new JSONParser();
                    obj1 = (JSONObject) parser.parse(output);
                    formatted = formatInfo(obj1);
                }
                else
                    formatted = "Card not Found";


                displayData(formatted, (String) obj1.get("imgLink"));
                connection.disconnect();

            }
            catch (Exception e){

                System.err.println(e.getMessage());
                System.exit(-1);
            }

        };

    }

    public static EventHandler <ActionEvent> addRequest (String...input) {

        return mouseEvent -> {

            try {
                URL add = (new URI (BaseURL + "add")).toURL();
                HttpURLConnection connection = (HttpURLConnection) add.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                String json = String.format(
                        "{\"name\":\"%s\",\"type\":\"%s\",\"rarity\":%d,\"healthPoints\":%f,\"imgLink\":\"%s\"}",
                        input[0], input[1], Integer.parseInt(input[2]), Double.parseDouble(input[3]), input[4]
                );
                wr.write(json);
                wr.flush();
                wr.close();
                connection.connect();
                System.out.println("ResponseCode: " + connection.getResponseCode());
                connection.disconnect();
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        };
    }

    public static EventHandler<ActionEvent> editRequest (String... input) {

        return mouseEvent -> {

            try {

                URL edit = (new URI("http://localhost:8080/api/tokimon/edit/" + input[0])).toURL();
                HttpURLConnection connection = (HttpURLConnection) edit.openConnection();
                connection.setRequestMethod("PUT");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                String json = String.format(
                        "{\"name\":\"%s\",\"type\":\"%s\",\"rarity\":%d,\"healthPoints\":%f,\"imgLink\":\"%s\"}",
                        input[1], input[2], Integer.parseInt(input[3]), Double.parseDouble(input[4]), input[5]
                );
                wr.write(json);
                wr.flush();
                wr.close();
                System.out.println("ResponseCode: " + connection.getResponseCode());
                connection.disconnect();


            } catch (Exception e) {

                System.err.println(e.getMessage());
                System.exit(-1);
            }
        };

    }

    public static EventHandler <ActionEvent> deleteRequest (String id) {

        return mouseEvent -> {

            try{

                URL del = (new URI(BaseURL + id)).toURL();
                HttpURLConnection connection = (HttpURLConnection) del.openConnection();
                connection.setRequestMethod("DELETE");

                if (connection.getResponseCode() == 204) {

                    displayData("Card Deleted", "ff");
                }
                else
                    displayData("Card not Found", "Ff");

                connection.disconnect();

            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        };
    }

    private static String formatInfo(JSONObject jsonObject1) {
        Object idObj = jsonObject1.get("id");
        int id = (idObj instanceof Number) ? ((Number) idObj).intValue() : 0;

        String name = (String) jsonObject1.get("name");
        String type = (String) jsonObject1.get("type");

        Object rarityObj = jsonObject1.get("rarity");
        long rarity = (rarityObj instanceof Number) ? ((Number) rarityObj).longValue() : 0;

        Object hpObj = jsonObject1.get("healthPoints");
        double hp = (hpObj instanceof Number) ? ((Number) hpObj).doubleValue() : 0;

        String imgLink = (String) jsonObject1.get("imgLink");

        return String.format(
                "Id: %d, Name: %s, Type: %s, Rarity: %d, Health Points: %.2f, Image Link: %s",
                id, name, type, rarity, hp, imgLink
        );
    }


}
