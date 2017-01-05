package w2v.view.fx;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import w2v.W2vFX;

public class FScene {
  private W2vFX app;
  private Scene sc;

  public FScene(W2vFX app) {
    this.app = app;
    init();
  }

  private void init() {
    BorderPane bp = new BorderPane();
    VBox vb = new VBox();

    Label msg = new Label("使用するモデルを選択してください。");
    Button btn = new Button("選択");

    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        FileChooser fc = new FileChooser();
        try {
          File fi = fc.showOpenDialog(new Stage());
          if (fi != null) {
            app.setFile(fi);
          }
        } catch (Exception ex) {
          System.out.println("log : " + ex);
          //ex.printStackTrace();
        }
      }
    });

    vb.getChildren().add(btn);
    bp.setTop(msg);
    bp.setCenter(vb);

    sc = new Scene(bp, 300, 200);
  }

  public Scene getScene(){
    return sc;
  }
}
