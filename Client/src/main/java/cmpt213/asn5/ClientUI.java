package cmpt213.asn5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientUI {

    private VBox vBox;

    private ImageView getAllIcon () {

        ImageView getAllIcon = new ImageView(new Image("file:src/Images/all.jpg"));

        getAllIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, ClientRequestManager.getAllRequest());

        return getAllIcon;

    }

    private ImageView getIcon () {

        ImageView getIcon = new ImageView(new Image("file:src/Images/Get1.png"));

        getIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Stage stage1 = new Stage();
                stage1.setTitle("Get a Card");
                Label label = new Label("Enter ID: ");
                TextField tf = new TextField();
                tf.setText("0");
                Button get = new Button("Get");

                GridPane gp = new GridPane();
                gp.setPadding(new Insets(5));
                gp.setHgap(5);
                gp.setVgap(5);

                gp.add(label, 0, 0);
                gp.add(tf, 1, 0);
                gp.add(get, 0, 2);

                get.setOnAction(event -> {
                    String idInput = tf.getText();

                    if (idInput != null) {
                        ClientRequestManager.getRequest(idInput).handle(event);
                    } else {
                        System.err.println("Please enter a valid ID.");
                    }
                    stage1.close();
                });

                stage1.setScene(new Scene(gp));
                stage1.show();

            }
        });

        return getIcon;

    }

    private ImageView addIcon () {

        ImageView addIcon = new ImageView(new Image("file:src/Images/Add.png"));

        addIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

            Stage stage1 = new Stage();
            stage1.setTitle("Add a Card");

            Label name = new Label("Name: ");
            Label type = new Label("Type: ");
            Label rarity = new Label("Rarity: ");
            Label hp = new Label("HP: ");
            Label image = new Label("Image URL: ");

            TextField nameTF = new TextField();

            ComboBox<String> typeBox = new ComboBox<>();
            typeBox.getItems().addAll("Electric", "Fire", "Flying", "Ghost", "Grass", "Ground", "Ice", "Psychic", "Rock", "Water", "UNIQUE");
            typeBox.setPrefWidth(300);

            ComboBox<Integer> rarityBox = new ComboBox<>();
            rarityBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            rarityBox.setPrefWidth(300);

            Slider hpSlider = new Slider(0.0, 100.0, 100.0);
            hpSlider.setShowTickMarks(true);
            hpSlider.setMajorTickUnit(10.0);
            hpSlider.setMinorTickCount(5);
            hpSlider.setPrefWidth(300);
            hpSlider.setShowTickLabels(true);
            hpSlider.setSnapToTicks(true);

            TextField imgTf = new TextField();

            GridPane gp = new GridPane();
            gp.setVgap(5);
            gp.setHgap(5);
            gp.setPadding(new Insets(5));

            gp.add(name, 0, 0);
            gp.add(nameTF, 1, 0);
            gp.add(type, 0, 1);
            gp.add(typeBox, 1, 1);
            gp.add(rarity, 0, 2);
            gp.add(rarityBox, 1, 2);
            gp.add(hp, 0, 3);
            gp.add(hpSlider, 1, 3);
            gp.add(image, 0,4);
            gp.add(imgTf, 1,4);

            Button submit = new Button("ADD");
            gp.add(submit, 0, 6);

            stage1.setScene(new Scene(gp));
            stage1.show();

            submit.setOnAction(event -> {
                String nameInput = nameTF.getText();
                String typeInput = typeBox.getValue();
                Integer rarityInput = rarityBox.getValue();
                Double hpInput = hpSlider.getValue();
                String imgInput = imgTf.getText();

                if (nameInput != null && typeInput != null && rarityInput != null && hpInput != null) {
                    ClientRequestManager.addRequest(
                            nameInput,
                            typeInput,
                            String.valueOf(rarityInput),
                            String.valueOf(hpInput),
                            imgInput
                    ).handle(event);
                } else {

                    System.err.println("Please fill in all fields.");

                }

                stage1.close();
            });

        });

        return addIcon;

    }

    public ImageView delIcon () {

        ImageView delete = new ImageView(new Image("file:src/Images/delete.jpg"));

        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

            Stage stage1 = new Stage();
            stage1.setTitle("Delete a Card");
            Label label = new Label("Enter ID: ");
            TextField tf = new TextField();
            tf.setText("0");
            Button del = new Button("Delete");

            GridPane gp = new GridPane();
            gp.setPadding(new Insets(5));
            gp.setHgap(5);
            gp.setVgap(5);

            gp.add(label, 0, 0);
            gp.add(tf, 1, 0);
            gp.add(del, 0, 2);

            del.setOnAction(event -> {

                String idInput = tf.getText();

                if (idInput != null) {
                    ClientRequestManager.deleteRequest(
                            idInput
                    ).handle(event);
                } else {

                    System.err.println("Please fill in all fields.");
                }

                stage1.close();

            });

            stage1.setScene(new Scene(gp));
            stage1.show();

        });

        return delete;
    }

    public ImageView editIcon () {

        ImageView edit = new ImageView(new Image("file:src/Images/update.png"));

        edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Stage stage1 = new Stage();
                stage1.setTitle("Edit a Card");
                Label label = new Label("Enter ID: ");
                TextField tf = new TextField();
                tf.setText("0");
                Button update = new Button("Update");

                GridPane gp = new GridPane();
                gp.setPadding(new Insets(5));
                gp.setHgap(5);
                gp.setVgap(5);

                gp.add(label, 0, 0);
                gp.add(tf, 1, 0);
                gp.add(update, 0, 2);

                update.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        Label name = new Label("Name: ");
                        Label type = new Label("Type: ");
                        Label rarity = new Label("Rarity: ");
                        Label hp = new Label("HP: ");
                        Label imgLink = new Label("Image Link: ");

                        TextField nameTF = new TextField();

                        ComboBox<String> typeBox = new ComboBox<>();
                        typeBox.getItems().addAll("Electric", "Fire", "Flying", "Ghost", "Grass", "Ground", "Ice", "Psychic", "Rock", "Water", "UNIQUE");
                        typeBox.setPrefWidth(300);

                        ComboBox<Integer> rarityBox = new ComboBox<>();
                        rarityBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                        rarityBox.setPrefWidth(300);

                        Slider hpSlider = new Slider(0.0, 100.0, 100.0);
                        hpSlider.setShowTickMarks(true);
                        hpSlider.setMajorTickUnit(10.0);
                        hpSlider.setMinorTickCount(5);
                        hpSlider.setPrefWidth(300);
                        hpSlider.setShowTickLabels(true);
                        hpSlider.setSnapToTicks(true);

                        TextField imgTF = new TextField();

                        Button editInfo = new Button("Edit");

                        editInfo.setOnAction(event -> {
                            String idInput = tf.getText();
                            String nameInput = nameTF.getText();
                            String typeInput = typeBox.getValue();
                            Integer rarityInput = rarityBox.getValue();
                            Double hpInput = hpSlider.getValue();
                            String imgInput = imgTF.getText();

                            if (idInput != null && nameInput != null && typeInput != null && rarityInput != null && hpInput != null) {
                                ClientRequestManager.editRequest(
                                        idInput,
                                        nameInput,
                                        typeInput,
                                        String.valueOf(rarityInput),
                                        String.valueOf(hpInput),
                                        imgInput
                                ).handle(event);
                            } else {

                                System.err.println("Please fill in all fields.");

                            }

                            stage1.close();
                        });


                        GridPane gp = new GridPane();
                        gp.setVgap(5);
                        gp.setHgap(5);
                        gp.setPadding(new Insets(5));

                        gp.add(name, 0, 0);
                        gp.add(nameTF, 1, 0);
                        gp.add(type, 0, 1);
                        gp.add(typeBox, 1, 1);
                        gp.add(rarity, 0, 2);
                        gp.add(rarityBox, 1, 2);
                        gp.add(hp, 0, 3);
                        gp.add(hpSlider, 1, 3);
                        gp.add(imgLink,0,4);
                        gp.add(imgTF, 1,4);
                        gp.add(editInfo, 0, 6);

                        stage1.setScene(new Scene(gp));

                    }

                });

                stage1.setScene(new Scene(gp));
                stage1.show();

            }
        });

        return edit;

    }

    private void format (VBox vbox, ImageView ... imageViews) {

        ImageView logo = new ImageView(new Image("file:src/images/logo.png"));
        logo.setPreserveRatio(true);
        logo.setFitWidth(400);

        HBox logoBox = new HBox(logo);
        logoBox.setAlignment(Pos.CENTER);

        for (ImageView imageView : imageViews){
            imageView.setFitWidth(150);
            imageView.setPreserveRatio(true);
        }

        HBox hBox = new HBox(20, imageViews[0], imageViews[1], imageViews[2], imageViews[3], imageViews[4]);
        hBox.setAlignment(Pos.CENTER);

        vBox = new VBox(50, logoBox, hBox);
        vBox.setPadding(new Insets(20));

    }

    public VBox getUI () {

        ImageView getAll = this.getAllIcon();
        ImageView get = this.getIcon();
        ImageView add = this.addIcon();
        ImageView del = this.delIcon();
        ImageView edit = this.editIcon();

        format(vBox, getAll, get, add, del, edit);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        return vBox;
    }

}
