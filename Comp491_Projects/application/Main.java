package application;


import java.awt.Label;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

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
	ArrayList<Line> cizgi;
	//Pointer port;
	@Override
	public void start(Stage primaryStage) {
		//	ArrayList<Rectangle> rent=new ArrayList<>();
		text=new ArrayList<>();
		cizgi=new ArrayList<>();
		Group root = new Group();
		//ArrayList<Pointer> coords= new ArrayList<>();
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
				label.setFill(Color.GOLDENROD);
			}else if(a>=53 && a<=79){
				label.setFill(Color.GREEN);
			}else if(a>=80 && a<=104){
				label.setFill(Color.BLUE);
			}

			label.setX(xcor+ Width/2); 
			label.setY(ycor+ Height/2); 
			label.setFont(Font.font("Verdana", 25));
			label.setOnMousePressed(labelpress);
			label.setOnMouseDragged(labeldragg);
			//label.setOnMouseDragReleased(labelfin);
			label.setOnMouseReleased(labelout);
			text.add(label);
			/*	rectangle = new Rectangle();
			rectangle.setX(xcor); 
			rectangle.setY(ycor); 
			rectangle.setWidth(Width); 
			rectangle.setHeight(Height);
			rectangle.setFill(Color.WHITE);
			rectangle.setStroke(Color.BLACK);
			rectangle.setOnMousePressed(circleOnMousePressedEventHandler);
			rectangle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
			rent.add(rectangle);
			 */
			// System.out.println(rectangle.getX());
			if(a%13==0 && a/13>0) {
				xcor=30.0f;
				ycor+=50;
			}else {
				xcor+=slide;
			}
		}
		double recx=100;
		double recy=700;
		en=1200;
		boy=200;
		rectangle = new Rectangle();
		rectangle.setX(recx); 
		rectangle.setY(recy); 
		rectangle.setWidth(en); 
		rectangle.setHeight(boy);
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.BLACK);
		line= new Line(recx,recy+boy/2,recx+en,recy+boy/2);

		root.getChildren().addAll(rectangle, line);
		for(int l=1; l<=30; l++) {
			//port =new Pointer();
			line= new Line();
			//1.satir
			if(l%16==0 && l/16!=0) {
				recx=100;
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
		/*	for(int ar=0; ar<cizgi.size(); ar++) {
			System.out.println(cizgi.get(ar).getStartX()+" "+cizgi.get(ar).getStartY());
		}*/

		//System.out.println(cizgi.get(0).getStartX());
		for(int b=0; b<text.size(); b++) {

			//  root.getChildren().add(label);
			//root.getChildren().addAll(rent.get(b), text.get(b));
			root.getChildren().add(text.get(b));
			//   root.getChildren().add(rent.get(b));
			// System.out.println(rent.get);
		}


		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 1500,1000));

		primaryStage.setTitle("java-buddy");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	/*
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((Rectangle)(t.getSource())).getTranslateX();
			orgTranslateY = ((Rectangle)(t.getSource())).getTranslateY();

		}
	};

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;



			((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
			((Rectangle)(t.getSource())).setTranslateY(newTranslateY);

		}
	};
	 */


	EventHandler<MouseEvent> labelpress = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			//	System.out.println();
			orgTranslateX = ((Text)(t.getSource())).getTranslateX();
			orgTranslateY = ((Text)(t.getSource())).getTranslateY();
			//	System.out.println("orgSceneX="+orgSceneX);
			//System.out.println("orgSceneY="+orgSceneY);
			//System.out.println("orgTranslateX="+orgTranslateX);
			//System.out.println("orgTranslateY="+orgTranslateY);
			//System.out.println("*******************************");
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
			//System.out.println("???????????????????????????");
			//System.out.println("offsetX="+offsetX);
			//System.out.println("offsetY="+offsetY);
			//System.out.println("newTranslateX="+newTranslateX);
			//System.out.println("newTranslateY="+newTranslateY);
			//	System.out.println("????????????????????????????????????????");
			((Text)(t.getSource())).setTranslateX(newTranslateX);
			((Text)(t.getSource())).setTranslateY(newTranslateY);
			//((Text)(t.getSource())).setX(500);
			//((Text)(t.getSource())).setY(600);
		}
	};

	EventHandler<MouseEvent> labelout = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double prspointX=t.getSceneX();
			double prspointY=t.getSceneY();
			double pstX=0;
			double pstY=0;
			
			double shortest=999999;
			double msf=0;
			for(int ln=0; ln<cizgi.size(); ln++) {
				double wayX=prspointX-cizgi.get(ln).getStartX();
				double wayY=prspointY-cizgi.get(ln).getStartY();
				 msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2) );
				
				if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
					pstX=cizgi.get(ln).getStartX()-newTranslateX;
					pstY=cizgi.get(ln).getStartY()-newTranslateY;
					break;
				}else if(msf<=shortest) {
					shortest=msf;
					pstX=cizgi.get(ln).getStartX()-newTranslateX;
					pstY=cizgi.get(ln).getStartY()-newTranslateY;
					 //+ cizgi.get(ln).getEndY()-cizgi.get(ln).getStartY()+30;
					// + cizgi.get(ln).getEndX()-cizgi.get(ln).getStartX()+30;
					//System.out.println(shortest);
				}
				

			}
			((Text)(t.getSource())).setX((pstX+30));
			((Text)(t.getSource())).setY((pstY+40));
		}
	};
}