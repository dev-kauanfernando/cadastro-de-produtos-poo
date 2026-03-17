import java.util.Scanner;
import java.util.ArrayList;

class Product {
	private int id;
	private String name;
	private double price;
	private int quantity;
	
	public Product(int id, String name, double price, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
	    return price;
	}
	
	public void setPrice(double price) {
	    this.price = price;
	}
	
	public int getQuantity() {
	    return quantity;
	}
	
	public void setQuantity(int quantity) {
	    this.quantity = quantity;
	}
	
	public void showProduct() {
		System.out.println("\nID: " + id);
		System.out.println("Nome: " + name);
		System.out.println("Preco: " + price);
		System.out.println("Quantidade: " + quantity);
	}
}

public class Main {
	ArrayList<Product> products = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	
	void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	void pauseScreen() {
		System.out.println("\nPressione ENTER para continuar...");
		sc.nextLine();
	}
	
	void title(String title) {
		System.out.println("==== " + title + " ====\n");
	}
	
	boolean hasNoProducts() {
		if (products.isEmpty()) {
			System.out.println("Sem produtos cadastrados...");
			return true;
		}
		return false;
	}
	
	boolean isIdAvailable(int id) {
		for (Product p : products) {
			if (p.getId() == id) {
				System.out.println("ID ja cadastrado!");
				return false;
			}
		}
		return true;
	}
	
	Product findProduct() {
	System.out.print("Digite o ID do produto: ");
	int id = Integer.parseInt(sc.nextLine());

	for (Product p : products) {
		if (p.getId() == id) {
			return p;
		}
	}

	System.out.println("Produto nao encontrado!");
	return null;
}
	
	void showStock() {
		System.out.printf("%-5s | %-15s | %8s | %5s | %8s\n", "ID", "Nome", "Preco", "Qtd", "Total");
		for (Product p : products) {
			System.out.println("------|-----------------|----------|-------|---------");
			System.out.printf("%-5d | %-15s | %8.2f | %5d | %8.2f\n", 
			p.getId(),
			p.getName(),
			p.getPrice(),
			p.getQuantity(),
			p.getQuantity() * p.getPrice());
		}
	}
	
	void removeProduct() {
		title("REMOVER PRODUTOS");
		
		if (hasNoProducts()) return;
		
		Product p = findProduct();

		if (p == null) return;
		
		p.showProduct();
		
		System.out.print("\nDeseja remover este produto (s/n)? ");
		char op = sc.nextLine().toLowerCase().charAt(0);
		
		if (op == 's') {
			products.remove(p);
			System.out.println("Produto removido com sucesso!");
		} else if (op != 'n') {
			System.out.println("Opcao invalida!");
		}
	}
	
	void updateProduct() {
		title("ATUALIZAR PRODUTOS");
		
		if (hasNoProducts()) return;
		
		Product p = findProduct();

		if (p == null) return;
		
		p.showProduct();
		
		System.out.print("\nDeseja atualizar este produto (s/n)? ");
		char op = sc.nextLine().toLowerCase().charAt(0);
		
		if (op == 's') {
			System.out.println("\nDigite as novas informacoes");
			System.out.print("Nome (Enter para manter): ");
			String name = sc.nextLine();
			if (!name.isEmpty()) p.setName(name);
			
			System.out.print("Preco (0 para manter): ");
			double price = Double.parseDouble(sc.nextLine());
			if (price != 0) p.setPrice(price);
			
			System.out.print("Quantidade (0 para manter): ");
			int quantity = Integer.parseInt(sc.nextLine());
			if (quantity != 0) p.setQuantity(quantity);
			
			System.out.println("\nProduto atualizado com sucesso!");
		} else if (op != 'n') {
			System.out.println("Opcao invalida!");
		}
	}
	
	void listProduct() {
		title("LISTAR PRODUTOS");
		
		if (hasNoProducts()) return;
		
		System.out.println("1 - Todos os produtos");
		System.out.println("2 - Pesquisar produto");
		System.out.print("\nEscolha uma opcao: ");
		int op = Integer.parseInt(sc.nextLine());
		
		clearScreen();
		
		switch (op) {
			case 1:
				title("ESTOQUE");
				showStock();
				break;
			case 2:
				title("LISTAR PRODUTOS");
				Product p = findProduct();
				if (p != null) p.showProduct();
				break;
			default:
				System.out.println("Opcao invalida!");
				break;
		}		
	}
	
	void cadastreProduct() {
		title("CADASTRO DE PRODUTOS");
		System.out.print("ID: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if (!isIdAvailable(id)) return;
		
		System.out.print("Nome: ");
		String name = sc.nextLine();
		
		System.out.print("Preco: ");
		double price = Double.parseDouble(sc.nextLine());
		
		System.out.print("Quantidade: ");
		int quantity = Integer.parseInt(sc.nextLine());
		
		System.out.println("\nProduto cadastrado com sucesso!");
		
		products.add(new Product(id, name, price, quantity));
	}
	
	int menu() {
		title("SISTEMA DE PRODUTOS");
		System.out.println("1 - Cadastrar produto");
		System.out.println("2 - Listar produtos");
		System.out.println("3 - Atualizar produto");
		System.out.println("4 - Remover produto");
		System.out.println("5 - Sair");
		System.out.print("\nEscolha uma opcao: ");
		return Integer.parseInt(sc.nextLine());
	}
	
	void startMenu() {
		while (true) {
			int op = menu();
			clearScreen();
			
			switch (op) {
				case 1:
					cadastreProduct();
					break;
				case 2:
					listProduct();
					break;
				case 3:
					updateProduct();
					break;
				case 4:
					removeProduct();
					break;
				case 5:
					System.out.println("Saindo...");
					System.exit(0);
					break;
				default:
					System.out.println("Opcao invalida!");
					break;
			}
			pauseScreen();
			clearScreen();
		}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		
		m.startMenu();
	}
}
