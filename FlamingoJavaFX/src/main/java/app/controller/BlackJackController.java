package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import app.Flamingo;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import pkgCore.Action;
import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Table;
import pkgEnum.eAction;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;

public class BlackJackController implements Initializable {

	private Flamingo FlamingoGame;
	@FXML private Label lblTopName;
	@FXML private Label lblBottomName;
	@FXML private Button btnTop;
	@FXML private Button btnBottom;
	@FXML private Button btnHit;
	@FXML private Button btnStand;
	

	@FXML
	private BorderPane mainScreen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public void setMainApp(Flamingo FlamingoGame) {
		this.FlamingoGame = FlamingoGame;
	}

	@FXML
	public void btnSitLeave_Click(ActionEvent event) {
		Button btn = (Button) event.getSource();

		Action act = new Action();

		act.setAction(btn.getText().equals("Sit")  ? eAction.Sit : eAction.Leave);
		Player p = FlamingoGame.getAppPlayer();
		if (btn.getId().equals("btnTop")) {
			p.setiPlayerPosition(2);

		} else if (btn.getId().equals("btnBottom")) {
			p.setiPlayerPosition(0);
		}

		act.setPlayer(FlamingoGame.getAppPlayer());
		FlamingoGame.SendMessageToClient(act);

		// TODO: Implement this. Create a new 'Action', send the 'Sit' or 'Leave' action
		// to the Hub.

	}
	
	@FXML 
	public void btnHit_Clicked(ActionEvent event) {
		Button btn = (Button) event.getSource();
		
		Action act = new Action();
		if(btn.getText().equals("Hit")) {
			act.setAction(eAction.Hit);
		}
		
		
	}

	public void HandleTableState(Table t) {
	btnHit.setVisible(false);
	btnStand.setVisible(false);
	lblBottomName.setText("");
	lblTopName.setText("");
	btnTop.setVisible(true);
	btnBottom.setVisible(true);
	btnTop.setText("Sit");
	btnBottom.setText("Sit");
	for (Player p: t.GetTablePlayers())
	{
		if (p.getPlayerID().compareTo(FlamingoGame.getAppPlayer().getPlayerID())==0) {
			switch (p.getiPlayerPosition())
			{
			case 0:
				lblBottomName.setText(p.getPlayerName());
				btnTop.setVisible(false);
				btnBottom.setText("Leave");
				break;
			case 2:
				lblTopName.setText(p.getPlayerName());
				btnBottom.setVisible(false);
				btnTop.setText("Leave");
				break;
			}
		}
		else {
			switch (p.getiPlayerPosition())
			{
			case 0:
				lblBottomName.setText(p.getPlayerName());
				btnBottom.setVisible(false);
				break;
			case 2:
				lblTopName.setText(p.getPlayerName());
				btnTop.setVisible(false);
				break;
			}
		}
	}
	
	//	How to handle button text & visibility
	//btnTop.setText("Leave");
	//btnTop.setVisible(false);
	// TODO: Implement this.
}

	public void HandleGameState(GamePlay gp) {

		// Coming Soon....!
	}

}
