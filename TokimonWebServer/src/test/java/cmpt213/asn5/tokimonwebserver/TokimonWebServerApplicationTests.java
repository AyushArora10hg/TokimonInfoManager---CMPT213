//package cmpt213.asn5.tokimonwebserver;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import cmpt213.asn5.tokimonwebserver.models.TokimonCard;
//import cmpt213.asn5.tokimonwebserver.models.TokimonList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class TokimonWebServerApplicationTests {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    private static final String base_URL = "/api/tokimon";
//
//    @BeforeEach
//    void setup() throws Exception {
//        System.out.println(" Setting up the server: ");
//    }
//
//    @Test
//    void testAddTokimonCard() throws Exception {
//        TokimonCard tokimonCard = new TokimonCard("Bulbasaur", "Grass", 2, 80.0, "img/bulbasaur.png");
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(base_URL + "/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(tokimonCard)))
//                        .andExpect(MockMvcResultMatchers.status().isCreated())
//                        .andReturn();
////        System.out.println(tokimonCard.getId());
//
//        String contentAsString = result.getResponse().getContentAsString();
//        TokimonCard toki = mapper.readValue(contentAsString, TokimonCard.class);
//
//        assertThat(toki).isNotNull();
//        assertThat(toki.getName()).isEqualTo("Bulbasaur");
//    }
//
//    @Test
//    void testGetCardByIdNotFound() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get(base_URL + "/999"))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    void testDeleteTokimonCard() throws Exception {
//        TokimonCard tokimonCard = new TokimonCard("Squirtle", "Water", 4, 85.0, "img/squirtle.png");
//
//        mvc.perform(MockMvcRequestBuilders.post(base_URL + "/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(tokimonCard)))
//                        .andExpect(MockMvcResultMatchers.status().isCreated());
//
//        mvc.perform(MockMvcRequestBuilders.delete(base_URL + "/1"))
//                        .andExpect(MockMvcResultMatchers.status().isNoContent());
//
//        mvc.perform(MockMvcRequestBuilders.get(base_URL + "/1"))
//                        .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//
//    @Test
//    void testGetCardByIdFound() throws Exception {
//        TokimonCard tokimonCard = new TokimonCard("Pikachu", "Electric", 1, 100.0, "img/pikachu.png");
//
//        mvc.perform(MockMvcRequestBuilders.post(base_URL + "/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(tokimonCard)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andReturn();
//
//        String contentResponse = create.getResponse().getContentAsString();
//        TokimonCard tokiCard = mapper.readValue(contentResponse, TokimonCard.class);
//
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(base_URL + "/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        TokimonCard toki = mapper.readValue(contentAsString, TokimonCard.class);
//
//        assertThat(toki).isNotNull();
//        assertThat(toki.getName()).isEqualTo("Pikachu");
//    }
//
//    @Test
//    void testGetAllTokimonCardsWhenEmpty() throws Exception {
//
//       MvcResult createResult = mvc.perform;
//
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(base_URL + "/all"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String contentAsString = result.getResponse().getContentAsString();
//        TokimonList toki = mapper.readValue(contentAsString, TokimonList.class);
//
//        assertThat(toki.getCards()).isEmpty();
//    }
//
//    @Test
//    void testEditTokimonCard() throws Exception {
//        TokimonCard newCard = new TokimonCard("Charmander", "Fire", 3, 90.0, "img/charmander.png");
//
//
//        mvc.perform(MockMvcRequestBuilders.post(base_URL + "/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(newCard)))
//                        .andExpect(MockMvcResultMatchers.status().isCreated());
//
//        TokimonCard update = new TokimonCard("Charmeleon", "Fire", 3, 95.0, "img/charmeleon.png");
//
//        mvc.perform(MockMvcRequestBuilders.put(base_URL + "/edit/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(update)))
//                        .andExpect(MockMvcResultMatchers.status().isOk());
//
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(base_URL + "/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        TokimonCard toki = mapper.readValue(contentAsString, TokimonCard.class);
//
//        assertThat(toki).isNotNull();
//        assertThat(toki.getName()).isEqualTo("Charmeleon");
//    }
//
//    @Test
//    void testGetAllTokimonCards() throws Exception {
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(base_URL + "/all"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String contentAsString = result.getResponse().getContentAsString();
//        TokimonList toki = mapper.readValue(contentAsString, TokimonList.class);
//
//        assertThat(toki).isNotNull();
//    }
//}
