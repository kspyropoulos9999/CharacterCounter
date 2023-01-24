package application;

 * Character Counter of .txt files

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Week2Lab2SourceCode extends Application {
    public static void main(String[] args){
        launch(args);
    }

  
    @Override
    public void start(Stage primaryStage){
    	
        primaryStage.setTitle("Character Counter"); // set title of program
        ArrayList<Integer> ASCIICharactersList = new ArrayList<Integer>(); // create array list to store ASCII values
        FileChooser fileChooser = new FileChooser(); // create ability to find files in computer
        Button SelectFileButton = new Button("Select File"); // button to select file to be read

        // add instructions to how to use program
        Label instructions = new Label("Press button to select file & frequency of characters in file will be displayed");
        Label instructions1 = new Label("                                                                              ");
        Label instructions2 = new Label("                                                                              ");
        Label characterLabels = new Label("Character        ASCII Code     Frequency");
        
        // create label to display character frequencies
        Label charactersFrequencyDisplay = new Label();
        
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER); // center alignment of pane
        pane.add(SelectFileButton, 0, 0);  // position button
        GridPane.setHalignment(SelectFileButton, HPos.CENTER); // center alignment of button
        
        pane.add(instructions1, 0, 1); // add instructions
        pane.add(instructions2, 0, 3);
        pane.add(instructions, 0, 4);
        
        pane.add(characterLabels, 0, 5); // add labels for characters
        GridPane.setHalignment(characterLabels, HPos.CENTER); // center character labels
        
        pane.add(charactersFrequencyDisplay, 0, 6); // add character frequency display
        GridPane.setHalignment(SelectFileButton, HPos.CENTER); // center character frequenct display

       // if button is pressed, display character frequencies in file selected
        SelectFileButton.setOnAction (e -> {
        	// make the label blank at first
        	charactersFrequencyDisplay.setText("");
        	
        	// select file from file chosen through fileChooser
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            
            // if file exists at the specified path, display character frequencies
            if (selectedFile != null) {
            	// convert selectedFile to string
                String fileAsString = selectedFile.toString();
                
                // create file from fileAsString
                File initialFile = new File(fileAsString);
                try {
                	// launch input stream from the initial file
					InputStream targetStream = new FileInputStream(initialFile);
					BufferedInputStream in = new BufferedInputStream(targetStream);
					
					// create array list to store and display raw ASCII code for each character
				    ArrayList<Integer> ASCIIRawCharacters = new ArrayList<Integer>();
				    
				    // create counter named buffer to help read through and append input from file
			        int buffer;
			        
			        // read input and append ASCIIRawCharacters with input
			        while ((buffer = in.read()) != -1)
			        	ASCIIRawCharacters.add(buffer);
			        
			    	// close input stream
			        in.close();
			        
			        // transfer ASCIIRawCharacters elements to charArray
			    	int[] charArray = ASCIIRawCharacters.stream().mapToInt(i -> i).toArray();

			    	// clear ASCII CharactersList from usage with prior files
			    	ASCIICharactersList.removeAll(ASCIICharactersList);
	               
			    	// iterate through all elements of ASCIIRawCharacters
			        for (int i = 0; i < ASCIIRawCharacters.size(); i++) {
			        	
			        	// display character frequency of each character
			            if (ASCIIRawCharacters.get(i) != 0 && Collections.frequency(ASCIICharactersList, ASCIIRawCharacters.get(i)) == 0) {
			            	String chara =String.valueOf((char)charArray[i]); 
			            	ASCIICharactersList.add(ASCIIRawCharacters.get(i));
			            	String chara1 =String.valueOf(ASCIIRawCharacters.get(i)); 
			            	String chara2 =String.valueOf(Collections.frequency(ASCIIRawCharacters, ASCIIRawCharacters.get(i)));
			                charactersFrequencyDisplay.setText(charactersFrequencyDisplay.getText()+chara+"                 "+chara1+"                   "+chara2+"\n");
			                GridPane.setHalignment(charactersFrequencyDisplay, HPos.CENTER);
			            }
			        }
			    
			     // if file is not found, inform user
				} catch (FileNotFoundException e1) {
		        	charactersFrequencyDisplay.setText("FileNotFoundException occurred");
					
				// if IOException occurs, inform user
				} catch (IOException e2) {
		        	charactersFrequencyDisplay.setText("IOException occurred");

				}
                
            }
        });
        // set the scene
        Scene scene = new Scene(pane, 500, 500);
        
        // set and display the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
