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
	
	
	double taxableInc;
	double AGI;
	double grossTaxLib;
	
	DropShadow shadow = new DropShadow();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}
	@Override
	public void start(Stage mainStage) throws Exception {
		// setting scene f
		Pane p1 = new Pane();
		Scene t = new Scene(p1,320,350);
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
		
		ChoiceBox<String> cb1 = new ChoiceBox<String>();
		cb1.getItems().add("Single");
		cb1.getItems().add("Married File Separately");
		cb1.getItems().add("Married File Together");
		cb1.getItems().add("Head of Household");
		cb1.setTranslateX(115);
		cb1.setTranslateY(11);
		cb1.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");
		
		Label incomeLbl = new Label("Annual Income:");
		incomeLbl.setFont(myFont);
		incomeLbl.setTranslateX(5);
		incomeLbl.setTranslateY(50);
		
		Label moneyLbl = new Label("$");
		moneyLbl.setFont(myFont);
		moneyLbl.setTranslateX(133);
		moneyLbl.setTranslateY(51);
		
		TextField incomeTxtF = new TextField();
		incomeTxtF.setTranslateX(145);
		incomeTxtF.setTranslateY(51);
		incomeTxtF.setFont(txtfFont);
		
       // Set the TextFormatter to the TextField
       incomeTxtF.setTextFormatter(textFormatter1);
      
		
		Label numOfDepLbl = new Label("Number of Dependents:");
		numOfDepLbl.setFont(myFont);
		numOfDepLbl.setTranslateX(5);
		numOfDepLbl.setTranslateY(90);
		
		
		ChoiceBox<Integer> cb2 = new ChoiceBox<Integer>();
		cb2.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10);
		cb2.setTranslateX(200);
		cb2.setTranslateY(93);
		cb2.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");
		
		Label deductionLbl = new Label("Deductions:");
		deductionLbl.setFont(myFont);
		deductionLbl.setTranslateX(5);
		deductionLbl.setTranslateY(133);
		
		TextField dedTxtF = new TextField();
		dedTxtF.setTranslateX(125);
		dedTxtF.setTranslateY(135);
		dedTxtF.setTextFormatter(textFormatter2);
		dedTxtF.setFont(txtfFont);
		
		Label moneyLb2 = new Label("-$");
		moneyLb2.setFont(myFont);
		moneyLb2.setTranslateX(105);
		moneyLb2.setTranslateY(135);
		
		Label nameLbl = new Label("Name:");
		nameLbl.setFont(myFont);
		nameLbl.setTranslateX(5);
		nameLbl.setTranslateY(173);
		
		TextField nameTxtF = new TextField();
		nameTxtF.setTranslateX(60);
		nameTxtF.setTranslateY(174);
		
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
     
		Button calcBtn = new Button("Calculate Income Tax");
		calcBtn.setTranslateX(80);
		calcBtn.setTranslateY(320);
		
		calcBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent args0) {
				
		       try {
		    	   t.getWindow().setHeight(550);
					String statVal = (String) cb1.getValue();//gets value from status cb
					double incomeVal = Double.parseDouble(incomeTxtF.getText());//gets value from income txt field
					int depVal = (Integer) cb2.getValue();//gets value from dependents cb
					double deducVal = Double.parseDouble(dedTxtF.getText());//gets value from deduction txt field
					
					AGI = incomeVal-deducVal;//Calculating AGI
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
			resetBtn.setTranslateX(270);
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
			
			resetBtn.setOnAction(new EventHandler <ActionEvent>() {//resets all fields
				@Override
				public void handle(ActionEvent arg0) {
					t.getWindow().setHeight(400);
					cb1.setValue(null);
					incomeTxtF.setText("");
					cb2.setValue(null);
					dedTxtF.setText("");
					nameTxtF.setText("");
					agiTxt.setVisible(false);
					taxableIncTxt.setVisible(false);
					grossTaxLibTxt.setVisible(false);
				}
				
			});
			
			Button saveBtn = new Button("Save");
			saveBtn.setTranslateX(5);
			saveBtn.setTranslateY(470);
			
			saveBtn.setOnAction(new EventHandler <ActionEvent>() {//saves tax info in to file
				@Override
				public void handle(ActionEvent arg0) {
					try {
						FileWriter fw = new FileWriter("C:\\Users\\timiv\\OneDrive\\Documents\\Git Projects\\Income-Tax-Calculator\\src\\tax\\tax info.txt");
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(nameTxtF.getText()+ " AGI: " + AGI +", Taxable Income: " + taxableInc + ", Gross Tax Liability: " + grossTaxLib);
						bw.newLine();
						bw.close();
						fw.close();
					}catch(Exception e) {
						System.out.println("Save button did not work");
					}
					
				}
				
			});
			
			Pane appPane = new Pane();
			appPane.getChildren().addAll(statusLbl,cb1,incomeLbl,incomeTxtF,moneyLbl,numOfDepLbl,cb2,deductionLbl,
					dedTxtF,moneyLb2,nameLbl,nameTxtF,calcBtn,agiTxt,taxableIncTxt,grossTaxLibTxt,resetBtn,saveBtn,errorMsg);
			return appPane;
			
		
	}
	
	
	
	
}

