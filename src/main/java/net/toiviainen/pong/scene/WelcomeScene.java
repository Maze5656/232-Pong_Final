package net.toiviainen.pong.scene;

import static java.util.Objects.requireNonNull;
import static net.toiviainen.pong.PongApplication.BIG_FONT;
import static net.toiviainen.pong.PongApplication.RESOLUTION_HEIGHT;
import static net.toiviainen.pong.PongApplication.RESOLUTION_WIDTH;
import static net.toiviainen.pong.PongApplication.SMALL_FONT;

import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.toiviainen.pong.PongApplication;

/**
 * <p>
 * The welcoming scene for the Pong game.
 * </p>
 * <p>
 * This scene is the introduction scene which will be shown to users when they
 * start the application. Scene contains the topic of the game, instructions
 * about how to move the paddles with the keyboard and a text which contains an
 * instructions about how to start the game.
 * </p>
 */
public class WelcomeScene extends AbstractScene {

	private final Text topicText;
	private final Text leftControlsTopicText;
	private final Text leftControlsText;
	private final Text rightControlsTopicText;
	private final Text rightControlsText;
	private final Text proceedInstructionsText;
	//private final Text onePlayerSelection;
	private final Text twoPlayerSelection;
	// CUSTOM
	private boolean isSinglePlayer = false;

	public WelcomeScene(PongApplication application) throws NullPointerException {
		super(new Group(), RESOLUTION_WIDTH, RESOLUTION_HEIGHT);

		requireNonNull(application, "The application cannot be null!");

		topicText = new Text("JavaFX Pong");
		topicText.setTextOrigin(VPos.CENTER);
		topicText.setFont(BIG_FONT);
		topicText.setLayoutX((RESOLUTION_WIDTH - topicText.prefWidth(-1)) / 2);
		topicText.setLayoutY(RESOLUTION_HEIGHT / 6);
		topicText.setFill(Color.WHITE);

		leftControlsTopicText = new Text("Controls for the left player(SINGLE AND 2 PLAYER):");
		leftControlsTopicText.setTextOrigin(VPos.CENTER);
		leftControlsTopicText.setFont(SMALL_FONT);
		leftControlsTopicText.setLayoutX((RESOLUTION_WIDTH - leftControlsTopicText.prefWidth(-1)) / 2);
		leftControlsTopicText.setLayoutY(topicText.getLayoutY() + 100);
		leftControlsTopicText.setFill(Color.WHITE);

		leftControlsText = new Text("W and S");
		leftControlsText.setTextOrigin(VPos.CENTER);
		leftControlsText.setFont(SMALL_FONT);
		leftControlsText.setLayoutX((RESOLUTION_WIDTH - leftControlsText.prefWidth(-1)) / 2);
		leftControlsText.setLayoutY(leftControlsTopicText.getLayoutY() + 40);
		leftControlsText.setFill(Color.WHITE);

		rightControlsTopicText = new Text("Controls for the right player(TWO PLAYER ONLY:");
		rightControlsTopicText.setTextOrigin(VPos.CENTER);
		rightControlsTopicText.setFont(SMALL_FONT);
		rightControlsTopicText.setLayoutX((RESOLUTION_WIDTH - rightControlsTopicText.prefWidth(-1)) / 2);
		rightControlsTopicText.setLayoutY(leftControlsText.getLayoutY() + 60);
		rightControlsTopicText.setFill(Color.WHITE);

		rightControlsText = new Text("UP-ARROW and DOWN-ARROW");
		rightControlsText.setTextOrigin(VPos.CENTER);
		rightControlsText.setFont(SMALL_FONT);
		rightControlsText.setLayoutX((RESOLUTION_WIDTH - rightControlsText.prefWidth(-1)) / 2);
		rightControlsText.setLayoutY(rightControlsTopicText.getLayoutY() + 40);
		rightControlsText.setFill(Color.WHITE);

		proceedInstructionsText = new Text("Press [SPACE] to start Single Player");
		proceedInstructionsText.setTextOrigin(VPos.CENTER);
		proceedInstructionsText.setFont(SMALL_FONT);
		proceedInstructionsText.setLayoutX((RESOLUTION_WIDTH - proceedInstructionsText.prefWidth(-1)) / 2);
		proceedInstructionsText.setLayoutY(rightControlsTopicText.getLayoutY() + 160);
		proceedInstructionsText.setFill(Color.WHITE);

// --------------------------- CUSTOM block
		twoPlayerSelection = new Text("Press [ENTER] to start with 2 Players");
		twoPlayerSelection.setTextOrigin(VPos.CENTER);
		twoPlayerSelection.setFont(SMALL_FONT);
		twoPlayerSelection.setLayoutX((RESOLUTION_WIDTH - twoPlayerSelection.prefWidth(-1)) / 2);
		twoPlayerSelection.setLayoutY(proceedInstructionsText.getLayoutY() + 40);
		twoPlayerSelection.setFill(Color.DODGERBLUE);
// --------------------------- CUSTOM end

		Parent root = getRoot();
		if (!(root instanceof Group)) {
			throw new AssertionError("The scene root is not a Group instance!");
		}

		Group rootGroup = (Group) root;
		ObservableList<Node> children = rootGroup.getChildren();
		children.add(topicText);
		children.add(leftControlsTopicText);
		children.add(leftControlsText);
		children.add(rightControlsTopicText);
		children.add(rightControlsText);
		children.add(proceedInstructionsText);
		children.add(twoPlayerSelection);

		setOnKeyReleased(x -> {
			if (x.getCode() == KeyCode.ENTER) {
				// move into the court scene so we can start the game.
				Stage primaryStage = application.getPrimaryStage();
				primaryStage.setScene(new CourtScene(application, isSinglePlayer));
			}
// ------------------------ Go to Custom Single Player
			if (x.getCode() == KeyCode.SPACE) {
				isSinglePlayer = true;
				Stage primaryStage = application.getPrimaryStage();
				primaryStage.setScene(new CourtScene(application, isSinglePlayer));
			}
		});

		setFill(Color.BLACK);
	}

	@Override
	public void tick() {
		// ... nothing to do ...
	}

}
