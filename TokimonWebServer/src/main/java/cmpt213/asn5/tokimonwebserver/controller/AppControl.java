package cmpt213.asn5.tokimonwebserver.controller;

import cmpt213.asn5.tokimonwebserver.models.TokimonCard;
import cmpt213.asn5.tokimonwebserver.models.TokimonList;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AppControl {

    private int uniqueID = 0;
    private final TokimonList tokimonList = new TokimonList();

    public AppControl() throws IOException {

    }

    @GetMapping("/api/tokimon/all")
    public TokimonList getTokimonList() {

        return tokimonList;

    }

    @GetMapping("/api/tokimon/{id}")
    public TokimonCard getCard(@PathVariable int id, HttpServletResponse response) {

        if (tokimonList.getCard(id) != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            return tokimonList.getCard(id);
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;

    }

    @PostMapping("/api/tokimon/add")
    public TokimonCard addTokimon(@RequestBody TokimonCard newCard, HttpServletResponse response) throws IOException {


        newCard.setId(++uniqueID);
        tokimonList.addCard(newCard);
        System.out.println(uniqueID);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return newCard;

    }

    @DeleteMapping("/api/tokimon/{id}")
    public TokimonCard deleteTokimon(@PathVariable int id, HttpServletResponse response) throws IOException {

        TokimonCard temp = tokimonList.getCard(id);
        if (temp != null) {
            tokimonList.deleteCard(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return temp;
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;


    }

    @PutMapping("/api/tokimon/edit/{id}")
    public void editTokimon(@PathVariable int id, @RequestBody TokimonCard newCard) throws IOException {

        tokimonList.editCard(id, newCard);

    }
}
