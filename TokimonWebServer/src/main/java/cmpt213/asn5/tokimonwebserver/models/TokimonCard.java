package cmpt213.asn5.tokimonwebserver.models;

public class TokimonCard {

    private int id;
    private String name;
    private String type;
    private int rarity;
    private double healthPoints;
    private String imgLink;

    public TokimonCard (String name, String type, int rarity, double healthPoints, String imgLink) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.healthPoints = healthPoints;
        this.imgLink = imgLink;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getRarity() {
        return rarity;
    }

    public String getImgLink() {
        return imgLink;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

}
