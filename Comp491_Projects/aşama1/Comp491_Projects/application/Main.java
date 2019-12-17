package application;



import java.util.ArrayList;
import java.util.Random;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application{
	int pzv=0;
	double pstX, pstY;
	double en,boy, wayX, wayY;
	double offsetX, offsetY, orgSceneX, orgSceneY;
	double newTranslateX, newTranslateY, orgTranslateX, orgTranslateY;
	double x, y, width, height;
	double msf, shortest=999999;
	Rectangle rectangle;

	//Stones
	Text label;

	//Ara cizgi
	Line line;

	//Sayilarin tutuldugu yer
	ArrayList<Text> text;

	//Istakaya atilan taslar
	ArrayList<Text> p1;
	ArrayList<Text> p2;
	ArrayList<Text> p3;
	ArrayList<Text> p4;

	//atilacak Taslarin konuldugu yer
	ArrayList<Rectangle> P1_Drop = new ArrayList<>();

	//Dropped place
	ArrayList<Line> Plc_Drop= new ArrayList<>();

	ArrayList<Line> cizgi=new ArrayList<>();
	ArrayList<Line> FirstStr=new ArrayList<>();
	//	boolean [] cntrl;



	Paint defeat=Color.RED;
	Button btn;
	Button ctr;
	Group root;

	Button nxtply;
	@Override
	public void start(Stage primaryStage) {

		text=new ArrayList<>();

		root = new Group();

		double xcor=30.0f;
		double ycor=10.0f;
		double slide=71.0f;
		double Width= 40.0f;
		double Height= 50.0f;
		for(int a=1; a<107; a++) {

			if(a%13==0) {
				label =new Text(Integer.toString(13));
			}else if(a/13==8 && a%13!=0) {
				label =new Text(Integer.toString(0));
				label.setFill(Color.BLACK);
			}else {
				label =new Text(Integer.toString(a%13));
			}

			if(a>=1 && a<=26){
				label.setFill(Color.RED);
			}else if(a>=27 && a<=52){
				label.setFill(Color.GOLD);
			}else if(a>=53 && a<=79){
				label.setFill(Color.GREEN);
			}else if(a>=80 && a<=104){
				label.setFill(Color.BLUE);
			}

			label.setX(xcor+ Width/2); 
			label.setY(ycor+ Height/2); 


			label.setFont(Font.font("Verdana", 30));
			label.setOnMousePressed(labelpress);
			label.setOnMouseDragged(labeldragg);
			label.setOnMouseReleased(labelout);
			//Controller(p1, cizgi);
			//label.setOnMouseClicked(thrower);
			//System.out.println(label.getX()+" "+label.getY());
			text.add(label);

			if(a%13==0 && a/13>0) {
				xcor=30.0f;
				ycor+=50;
			}else {
				xcor+=slide;
			}
		}
		double recx=230;
		double recy=700;
		en=1200;
		boy=200;
		//Big Istakasa
		Rectangle rectangle1= new Rectangle(recx, recy, en, boy);
		rectangle1.setFill(Color.BURLYWOOD);
		rectangle1.setStroke(Color.BLACK);
		Rectangle rectangle2= new Rectangle(recx+1250, recy-610, boy-50, en-500);
		rectangle2.setFill(Color.BURLYWOOD);
		rectangle2.setStroke(Color.BLACK);
		Rectangle rectangle3= new Rectangle(recx, recy-650, en, boy);
		rectangle3.setFill(Color.BURLYWOOD);
		rectangle3.setStroke(Color.BLACK);
		Rectangle rectangle4= new Rectangle(recx-200, recy-610, boy-50, en-500);
		rectangle4.setFill(Color.BURLYWOOD);
		rectangle4.setStroke(Color.BLACK);
		//Tas koyma yeri 3.player
		for(int c=1; c<=3; c++ ) {
			Rectangle P3_Stones =new Rectangle(recx, 260,80, 100);
			P3_Stones.setFill(Color.WHITE);
			recx+=90;
			root.getChildren().add(P3_Stones);
		}
		recx=230;
		//Tas koyma yeri 4.player
		for(int c=1; c<=3; c++ ) {
			Rectangle P4_Stones =new Rectangle(recx, 560,80, 100);
			P4_Stones.setFill(Color.WHITE);

			recx+=90;
			root.getChildren().add(P4_Stones);
		}
		recx=230;
		//Tas koyma yeri 1.player
		for(int c=1; c<=3; c++ ) {
			Rectangle P1_Stones =new Rectangle(recx+900, 560,80, 100);
			P1_Stones.setFill(Color.WHITE);
			P1_Drop.add(P1_Stones);
			recx+=90;
			root.getChildren().add(P1_Stones);
		}
		recx=230;
		//Tas koyma yeri 2.player
		for(int c=1; c<=3; c++ ) {
			Rectangle P2_Stones =new Rectangle(recx+900, 260,80, 100);
			P2_Stones.setFill(Color.WHITE);
			recx+=90;

			root.getChildren().add(P2_Stones);
		}
		recx=230;
		line= new Line(recx,recy+boy/2,recx+en,recy+boy/2);

		root.getChildren().addAll(rectangle1,rectangle2,rectangle3,rectangle4, line);
		for(int l=1; l<=30; l++) {

			line= new Line();
			//1.satir
			if(l%16==0 && l/16!=0) {
				recx=230;
				recy+=100;
				line.setStartX(recx);
				line.setStartY(recy);
				line.setEndX(recx);
				line.setEndY(recy+100);

			}else {
				line.setStartX(recx);
				line.setStartY(recy);
				line.setEndX(recx);
				line.setEndY(recy+100);
			}
			int id=l-1;
			line.setId(Integer.toString(id));
			cizgi.add(line);
			root.getChildren().add(line);
			recx+=80;
		}
		//System.out.println(cizgi.size());
		btn = new Button("Let's Start"); 
		btn.setOnAction(event);
		btn.setLayoutX(700);
		btn.setLayoutY(400);
		btn.setPrefWidth(200);
		btn.setPrefHeight(200);
		root.getChildren().add(btn);


		/*ctr = new Button("Controller"); 
		ctr.setOnAction(Series_Control);
		ctr.setLayoutX(700);
		ctr.setLayoutY(400);
		ctr.setPrefWidth(100);
		ctr.setPrefHeight(100);*/

		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 1700,1000, Color.CHARTREUSE));

		primaryStage.setTitle("This Game real Name is= 183 game");
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) 
		{ 
			Random rnd = new Random();
			//	 cntrl=new boolean[30];
			root.getChildren().remove(btn);
			p1= new ArrayList<>();
			p2= new ArrayList<>();
			p3= new ArrayList<>();
			p4= new ArrayList<>();


			for(int b=0; b<=70; b++) {
				int stone= rnd.nextInt(text.size());
				if(b<=19) {
					text.get(stone).setX(cizgi.get(b).getStartX()+30 );
					text.get(stone).setY(cizgi.get(b).getStartY()+40 );
					//	busy.add(cizgi.get(b));

					cizgi.get(b).setStroke(Color.RED); //public final void setStroke(Paint value)

					p1.add(text.get(stone));
					root.getChildren().add(p1.get(b));
					//cntrl[b]=true;
					//Plc_Drop.add(cizgi.get(0));

					//cizgi.remove(0);


				}
				else if(20<=b && b<=36) {
					p2.add(text.get(stone));
				}else if(37<=b && b<=53) {
					p3.add(text.get(stone));
				} else if(54<=b) {
					p4.add(text.get(stone));
				}

				//  root.getChildren().add(label);
				//root.getChildren().addAll(rent.get(b), text.get(b));

				text.remove(stone);

				//   root.getChildren().add(rent.get(b));
				// System.out.println(rent.get);
			}
			System.out.println(cizgi.get(25).getStroke());
			System.out.println(cizgi.get(5).getStroke());

			//for(int h=0; h<cntrl.length; h++) {
			//System.out.println(cntrl[h]);
			//	}
			//System.out.println(mark);
			//System.out.println(mark.size());
			/*	System.out.print("p1= ");
			for(Text elem : p1){
				System.out.print(elem.getText()+"  ");
			}
			System.out.println();
			System.out.print("p2= ");
			for(Text elem : p2){
				System.out.print(elem.getText()+"  ");
			}
			System.out.println();

			System.out.print("p3= ");
			for(Text elem : p3){
				System.out.print(elem.getText()+"  ");
			}
			System.out.println();

			System.out.print("p4= ");
			for(Text elem : p4){
				System.out.print(elem.getText()+"  ");
			}
			 */
		} 
	}; 
	EventHandler<MouseEvent> labelpress = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {


			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((Text)(t.getSource())).getTranslateX();
			orgTranslateY = ((Text)(t.getSource())).getTranslateY();
			//System.out.println(orgSceneX+" "+orgSceneY);
			for(int ln=0; ln<cizgi.size(); ln++) {

				wayX=orgSceneX-cizgi.get(ln).getStartX();
				wayY=orgSceneY-cizgi.get(ln).getStartY();
				msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));

				if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {

					pzv=ln;

					break;

				}else if(msf<=shortest) {
					shortest=msf;
					pzv=ln;

				}


			}
			System.out.println(cizgi.get(pzv).getId()+" çizgi Using"+cizgi.get(pzv).getStartX()+" "+cizgi.get(pzv).getStartY());

		}
	};

	EventHandler<MouseEvent> labeldragg = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {

			offsetX = t.getSceneX() - orgSceneX;
			offsetY = t.getSceneY() - orgSceneY; 
			newTranslateX = orgTranslateX + offsetX;
			newTranslateY = orgTranslateY + offsetY;

			((Text)(t.getSource())).setTranslateX(newTranslateX);
			((Text)(t.getSource())).setTranslateY(newTranslateY);


		}
	};

	EventHandler<MouseEvent> labelout = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double prspointX=t.getSceneX();
			double prspointY=t.getSceneY();
			double ne=5;
			boolean maker=false;
			int shrt = 0;

			double shortest=999999;
			msf=0;

			for(int ln=0; ln<cizgi.size(); ln++) {

				wayX=prspointX-cizgi.get(ln).getStartX();
				wayY=prspointY-cizgi.get(ln).getStartY();
				msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));

				if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
					pstX=cizgi.get(ln).getStartX()-newTranslateX;
					pstY=cizgi.get(ln).getStartY()-newTranslateY;
					if(cizgi.get(ln).getStroke().equals(defeat)) {
						System.out.println("dolu");
						pstX=cizgi.get(pzv).getStartX()-newTranslateX;
						pstY=cizgi.get(pzv).getStartY()-newTranslateY;
						ne=0;
						maker=true;
						break;
					}else {
						System.out.println("buradasýn");
						((Text)(t.getSource())).setX((pstX +30));
						((Text)(t.getSource())).setY((pstY+ 40));
						maker=false;
						ne=1;
						shrt=ln;

						break;
					}
				}else if(msf<=shortest) {
					shortest=msf;
					pstX=cizgi.get(ln).getStartX()-newTranslateX;
					pstY=cizgi.get(ln).getStartY()-newTranslateY;

					if(cizgi.get(ln).getStroke().equals(defeat)) {
						System.out.println("var birþeyler");
						pstX=cizgi.get(pzv).getStartX()-newTranslateX;
						pstY=cizgi.get(pzv).getStartY()-newTranslateY;
						ne=0;
						maker=true;
					}else {
						shrt=ln;
						ne=-1;
						maker=false;
					}

					//cizgi.get(ln).setStroke(Color.RED);
				}
				//cizgi.get(ln).setStroke(Color.RED);

			}
			((Text)(t.getSource())).setX((pstX+30));
			((Text)(t.getSource())).setY((pstY+40));
			if(maker==false) {
			
			}
			System.out.println(cizgi.get(shrt).getStroke());
			if(ne==1) {
				System.out.println("yeni kutunun içine girdi");
				cizgi.get(shrt).setStroke(Color.RED);
				cizgi.get(pzv).setStroke(Color.BLACK);
			}else if(ne==-1) {
				System.out.println("uzak mesafeden geldi");
				cizgi.get(shrt).setStroke(Color.RED);
				cizgi.get(pzv).setStroke(Color.BLACK);
			}else if(ne==0) {
				System.out.println("ayný kutuya döndü");
			}else {
				System.out.println("cafer aða");
			}
		
			System.out.println("added="+cizgi.get(shrt));
			//Plc_Drop.add(cizgi.get(shrt));
			//System.out.println("added="+cizgi.get(shrt));
			//cizgi.remove(cizgi.get(shrt));
			//System.out.println("number of Not empty places are "+Plc_Drop.size());


		}	
	};

	EventHandler<MouseEvent> thrower = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			MouseButton button = t.getButton();
			if(button==MouseButton.SECONDARY){

				double prspointX=t.getSceneX();
				double prspointY=t.getSceneY();
				double pstX=0;
				double pstY=0;
				double shortest=999999;
				double msf=0;
				int chs=0;
				for(int ln=0; ln<P1_Drop.size(); ln++) {
					wayX=prspointX-P1_Drop.get(ln).getX();
					wayY=prspointY-P1_Drop.get(ln).getY();


					msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));
					if(msf<=shortest) {
						shortest=msf;
						pstX=P1_Drop.get(ln).getX()-newTranslateX;
						pstY=P1_Drop.get(ln).getY()-newTranslateY;
						chs=ln;
					}
				}
				P1_Drop.get(chs).setFill(Color.DARKGRAY);

				P1_Drop.remove(chs);
				//System.out.println(rnt.get(chs).getFill());
				((Text)(t.getSource())).setX((pstX +30));
				((Text)(t.getSource())).setY((pstY+ 40));
				//System.out.println("SECONDARY button clicked");   
				if(P1_Drop.isEmpty()) {
					nxtply = new Button("CONTINUE");
					nxtply.setOnAction(hiza);
					nxtply.setLayoutX(700);
					nxtply.setLayoutY(400);
					nxtply.setPrefWidth(200);
					nxtply.setPrefHeight(200);
					root.getChildren().add(nxtply);
				}
			}else if(button==MouseButton.MIDDLE){
				System.out.println("MIDDLE button clicked");


			}
		}
	};
	/*
	EventHandler<ActionEvent> siradan = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) {
			System.out.println("next player time");
			root.getChildren().remove(nxtply);

		}
	};*/
	EventHandler<ActionEvent> hiza = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) {
			Controller(p1, Plc_Drop);
			root.getChildren().remove(nxtply);

		}
	};
	private void Controller(ArrayList<Text> nums, ArrayList<Line> places) {

		for(int m=0; m<places.size(); m++) {
			int a=0;
			//System.out.println("for point "+m);
			for(int n=0; n<nums.size(); n++) {
				wayX=nums.get(n).getX()-places.get(m).getStartX();
				wayY=nums.get(n).getY()-places.get(m).getStartY();
				msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));

				if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
					System.out.println(nums.get(n).getText()+" holding point "+places.get(m).getStartX()+" & "+places.get(m).getStartY());
					break;
				}else {
					a++;

				}
			}
			System.out.println(a+" element adding");
		}

	}
}