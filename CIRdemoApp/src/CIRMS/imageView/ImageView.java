package CIRMS.imageView;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ImageView
{
	private static final String imageView = "/CIRMS/Image/images.png";

	public static void setStageIcon(Stage stage)
	{
		stage.getIcons().add(new Image(imageView));
	}

}
