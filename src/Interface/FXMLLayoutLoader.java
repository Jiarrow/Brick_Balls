package Interface;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public interface FXMLLayoutLoader {
	default void loadXML(Pane pane, String xml) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/" + xml + ".fxml"));
		fxmlLoader.setRoot(pane);
		fxmlLoader.setController(pane);
		
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
