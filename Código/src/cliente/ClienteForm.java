package cliente;

import java.io.IOException;
import java.sql.SQLException;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LongStringConverter;
import principal.Executor;
import principal.Tela;

public class ClienteForm implements Tela {
	private TextField txtNome = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtTipoEnd = new TextField();
	private TextField txtLogrEnd = new TextField();
	private TextField txtNumEnd = new TextField();
	private TextField txtCompEnd = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEmail = new TextField();
	private Label lblId = new Label("");
	private ClienteControl control;
	private TableView<Cliente> table = new TableView<>();
	private BorderPane principal;
	private Executor executor;

	public ClienteForm(Executor executor) {
		this.executor = executor;
	}

	public ClienteForm() {
	}

	public void limparCampos() {
		txtNome.setText("");
		txtCpf.setText("");
		txtTipoEnd.setText("");
		txtLogrEnd.setText("");
		txtNumEnd.setText("");
		txtCompEnd.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
	}

	public void ligacoes() {
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtCpf.textProperty(), control.cpfProperty());
		Bindings.bindBidirectional(txtTipoEnd.textProperty(), control.tipoEndProperty());
		Bindings.bindBidirectional(txtLogrEnd.textProperty(), control.logrEndProperty());
		Bindings.bindBidirectional(txtNumEnd.textProperty(), control.numEndProperty());
		Bindings.bindBidirectional(txtCompEnd.textProperty(), control.compEndProperty());
		Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
		Bindings.bindBidirectional(lblId.textProperty(), control.idProperty(),
				(StringConverter) new LongStringConverter());
	}

	public void adicionar() {
		try {
			control.salvar();
		} catch (SQLException e) {	
			Alert a = new Alert(AlertType.ERROR, 
     				"Erro ao salvar pela razão: " + e.getMessage(),ButtonType.OK);
     		a.showAndWait();
		}
		Alert a = new Alert(AlertType.INFORMATION, "Cliente adicionado com sucesso", ButtonType.OK);
		a.showAndWait();

		control.novo();
	}

	public void prepararTabela() {
		TableColumn<Cliente, String> col1 = new TableColumn<>("Nome");
		col1.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		
		TableColumn<Cliente, String> col2 = new TableColumn<>("Telefone");
		col2.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));

		TableColumn<Cliente, Void> col3 = new TableColumn<>("Acoes");
		Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> acoes = new Callback<>() {
			
			@Override
			public TableCell<Cliente, Void> call(TableColumn<Cliente, Void> param) {
				final Button btnExcluir = new Button("Excluir");
				TableCell<Cliente, Void> cell = new TableCell<>() {
					{
						btnExcluir.setOnAction(event -> {
							Cliente c = table.getItems().get(getIndex());
							try {
								control.excluir(c);
							} catch (SQLException e) {
								Alert a = new Alert(AlertType.ERROR, 
					     				"Erro ao excluir pela razão: " + e.getMessage(),ButtonType.OK);
					     		a.showAndWait();
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnExcluir);
						}
					}
				};
				return cell;
			}
		};
		col3.setCellFactory(acoes);
		table.getColumns().addAll(col1, col2, col3);
		table.setItems(control.getList());
		table.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Cliente>() {
			@Override
			public void onChanged(Change<? extends Cliente> c) {
				if (!c.getList().isEmpty()) {
					control.fromEntity(c.getList().get(0));
				}
			}
		});
	}

	@Override
	public void start() {
		try {
			control = new ClienteControl();
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		principal = new BorderPane();
		principal.setPadding(new Insets(50));
		GridPane painelForm = new GridPane();
		principal.setTop(new VBox(new Label("Cadastro de Clientes"), painelForm));
		principal.setCenter(table);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(30);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(70);
		painelForm.getColumnConstraints().addAll(col1, col2);

		Button btnNovo = new Button("Novo Item");
		Button btnSalvar = new Button("Salvar");
		Button btnPesquisar = new Button("Pesquisar");
		Button btnVoltar = new Button("Voltar Menu");

		btnNovo.setOnAction(e -> {
			control.novo();
		});

		btnSalvar.setOnAction((e) -> {
			adicionar();
		});

		btnPesquisar.setOnAction((e)->{
			try { 
				control.pesquisar();
			} catch (SQLException err) { 
				Alert a = new Alert(AlertType.ERROR, 
     					"Erro ao pesquisar pela razão: " + err.getMessage(), 
     					ButtonType.OK);
     			a.showAndWait();
			}
		});


		painelForm.add(new Label("Id"), 0, 0);
		painelForm.add(lblId, 1, 0);
		painelForm.add(new Label("Nome"), 0, 1);
		painelForm.add(txtNome, 1, 1);
		painelForm.add(new Label("CPF"), 0, 2);
		painelForm.add(txtCpf, 1, 2);
		painelForm.add(new Label("Tipo Endereço"), 0, 3);
		painelForm.add(txtTipoEnd, 1, 3);
		painelForm.add(new Label("Endereço"), 0, 4);
		painelForm.add(txtLogrEnd, 1, 4);
		painelForm.add(new Label("Número"), 0, 5);
		painelForm.add(txtNumEnd, 1, 5);
		painelForm.add(new Label("Complemento"), 0, 6);
		painelForm.add(txtCompEnd, 1, 6);
		painelForm.add(new Label("Telefone"), 0, 7);
		painelForm.add(txtTelefone, 1, 7);
		painelForm.add(new Label("Email"), 0, 8);
		painelForm.add(txtEmail, 1, 8);

		FlowPane fpBotoes = new FlowPane();
		fpBotoes.getChildren().addAll(btnSalvar, btnPesquisar);

		painelForm.add(btnNovo, 0, 10);
		painelForm.add(fpBotoes, 1, 10);

		ligacoes();
		prepararTabela();
	}

	public Pane render() {
		return principal;
	}
}
