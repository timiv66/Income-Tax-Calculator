package tax;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class TaxFX extends Application{
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}
	
	@Override
	public void start(Stage mainStage) throws Exception {
		// setting scene f
		Pane p1 = new Pane();
		Scene t = new Scene(p1,420,350);
		t.setFill(Color.BLUE);
		t.setRoot(app(t));
		Image icon = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnFpKmdkPy1ThQ50M-aPNIg7NV3KP7qnKqTw&usqp=CAU");
		mainStage.getIcons().add(icon);
		mainStage.setTitle("2023 Income Tax Calculator");
		mainStage.setScene(t);
		mainStage.show();	
	}
	
	public Pane app(Scene t) {
		Filer newFiler = new Filer();
		
		Font myFont = new Font("Impact",19);
		Font txtfFont = new Font("Arial", 12);
		DropShadow shadow = new DropShadow();
		
		UnaryOperator<TextFormatter.Change> filter = change -> {
           String text = change.getText();
           // Check if the new text is a number or empty
           if (text.matches("[0-9]*") || text.isEmpty()) {
               return change;
           }
           // If not a number or empty, reject the change
           return null;
       };
       TextFormatter<Integer> textFormatter1 = new TextFormatter<>(new IntegerStringConverter(), null, filter);
       TextFormatter<Integer> textFormatter2 = new TextFormatter<>(new IntegerStringConverter(), null, filter);
		
		Label statusLbl = new Label("Filing Status:");
		statusLbl.setFont(myFont);
		statusLbl.setTranslateX(5);
		statusLbl.setTranslateY(10);
		
		ChoiceBox<String> cb1 = new ChoiceBox<String>();//choice box for status
		cb1.getItems().add("Single");
		cb1.getItems().add("Married File Separately");
		cb1.getItems().add("Married File Together");
		cb1.getItems().add("Head of Household");
		cb1.setTranslateX(108);
		cb1.setTranslateY(11);
		cb1.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");
		
		Label incomeLbl = new Label("Annual Income:");
		incomeLbl.setFont(myFont);
		incomeLbl.setTranslateX(5);
		incomeLbl.setTranslateY(50);
		
		Label moneyLbl = new Label("$");
		moneyLbl.setFont(myFont);
		moneyLbl.setTranslateX(128);
		moneyLbl.setTranslateY(51);
		
		TextField incomeTxtF = new TextField();
		incomeTxtF.setTranslateX(141);
		incomeTxtF.setTranslateY(51);
		incomeTxtF.setFont(txtfFont);
       // Set the TextFormatter to the TextField
       incomeTxtF.setTextFormatter(textFormatter1);
      
		Label totalTaxCred = new Label("Total Tax Credits: $");
		totalTaxCred.setFont(myFont);
		totalTaxCred.setTranslateX(5);
		totalTaxCred.setTranslateY(90);
		
		TextField taxCredTxtF = new TextField();
		taxCredTxtF.setTranslateX(190);
		taxCredTxtF.setTranslateY(92);
		taxCredTxtF.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");
		
		Label atlDeductionLbl = new Label("Above-the-line Deductions:");
		atlDeductionLbl.setFont(myFont);
		atlDeductionLbl.setTranslateX(5);
		atlDeductionLbl.setTranslateY(129);
		
		TextField atlDedTxtF = new TextField();
		atlDedTxtF.setTranslateX(238);
		atlDedTxtF.setTranslateY(131);
		atlDedTxtF.setTextFormatter(textFormatter2);
		atlDedTxtF.setFont(txtfFont);
		
		Label moneyLb2 = new Label("-$");
		moneyLb2.setFont(myFont);
		moneyLb2.setTranslateX(219);
		moneyLb2.setTranslateY(130);
		
		Label itmDed = new Label("Itemized Deductions:");
		itmDed.setFont(myFont);
		itmDed.setTranslateX(5);
		itmDed.setTranslateY(170);
		
		Label moneyLb3 = new Label("-$");
		moneyLb3.setFont(myFont);
		moneyLb3.setTranslateX(247);
		moneyLb3.setTranslateY(170);
		moneyLb3.setVisible(false);
		
		TextField itmDedTxtF = new TextField();
		itmDedTxtF.setTranslateX(267);
		itmDedTxtF.setTranslateY(170);
		itmDedTxtF.setVisible(false);
		itmDedTxtF.setFont(txtfFont);
		
		Button yesBtn = new Button("Yes");
		yesBtn.setTranslateX(170);
		yesBtn.setTranslateY(170);
		
		Button noBtn = new Button("No");
		noBtn.setTranslateX(210);
		noBtn.setTranslateY(170);
		
		
		//Yes button actions
		yesBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				moneyLb3.setVisible(true);
				itmDedTxtF.setVisible(true);
				noBtn.setDisable(true);
			}
		});
		yesBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  yesBtn.setEffect(shadow);
	          }
	        });
		yesBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  yesBtn.setEffect(null);
	          }
	        });
		
		//No button actions
		noBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
			itmDedTxtF.setText("0");
			yesBtn.setDisable(true);
			}
		});
		noBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  noBtn.setEffect(shadow);
	          }
	        });
		noBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  noBtn.setEffect(null);
	          }
	        });
		
		Label nameLbl = new Label("Name:");
		nameLbl.setFont(myFont);
		nameLbl.setTranslateX(5);
		nameLbl.setTranslateY(203);
		
		TextField nameTxtF = new TextField();
		nameTxtF.setTranslateX(60);
		nameTxtF.setTranslateY(204);
		nameTxtF.setFont(txtfFont);
		
		Text errorMsg = new Text("");
		errorMsg.setVisible(false);
		errorMsg.setX(10);
		errorMsg.setY(370);
		errorMsg.setFill(Color.RED);
		errorMsg.setFont(txtfFont);
		
		Text agiTxt = new Text("");
		agiTxt.setX(10);
		agiTxt.setY(370);
		agiTxt.setVisible(false);
		agiTxt.setFont(txtfFont);
		
		Text taxableIncTxt = new Text("");
		taxableIncTxt.setX(10);
		taxableIncTxt.setY(390);
		taxableIncTxt.setVisible(false);
		taxableIncTxt.setFont(txtfFont);
		
		Text grossTaxLibTxt = new Text("");
		grossTaxLibTxt.setX(10);
		grossTaxLibTxt.setY(410);
		grossTaxLibTxt.setVisible(false);
		grossTaxLibTxt.setFont(txtfFont);
		
		
		Text taxDorRTxt = new Text("");
		taxDorRTxt.setX(10);
		taxDorRTxt.setY(430);
		taxDorRTxt.setVisible(false);
		taxDorRTxt.setFont(txtfFont);
		
		ImageView moneyImg = new ImageView("https://cdn1.i-scmp.com/sites/default/files/styles/1200x800/public/images/methode/2019/03/01/2a632684-3c00-11e9-a334-8d034d5595df_image_hires_182228.jpg?itok=hXgwquyJ&v=1551435753");
		moneyImg.setFitHeight(80);
		moneyImg.setFitWidth(120);
		moneyImg.setX(290);
		moneyImg.setY(265);
     
		Button calcBtn = new Button("Calculate Income Tax");
		calcBtn.setTranslateX(135);
		calcBtn.setTranslateY(320);
		
		calcBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent args0) {
				
		       try {
		    	   errorMsg.setVisible(false);
		    	   t.getWindow().setHeight(550);
		    	   
		    	   
		    	   newFiler.setAnnualIncome(Double.parseDouble(incomeTxtF.getText()));
		    	   newFiler.setItmdeductions(Double.parseDouble(itmDedTxtF.getText()));
		    	   newFiler.setAtldeductions(Double.parseDouble(atlDedTxtF.getText()));
		    	   newFiler.setStatus((String) cb1.getValue());
		    	   newFiler.setTaxCredits(Double.parseDouble(taxCredTxtF.getText()));
		    	   newFiler.setName(nameTxtF.getText());
		    	   
		    	   //Calculate the AGI
		    	   agiTxt.setText("Your Adjusted Gross Income(AGI) is: $" + newFiler.calcAGI());
		    	   agiTxt.setVisible(true);
		    	   
		    	   //Calculate total deductions
		    	   taxableIncTxt.setText("Your taxable income is: $" + newFiler.calcTaxIncome());
		    	   taxableIncTxt.setVisible(true);
		    	   
		    	   //Calculate gross tax liability
		    	   grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + newFiler.calcGrossTax());
		    	   grossTaxLibTxt.setVisible(true);
		    	   
		    	   //Calculate tax refund or tax due
		    	   if (newFiler.calctaxDueorRef() < 0) {
		    		   taxDorRTxt.setText("Your tax refund is : $" + Math.abs(newFiler.calctaxDueorRef()));
		    		   taxDorRTxt.setVisible(true);
		    	   }else {
		    		   taxDorRTxt.setText("Your taxes due is : $" + newFiler.calctaxDueorRef());
		    		   taxDorRTxt.setVisible(true);
		    	   }
		    
		         }catch(Exception e) {
		        	 errorMsg.setText("Please fill in all answer boxes");
		        	 errorMsg.setVisible(true);
		          }	      
				}	
			});
		           
		calcBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		            calcBtn.setEffect(shadow);
		          }
		        });
	
		 calcBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		            calcBtn.setEffect(null);
		          }
		        });
		   
		 Button resetBtn = new Button("Reset");
		 resetBtn.setTranslateX(370);
		 resetBtn.setTranslateY(5);
		
		 //reset button actions
		 resetBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		            resetBtn.setEffect(shadow);
		          }
		        });
	     resetBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		           resetBtn.setEffect(null);
		          }
		        });
		  resetBtn.setOnAction(new EventHandler <ActionEvent>() {//resets all fields
				@Override
				public void handle(ActionEvent arg0) {
					t.getWindow().setHeight(389);
					cb1.setValue(null);
					incomeTxtF.setText("");
					taxCredTxtF.setText("");
					atlDedTxtF.setText("");
					nameTxtF.setText("");
					moneyLb3.setVisible(false);
					itmDedTxtF.setVisible(false);
					itmDedTxtF.setText("");
					agiTxt.setVisible(false);
					taxableIncTxt.setVisible(false);
					grossTaxLibTxt.setVisible(false);
					
					taxDorRTxt.setVisible(false);
					yesBtn.setDisable(false);
					noBtn.setDisable(false);
				}
			});
			
			Button saveBtn = new Button("Save");
			saveBtn.setTranslateX(5);
			saveBtn.setTranslateY(470);
			
			saveBtn.setOnAction(new EventHandler <ActionEvent>() {//saves tax info in to file
				@Override
				public void handle(ActionEvent arg0) {
					appendDataToFile(newFiler);
				}
			});
			
		    saveBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent e) {
		        saveBtn.setEffect(shadow);
		        }
		    });
	
	        saveBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent e) {
		        saveBtn.setEffect(null);
		        }
		    });
			
			Pane appPane = new Pane();
			appPane.getChildren().addAll(statusLbl,cb1,incomeLbl,incomeTxtF,moneyLbl,totalTaxCred,taxCredTxtF,atlDeductionLbl,
					atlDedTxtF,moneyLb2,nameLbl,nameTxtF,calcBtn,agiTxt,taxableIncTxt,grossTaxLibTxt,resetBtn,saveBtn,
					errorMsg,taxDorRTxt,itmDed,moneyLb3,yesBtn,noBtn,itmDedTxtF,moneyImg);
			return appPane;
			
		
	}
	
	public void appendDataToFile(Filer filer) {
		
        FileChooser fileChooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                // Append data to the file
               bw.write("Name:"+ filer.getName() + ", AGI:$"+ filer.calcAGI() + ", Taxable Income:$" + filer.calcTaxIncome() + ", Gross Tax Liability:$"+filer.calcGrossTax() 
            		   + ", Tax Credit:$" + filer.getTaxCredits() + ", Taxes due or refunded:$" + filer.calctaxDueorRef());
               bw.newLine();
               bw.newLine();
               System.out.println("Data appended to: " + file.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	
}

