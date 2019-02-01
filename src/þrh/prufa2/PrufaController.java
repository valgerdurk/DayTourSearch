/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package þrh.prufa2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import org.json.*;
import java.io.*;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * Controller for a very basic trip search mechanism using JSON
 * @author Valgerdur Kristinsdottir vak9@hi.is
 */
public class PrufaController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Button bookNowButton;
    @FXML
    private Label price;
    @FXML
    private Label about;
    @FXML
    private Label duration;
    @FXML
    private Label title;
    @FXML
    private Font x1;
    @FXML
    private TextField keyword;
    @FXML
    private Button search;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");  
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Reads file from given filename and returns data in a string
     * @param filename
     * @return 
     */
    public static String readFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * Event handler for search button
     * Keywords are extracted from text field and 
     * compared to trip keywords from JSON file
     * The matching trip appears on screen
     * @param event 
     */
    @FXML
    private void handleSearch(ActionEvent event) {
        String jsonData = readFile("trips.json");
        JSONObject obj = new JSONObject(jsonData);
        JSONArray trips = new JSONArray(obj.getJSONArray("trips").toString());
        
        int tripNumber = 0;
        String key = keyword.getText();
        
        for(int i = 0; i < trips.length(); i++) {
            // We make an array of the keywords
            JSONArray keys = new JSONArray(trips.getJSONObject(i).
                    getJSONArray("keywords").toString());
            for(int j = 0; j < keys.length(); j++) {
                // We check if any of the keywords matches the input
                if(keys.getString(j).equals(key)){
                    tripNumber = i; // Trip number is set to matching trip
                }
            }
        }
        
        // Trip information for given trip number appears on screen
        duration.setText(trips.getJSONObject(tripNumber).getString("duration"));
        price.setText(trips.getJSONObject(tripNumber).getString("price"));
        about.setText(trips.getJSONObject(tripNumber).getString("about"));
        title.setText(trips.getJSONObject(tripNumber).getString("title"));
        
        Image img = new Image(trips.getJSONObject(tripNumber).getString("image"));
        image.setImage(img);       
   
    }
}
