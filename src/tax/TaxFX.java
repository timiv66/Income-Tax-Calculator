package tax;
import java.util.ArrayList;
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
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class TaxFX extends Application{
	
	ArrayList<String> statusList = new ArrayList<>();
	double taxableInc;
	DropShadow shadow = new DropShadow();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}
	@Override
	public void start(Stage mainStage) throws Exception {
		// TODO Auto-generated method stub
		Pane p1 = new Pane();
		Pane p2 = new Pane();
		Scene t = new Scene(p1,300,350);
		t.setRoot(app(t));
		mainStage.setTitle("Income Tax Calculator");
		mainStage.setScene(t);
		mainStage.show();
		
		
		
		
			
	}
	public Pane app(Scene t) {
		
	
		Font myFont = new Font("Impact",20);
		Font txtfFont = new Font("Arial", 12);
		
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
		
		ChoiceBox<String> cb = new ChoiceBox<String>();
		cb.getItems().add("Single");
		cb.getItems().add("Married File Separately");
		cb.getItems().add("Married File Together");
		cb.getItems().add("Head of Household");
		cb.setTranslateX(115);
		cb.setTranslateY(11);
		cb.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");
		
		Label incomeLbl = new Label("Annual Income:");
		incomeLbl.setFont(myFont);
		incomeLbl.setTranslateX(5);
		incomeLbl.setTranslateY(50);
		
		Label moneyLbl = new Label("$");
		moneyLbl.setFont(myFont);
		moneyLbl.setTranslateX(5);
		moneyLbl.setTranslateY(83);
		
		TextField incomeTxtF = new TextField();
		incomeTxtF.setTranslateX(20);
		incomeTxtF.setTranslateY(83);
		incomeTxtF.setFont(txtfFont);
		
       // Set the TextFormatter to the TextField
       incomeTxtF.setTextFormatter(textFormatter1);
      
		
		Label numOfDepLbl = new Label("Number of Dependents:");
		numOfDepLbl.setFont(myFont);
		numOfDepLbl.setTranslateX(5);
		numOfDepLbl.setTranslateY(120);
		
		
		ChoiceBox<Integer> cb2 = new ChoiceBox<Integer>();
		cb2.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10);
		cb2.setTranslateX(5);
		cb2.setTranslateY(145);
		cb2.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");
		
		Label deductionLbl = new Label("Deductions:");
		deductionLbl.setFont(myFont);
		deductionLbl.setTranslateX(5);
		deductionLbl.setTranslateY(180);
		
		TextField dedTxtF = new TextField();
		dedTxtF.setTranslateX(27);
		dedTxtF.setTranslateY(205);
		dedTxtF.setTextFormatter(textFormatter2);
		dedTxtF.setFont(txtfFont);
		
		Label moneyLb2 = new Label("-$");
		moneyLb2.setFont(myFont);
		moneyLb2.setTranslateX(5);
		moneyLb2.setTranslateY(203);
		
		Label nameLbl = new Label("Name");
		
		Text errorMsg = new Text("");
		errorMsg.setVisible(false);
		errorMsg.setX(10);
		errorMsg.setY(370);
		errorMsg.setFill(Color.RED);
		
		Text agiTxt = new Text("");
		agiTxt.setX(10);
		agiTxt.setY(370);
		agiTxt.setVisible(false);
		
		Text taxableIncTxt = new Text("");
		taxableIncTxt.setX(10);
		taxableIncTxt.setY(390);
		taxableIncTxt.setVisible(false);
		
		Text grossTaxLibTxt = new Text("");
		grossTaxLibTxt.setX(10);
		grossTaxLibTxt.setY(410);
		grossTaxLibTxt.setVisible(false);
		
		Text finalAmt = new Text("");
		finalAmt.setX(10);
		finalAmt.setY(430);
       finalAmt.setVisible(false);
     
		Button calcBtn = new Button("Calculate Income Tax");
		calcBtn.setTranslateX(80);
		calcBtn.setTranslateY(320);
		
		calcBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent args0) {
				
		       try {
		    	   t.getWindow().setHeight(550);
					String statVal = (String) cb.getValue();//gets value from status cb
					double incomeVal = Double.parseDouble(incomeTxtF.getText());//gets value from income txt field
					int depVal = (Integer) cb2.getValue();//gets value from dependents cb
					double deducVal = Double.parseDouble(dedTxtF.getText());//gets value from deduction txt field
					
					double AGI = incomeVal-deducVal;//Calculating AGI
					agiTxt.setText("Your Adjusted Gross Income(AGI) is: $" + AGI);
					agiTxt.setVisible(true);
					
					//Calculating taxable income
					if (statVal.matches("Single") || statVal.matches("Married File Separately")) {
						taxableInc=AGI-13850;
						taxableIncTxt.setText("Your taxable income is: $" + taxableInc);
						taxableIncTxt.setVisible(true);
					}
					else if (statVal.matches("Married File Together")) {
						taxableInc=AGI-27700;
						taxableIncTxt.setText("Your taxable income is: $" + taxableInc);
						taxableIncTxt.setVisible(true);	
					}
					else if (statVal.matches("Head of Household")) {
						taxableInc=AGI-20800;
						taxableIncTxt.setText("Your taxable income is: $" + taxableInc);
						taxableIncTxt.setVisible(true);
					}
					
					//Calculating gross tax liability
					double grossTaxLib;
					switch (statVal) {
						case "Single"://gross tax liability for single
							if(taxableInc>0 && taxableInc<=11000) {
								grossTaxLib=taxableInc*.10;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>11000 && taxableInc<=44725) {
								grossTaxLib=taxableInc*.12;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>44725 && taxableInc<=95375) {
								grossTaxLib=taxableInc*.22;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>95375 && taxableInc<=182100) {
								grossTaxLib=taxableInc*.24;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>182100 && taxableInc<=231250) {
								grossTaxLib=taxableInc*.32;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>231250 && taxableInc<=578125) {
								grossTaxLib=taxableInc*.35;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>578125) {
								grossTaxLib=taxableInc*.37;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							break;
							
						case"Married File Separately"://gross tax liability for Married File Separately
							if(taxableInc>0 && taxableInc<=11000) {
								grossTaxLib=taxableInc*.10;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>11000 && taxableInc<=44725) {
								grossTaxLib=taxableInc*.12;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>44725 && taxableInc<=95375) {
								grossTaxLib=taxableInc*.22;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>95375 && taxableInc<=182100) {
								grossTaxLib=taxableInc*.24;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>182100 && taxableInc<=231250) {
								grossTaxLib=taxableInc*.32;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>231250 && taxableInc<=346875) {
								grossTaxLib=taxableInc*.35;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>346875) {
								grossTaxLib=taxableInc*.37;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							break;
							
						case"Married File Together"://gross tax liability for Married File Separately Together
							if(taxableInc>0 && taxableInc<=22000) {
								grossTaxLib=taxableInc*.10;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>22000 && taxableInc<=89450) {
								grossTaxLib=taxableInc*.12;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>89450 && taxableInc<=190750) {
								grossTaxLib=taxableInc*.22;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>190750 && taxableInc<=364200) {
								grossTaxLib=taxableInc*.24;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>364200 && taxableInc<=462500) {
								grossTaxLib=taxableInc*.32;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>462500 && taxableInc<=693750) {
								grossTaxLib=taxableInc*.35;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>693750) {
								grossTaxLib=taxableInc*.37;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							break;
							
						case"Head of Household"://gross tax liability for Head of Household
							if(taxableInc>0 && taxableInc<=15700) {
								grossTaxLib=taxableInc*.10;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>15700 && taxableInc<=59850) {
								grossTaxLib=taxableInc*.12;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>59850 && taxableInc<=95350) {
								grossTaxLib=taxableInc*.22;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>95350 && taxableInc<=182100) {
								grossTaxLib=taxableInc*.24;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>182100 && taxableInc<=231250) {
								grossTaxLib=taxableInc*.32;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>231250 && taxableInc<=578100) {
								grossTaxLib=taxableInc*.35;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							else if(taxableInc>578100) {
								grossTaxLib=taxableInc*.37;
								grossTaxLibTxt.setText("Your Gross Tax Liability is: $" + grossTaxLib);
								grossTaxLibTxt.setVisible(true);
							}
							break;	
					}
					double taxCredit;
					
					
	
		         }catch(Exception e) {
		        	 errorMsg.setText("Pleas enter valid numbers only");
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
			resetBtn.setTranslateX(245);
			resetBtn.setTranslateY(470);
			
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
			
			resetBtn.setOnAction(new EventHandler <ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					cb.setValue(null);
					incomeTxtF.setText("");
					cb2.setValue(null);
					dedTxtF.setText("");
					agiTxt.setVisible(false);
					taxableIncTxt.setVisible(false);
					grossTaxLibTxt.setVisible(false);
				}
				
			});
			
			Pane appPane = new Pane();
			appPane.getChildren().addAll(statusLbl,cb,incomeLbl,incomeTxtF,moneyLbl,numOfDepLbl,cb2,deductionLbl,
					dedTxtF,moneyLb2,calcBtn,agiTxt,taxableIncTxt,grossTaxLibTxt,resetBtn,errorMsg);
			return appPane;
			
		
	}
	
	
	
	
}

