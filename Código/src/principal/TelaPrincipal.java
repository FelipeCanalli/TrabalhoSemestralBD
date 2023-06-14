package principal;

import java.util.HashMap;
import java.util.Map;

import cliente.ClienteForm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pizza.PizzaForm;


public class TelaPrincipal extends Application {
	private Map<String, Tela> telas = new HashMap<>();

	public void gerarTelas() {
		telas.put("cadCliente", new ClienteForm());
		telas.put("cadPizza", new PizzaForm());

		for (Tela t : telas.values()) {
			t.start();
		}
	}

	public Tela getTela(String nome) {
		return telas.get(nome);
	}

	@Override
	public void start(Stage stage) throws Exception {

		gerarTelas();

		MenuBar mnuBar = new MenuBar();
		Menu mnuCadastro = new Menu("Cadastro");
		Menu mnuPedido = new Menu("Pedido");
		mnuBar.getMenus().addAll(mnuCadastro, mnuPedido);

		MenuItem mnuPizza = new MenuItem("Pizza");
		MenuItem mnuCliente = new MenuItem("Cliente");
		MenuItem mnuFun = new MenuItem("Funcion�rio");
		mnuCadastro.getItems().addAll(mnuPizza, mnuCliente, mnuFun);

		MenuItem mnuCadastroPedido = new MenuItem("Cadastrar");
		MenuItem mnuVisualizarPedido = new MenuItem("Visualizar");
		MenuItem mnuPagamentoPedido = new MenuItem("Pagamento");
		mnuPedido.getItems().addAll(mnuCadastroPedido, mnuVisualizarPedido, mnuPagamentoPedido);

		BorderPane bp = new BorderPane();
		mnuPizza.setOnAction((e) -> {
			bp.setCenter(getTela("cadPizza").render());
		});
		mnuCliente.setOnAction((e) -> {
			bp.setCenter(getTela("cadCliente").render());
		});
		bp.setTop(mnuBar);
		Scene scn = new Scene(bp, 1000, 600);

		stage.setScene(scn);
		stage.setTitle("Pizzaria Romero");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(TelaPrincipal.class, args);

	}

}
