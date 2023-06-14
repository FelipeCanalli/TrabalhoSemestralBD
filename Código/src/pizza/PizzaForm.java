package pizza;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
import principal.Executor;
import principal.Tela;

public class PizzaForm implements Tela {
	private TextField txtNome = new TextField();
	private TextField txtPreco = new TextField();
	private TextField txtTamanho = new TextField();
	private TextField txtIngre = new TextField();
	private Label lblId = new Label("");
	private PizzaControl control;
	private TableView<Pizza> table = new TableView<>();
	private BorderPane principal;
	private Executor executor;

	public PizzaForm(Executor executor) {
		this.executor = executor;
	}

	public PizzaForm() {
	}

	public void limparCampos() {
		txtNome.setText("");
		txtPreco.setText("");
		txtTamanho.setText("");
		txtIngre.setText("");
	}

	public void ligacoes() {
//		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
//		Bindings.bindBidirectional(txtPreco.textProperty(),control.PrecoProperty(), 
//				(StringConverter)new DoubleStringConverter());
//		Bindings.bindBidirectional(txtTamanho.textProperty(), control.TamanhoProperty());
//		Bindings.bindBidirectional(txtIngre.textProperty(), control.IngreProperty());
//		Bindings.bindBidirectional(lblId.textProperty(), control.idProperty(),
//				(StringConverter) new LongStringConverter());
	}

	public void adicionar() {
//		try {
//			control.salvar();
//		} catch (SQLException e) {	
//			Alert a = new Alert(AlertType.ERROR, 
//     				"Erro ao salvar pela razão: " + e.getMessage(),ButtonType.OK);
//     		a.showAndWait();
//		}
//		Alert a = new Alert(AlertType.INFORMATION, "Pizza adicionada com sucesso", ButtonType.OK);
//		a.showAndWait();
//
//		control.novo();
	}

	public void prepararTabela() {
		TableColumn<Pizza, String> col1 = new TableColumn<>("Nome");
		col1.setCellValueFactory(new PropertyValueFactory<Pizza, String>("nome"));
		
		TableColumn<Pizza, String> col2 = new TableColumn<>("Preco");
		col2.setCellValueFactory(new PropertyValueFactory<Pizza, String>("preco"));
		
		TableColumn<Pizza, String> col3 = new TableColumn<>("Tamanho");
		col3.setCellValueFactory(new PropertyValueFactory<Pizza, String>("tamanho"));
		
		TableColumn<Pizza, String> col4 = new TableColumn<>("Ingredientes");
		col4.setCellValueFactory(new PropertyValueFactory<Pizza, String>("ingredientes"));

		TableColumn<Pizza, Void> col5 = new TableColumn<>("Acoes");
		Callback<TableColumn<Pizza, Void>, TableCell<Pizza, Void>> acoes = new Callback<>() {
			
			@Override
			public TableCell<Pizza, Void> call(TableColumn<Pizza, Void> param) {
				final Button btnExcluir = new Button("Excluir");
				TableCell<Pizza, Void> cell = new TableCell<>() {
					{
						btnExcluir.setOnAction(event -> {
							Pizza p = table.getItems().get(getIndex());
//							try {
//								control.excluir(p);
//							} catch (SQLException e) {
//								Alert a = new Alert(AlertType.ERROR, 
//					     				"Erro ao excluir pela razão: " + e.getMessage(),ButtonType.OK);
//					     		a.showAndWait();
//							}
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
		col5.setCellFactory(acoes);
		table.getColumns().addAll(col1, col2, col3, col4, col5);
//		table.setItems(control.getList());
//		table.getSelectionModel().getSelectedItems().addListener(new ListChangeListenePizzate>() {
//			@Override
//			public void onChanged(Change<? extends Pizza> c) {
//				if (!c.getList().isEmpty()) {
//					control.fromEntity(c.getList().get(0));
//				}
//			}
//		});
	}

	@Override
	public void start() {
//		try {
//			control = new PizzaControl();
//		} catch (ClassNotFoundException | SQLException | IOException e1) {
//			e1.printStackTrace();
//		}
		principal = new BorderPane();
		principal.setPadding(new Insets(50));
		GridPane painelForm = new GridPane();
		principal.setTop(new VBox(new Label("Cadastro de Pizza"), painelForm));
		principal.setCenter(table);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(15);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(30);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(10);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setPercentWidth(10);
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setPercentWidth(20);
		
		painelForm.getColumnConstraints().addAll(col1, col2, col3, col4, col5);

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
//			try { 
//				control.pesquisar();
//			} catch (SQLException err) { 
//				Alert a = new Alert(AlertType.ERROR, 
//     					"Erro ao pesquisar pela razão: " + err.getMessage(), 
//     					ButtonType.OK);
//     			a.showAndWait();
//			}
		});


		painelForm.add(new Label("Id"), 0, 0);
		painelForm.add(lblId, 1, 0);
		painelForm.add(new Label("Nome"), 0, 1);
		painelForm.add(txtNome, 1, 1);
		painelForm.add(new Label("Preço"), 0, 2);
		painelForm.add(txtPreco, 1, 2);
		painelForm.add(new Label("Tamanho"), 0, 3);
		painelForm.add(txtTamanho, 1, 3);
		painelForm.add(new Label("Ingredientes"), 0, 4);
		painelForm.add(txtIngre, 1, 4);

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