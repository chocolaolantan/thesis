package w2v.view.fx;

import w2v.W2vFX;
import w2v.model.W2vModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;

public class SScene {
  private W2vModel wvm;
  private W2vFX app;
  private Stage stage;
  private Scene sc;
  private int i;

  public SScene(W2vFX app, W2vModel wvm) {
    this.app = app;
    this.wvm = wvm;
    init();
  }

  private void init() {
    StringBuffer sb = new StringBuffer();
    BorderPane bp = new BorderPane();
    HBox hb = new HBox();
    VBox vb = new VBox();

    Label msg = new Label("調べたい単語を入力してください。");
    Label lb1 = new Label("単語１");
    Label lb2 = new Label("単語2");
    Label txt = new Label("words : " + wvm.getWords() + "\tsize : " + wvm.getSize());

    TextField tf1 = new TextField();
    TextField tf2 = new TextField();

    Button btn = new Button("検索");

    ScrollPane sl = new ScrollPane();
    TextArea ta = new TextArea();
    sl.setContent(ta);

    bp.setTop(msg);
    hb.getChildren().add(lb1);
    hb.getChildren().add(tf1);
    hb.getChildren().add(lb2);
    hb.getChildren().add(tf2);
    hb.getChildren().add(btn);
    vb.getChildren().add(hb);
    vb.getChildren().add(txt);
    bp.setCenter(vb);
    bp.setBottom(ta);

    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String tmp = "tf1 : " + tf1.getText() + " tf2 : " + tf2.getText();
        System.out.println(wvm.getVocab(i));
        sb.append(tmp + "\n");
        sb.append("vocab[" + i +"] : " + wvm.getVocab(i) + "\n");
        i++;
        ta.setText(sb.toString());
      }
    });

    sc = new Scene(bp, 500, 300);
  }

  public Scene getScene() {
    return sc;
  }

}
