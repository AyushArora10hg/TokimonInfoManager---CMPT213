package cmpt213.asn5.tokimonwebserver.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TokimonList {

    private final ArrayList <TokimonCard> cards;

    public TokimonList () {

        this.cards = new ArrayList<>();

    }

    public ArrayList<TokimonCard> getCards() {
        return cards;
    }

    public TokimonCard getCard (int id) {

        for (TokimonCard card : cards){
            if (card.getId() == id)
                return card;
        }
        return null;
    }

    public void addCard (TokimonCard newCard) throws IOException {

        this.cards.add(newCard);
        updateJSONFile();

    }


    public void deleteCard (int id) throws IOException {

        cards.removeIf(card -> card.getId() == id);
        updateJSONFile();
    }

    public void editCard (int id, TokimonCard newCard) throws IOException {

        if (this.getCard(id) != null){
            this.getCard(id).setName(newCard.getName());
            this.getCard(id).setType(newCard.getType());
            this.getCard(id).setRarity(newCard.getRarity());
            this.getCard(id).setHealthPoints(newCard.getHealthPoints());
            this.getCard(id).setImgLink(newCard.getImgLink());
        }
        updateJSONFile();

    }

    private JSONObject createJSONObject (TokimonCard card) {

        JSONObject obj = new JSONObject();
        obj.put("id", card.getId());
        obj.put("name", card.getName());
        obj.put("type", card.getType());
        obj.put("rarity", card.getRarity());
        obj.put("healthPoints", card.getHealthPoints());
        obj.put("imgLink", card.getImgLink());
        return obj;
    }

    private void updateJSONFile () throws IOException {

        FileWriter writer = new FileWriter("tokimoncards.json");
        JSONArray array = new JSONArray();
        for (TokimonCard card : cards){
            JSONObject obj =  this.createJSONObject(card);
            array.add(obj);
        }

        writer.write(array.toJSONString());
        writer.close();
    }

}

