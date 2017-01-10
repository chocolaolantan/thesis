package w2v;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

import w2v.view.fx.FScene;
import w2v.view.fx.SScene;
import w2v.model.W2vModel;

public class W2vFX extends Application {
  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    stage.setTitle("W2vProject ~ for JavaFX ~");
    FScene fs = new FScene(this);
    stage.setScene(fs.getScene());
    stage.show();
  }

  public void setFile(File fi) {
    SScene ss = new SScene(this, new W2vModel(fi));
    stage.setScene(ss.getScene());
    stage.show();
  }
}
