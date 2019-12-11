import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RaceTrack extends Application {

    ReentrantLock lock = new ReentrantLock();
    private volatile boolean flag = false;
    int initialPos = 10; //starting position

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //buttons
        Button start = new Button("Start");
        Button pause = new Button("Pause");
        Button reset = new Button("Reset");
        primaryStage.setTitle("Richmond Raceway");

        //Image file
        File file = new File("out/production/RaceTrackGUI/sportscar.png");
        Image image = new Image(file.toURI().toString());

        //create image views
        ImageView car1 = new ImageView(image);
        ImageView car2 = new ImageView(image);
        ImageView car3 = new ImageView(image);

        //Puts the buttons in a horizontal box
        HBox buttonsHbox = new HBox(start, pause, reset);
        buttonsHbox.setAlignment(Pos.TOP_CENTER);
        buttonsHbox.setPadding(new Insets(15, 0, 0, 0 ));
        buttonsHbox.setSpacing(20);

        //arranges the car images vertically
        VBox vboxCar1 = new VBox(car1);
        vboxCar1.setManaged(false);
        vboxCar1.setLayoutX(initialPos);
        vboxCar1.setLayoutY(40);
        vboxCar1.setPickOnBounds(false);

        //arranges the car images vertically
        VBox vboxCar2 = new VBox(car2);
        vboxCar2.setManaged(false);
        vboxCar2.setLayoutX(initialPos);
        vboxCar2.setLayoutY(70);
        vboxCar2.setPickOnBounds(false);

        //arranges the car images vertically
        VBox vboxCar3 = new VBox(car3);
        vboxCar3.setManaged(false);
        vboxCar3.setLayoutX(initialPos);
        vboxCar3.setLayoutY(100);
        vboxCar3.setPickOnBounds(false);

        //Creates rectangle image to act as racetrack
        Rectangle racetrack1 = new Rectangle(0,0 , 400, 20);
        racetrack1.setFill(Color.rgb(50,50,50,0.2));
        //Creates rectangle image to act as racetrack
        Rectangle racetrack2 = new Rectangle(0,0 , 400, 20);
        racetrack2.setFill(Color.rgb(50,50,50,0.2));
        //Creates rectangle image to act as racetrack
        Rectangle racetrack3 = new Rectangle(0,0 , 400, 20);
        racetrack3.setFill(Color.rgb(50,50,50,0.2));

        //Puts the racetrack object in a vbox
        VBox racetrackVBox = new VBox(racetrack1, racetrack2, racetrack3);
        racetrackVBox.setAlignment(Pos.CENTER);
        racetrackVBox.setSpacing(10);
        racetrackVBox.setPickOnBounds(false);

        //Creates a stackpane and adds all the vbox and hbox nodes to it.
        StackPane stackShit = new StackPane();
        stackShit.getChildren().addAll(vboxCar1,vboxCar2,vboxCar3, buttonsHbox, racetrackVBox);

        StackPane.setAlignment(buttonsHbox, Pos.TOP_CENTER);
        StackPane.setAlignment(racetrackVBox, Pos.CENTER);

        //Creates a scene
        Scene scene = new Scene(stackShit);
        //Set size of primary stage
        primaryStage.setWidth(500);
        primaryStage.setHeight(200);
        //Sets the scene
        primaryStage.setScene(scene);
        //Make the soxe unresizeable
        primaryStage.setResizable(false);
        //show stage
        primaryStage.show();

        //Thread for first car
        Thread car1_Thread = new Thread(() -> {
            Runnable runnable = () -> {
                Random rand = new Random();

                if (flag) {
                    int randomVal = rand.nextInt(11); //creates  a random number from 1 - 10
                    if (vboxCar1.getTranslateX() < 400) {
                        vboxCar1.setTranslateX(vboxCar1.getTranslateX() + randomVal);
                    } else if (vboxCar1.getTranslateX() > 399) {
                        //calls the stop car method
                        stopCar();
                        //alert the alerter the winner
                        winningCar("The First Car");
                    }
                }
            };

            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(runnable);
            }
        });

        //Thread for Second car
        Thread car2_Thread = new Thread(() -> {
            Runnable runnable = () -> {
                Random rand = new Random();

                if (flag) {
                    int randomVal = rand.nextInt(11); //creates  a random number from 1 - 10
                    if (vboxCar2.getTranslateX() < 400) {
                        vboxCar2.setTranslateX(vboxCar2.getTranslateX() + randomVal);
                    } else if (vboxCar2.getTranslateX() > 399) {
                        //calls the stop car method
                        stopCar();
                        //alert the alerter the winner
                        winningCar("The Second Car");
                    }
                }
            };

            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(runnable);
            }
        });

        //Thread for third car
        Thread car3_Thread = new Thread(() -> {
            Runnable runnable = () -> {
                Random rand = new Random();
                if (flag) {
                    int randomVal = rand.nextInt(11); //creates  a random number from 1 - 10
                    if (vboxCar3.getTranslateX() < 400) {
                        vboxCar3.setTranslateX(vboxCar3.getTranslateX() + randomVal);
                    }
                    else if (vboxCar3.getTranslateX() > 399) {
                        //calls the stop car method
                        stopCar();
                        //alert the alerter the winner
                        winningCar("The Third Car");
                    }
                }
            };

            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(runnable);
            }
        });

        //
        car1_Thread.setDaemon(true);
        car2_Thread.setDaemon(true);
        car3_Thread.setDaemon(true);

        //Start threads
        car1_Thread.start();
        car2_Thread.start();
        car3_Thread.start();

        //Event Handler for the start button
        EventHandler<ActionEvent> carEvent = e -> {
            lock.lock();
            try {
                flag = true;
            } finally {
                lock.unlock();
            }
        };
        start.setOnAction(carEvent);

        //Pause button event handler
        pause.setOnAction(actionEvent -> {
            lock.lock();
            try {
                flag = false;
            } finally {
                lock.unlock();
            }
        });

        //Event handler for the reset button
        reset.setOnAction(actionEvent -> {
            //Resets the position back to the initial position value
            vboxCar1.setLayoutX(initialPos);
            vboxCar2.setLayoutX(initialPos);
            vboxCar3.setLayoutX(initialPos);

            //resets the translate position to 0
            vboxCar1.setTranslateX(0);
            vboxCar2.setTranslateX(0);
            vboxCar3.setTranslateX(0);

            //changes the boolean state
            flag = false;
        });
    }


    //Method to stop the running thread for the cars.
    private void stopCar() {
        lock.lock();
        try {
            //sets the flag to false
            flag = false;
        } finally {
            lock.unlock();
        }
    }

    //This method creates an alert box to display the winner of the race
    private void winningCar(String winner) {
        lock.lock();
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(winner);
            alert.setTitle("Race Winner");
            alert.setHeaderText("And the winner is ....");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        } finally {
            lock.unlock();
        }
    }
}
