package application;


import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{

	double en,boy;
	double offsetX, offsetY;
	double newTranslateX, newTranslateY;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	double x, y, width, height;
	Rectangle rectangle;
	Text label;
	Line line;
	ArrayList<Text> text;
	ArrayList<Text> p1;
	ArrayList<Text> p2;
	ArrayList<Text> p3;
	ArrayList<Text> p4;
	ArrayList<Line> cizgi;

	ArrayList<Pointer> pnt= new ArrayList<>();
	//ArrayList<Line> cp = new ArrayList<>(); 
	combine cmp;
	Button btn;
	Group root;
	Button combiner;
	@Override
	public void start(Stage primaryStage) {

		btn = new Button("Let's Start"); 
		btn.setOnAction(event);
		btn.setLayoutX(700);
		btn.setLayoutY(400);
		btn.setPrefWidth(200);
		btn.setPrefHeight(200);

		//	ArrayList<Rectangle> rent=new ArrayList<>();
		text=new ArrayList<>();
		cizgi=new ArrayList<>();
		root = new Group();

		double xcor=30.0f;
		double ycor=10.0f;
		double slide=71.0f;
		double Width= 40.0f;
		double Height= 50.0f;
		for(int a=1; a<107; a++) {
			cmp= new combine();
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
			cmp.setChX(label.getX());
			cmp.setChY(label.getY());
			cmp.setBusy(false);

			label.setFont(Font.font("Verdana", 30));
			label.setOnMousePressed(labelpress);
			label.setOnMouseDragged(labeldragg);
			label.setOnMouseReleased(labelout);
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
		Rectangle rectangle1= new Rectangle(recx, recy, en, boy);
		rectangle1.setFill(Color.BURLYWOOD);
		rectangle1.setStroke(Color.BLACK);
		Rectangle rectangle2= new Rectangle(recx-200, recy-610, boy-50, en-500);
		rectangle2.setFill(Color.BURLYWOOD);
		rectangle2.setStroke(Color.BLACK);
		Rectangle rectangle3= new Rectangle(recx, recy-650, en, boy);
		rectangle3.setFill(Color.BURLYWOOD);
		rectangle3.setStroke(Color.BLACK);
		Rectangle rectangle4= new Rectangle(recx+1250, recy-610, boy-50, en-500);
		rectangle4.setFill(Color.BURLYWOOD);
		rectangle4.setStroke(Color.BLACK);


		line= new Line(recx,recy+boy/2,recx+en,recy+boy/2);
		root.getChildren().add(btn);
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
			//coords.add(port);
			cizgi.add(line);
			root.getChildren().add(line);
			recx+=80;
		}


		//System.out.println(cizgi.get(0).getStartX());
		/*	for(int b=0; b<text.size(); b++) {

			//  root.getChildren().add(label);
			//root.getChildren().addAll(rent.get(b), text.get(b));
			root.getChildren().add(text.get(b));
			//   root.getChildren().add(rent.get(b));
			// System.out.println(rent.get);
		}
		 */

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
			//System.out.println("randon raw"); 
			root.getChildren().remove(btn);
			p1= new ArrayList<>();
			p2= new ArrayList<>();
			p3= new ArrayList<>();
			p4= new ArrayList<>();
			double pointx=130;
			double pointy=700;
			for(int b=0; b<=67; b++) {
				int stone= rnd.nextInt(text.size());
				if(b<=16) {
					text.get(stone).setX( cizgi.get(b).getStartX()+30 );
					text.get(stone).setY( cizgi.get(b).getStartY()+40 );
					p1.add(text.get(stone));
					root.getChildren().add(p1.get(b));
				}
				else if(17<=b && b<=33) {
					p2.add(text.get(stone));
				}else if(34<=b && b<=50) {
					p3.add(text.get(stone));
				} else if(51<=b) {
					p4.add(text.get(stone));
				}

				//  root.getChildren().add(label);
				//root.getChildren().addAll(rent.get(b), text.get(b));

				text.remove(stone);
				//   root.getChildren().add(rent.get(b));
				// System.out.println(rent.get);
			}
			System.out.print("p1= ");
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

		} 
	}; 
	EventHandler<MouseEvent> labelpress = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			//	System.out.println();
			orgTranslateX = ((Text)(t.getSource())).getTranslateX();
			orgTranslateY = ((Text)(t.getSource())).getTranslateY();

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
		// knk bu method
		@Override
		public void handle(MouseEvent t) {
			double prspointX=t.getSceneX();
			double prspointY=t.getSceneY();
			double pstX=0;
			double pstY=0;
			double wayX=0;
			double wayY=0;
			int shrt = 0;
			double clsx=0;
			double clsy=0;
			double shortest=999999;
			double msf=0;

			for(int ln=0; ln<cizgi.size(); ln++) {
				shrt=0;
				clsx=0;
				clsy=0;
				//if(dolu.get(ln)==false) {dolu.set(frk, true);}
				//	if(cizgi.get(ln).getStroke()== null) {
				///	Pointer portal= new Pointer();
				//pnt.contains(portal);
				wayX=prspointX-cizgi.get(ln).getStartX();
				wayY=prspointY-cizgi.get(ln).getStartY();
				msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));
				if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
					int frk=0;
					Pointer port= new Pointer();
					pstX=cizgi.get(ln).getStartX()-newTranslateX;
					pstY=cizgi.get(ln).getStartY()-newTranslateY;
					clsx=cizgi.get(ln).getStartX();
					clsy=cizgi.get(ln).getStartY();
					port.setXP(clsx);
					port.setYP(clsy);
					pnt.add(port);
					clsx=cizgi.get(ln).getStartX();
					clsy=cizgi.get(ln).getStartY();
					frk=ln;
					cizgi.get(ln).setStroke(Color.RED);
					break;
					//istakanin disinda tas birakilirsa en yakin okey karesine gider.
				}else if(msf<=shortest) {

					shortest=msf;
					pstX=cizgi.get(ln).getStartX()-newTranslateX;
					pstY=cizgi.get(ln).getStartY()-newTranslateY;
					shrt=ln;


				}

				//dolu.set(shrt, true);


			}
			//System.out.println("clsx="+prspointX);
			//System.out.println("clsy="+prspointY);
			//cizgi.get(shrt).setStroke(Color.RED);
			((Text)(t.getSource())).setX((pstX +30));
			((Text)(t.getSource())).setY((pstY+ 40));
			//Mouse birakilan yer
			//double orgScenerX = t.getSceneX();
			//double orgScenerY = t.getSceneY();
			//System.out.println("orgscenex="+((Text)(t.getSource())).getX());
			//System.out.println("orgsceney="+((Text)(t.getSource())).getY());


		}


	};

}