import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Agenda {

    private final List<Contato> contatos = new ArrayList<>();

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Que jeito gostaria de trabalhar: 1 - Gráfica 2 - Console");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            SwingUtilities.invokeLater(() -> agenda.iniciarInterfaceGrafica());
        } else if (escolha == 2) {
            agenda.iniciarConsole(scanner);
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    public void iniciarInterfaceGrafica() {
        JFrame frame = new JFrame("Agenda de Contatos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        DefaultListModel<Contato> listModel = new DefaultListModel<>();
        JList<Contato> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(list);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        panel.add(nomeField);
        panel.add(new JLabel("Celular:"));
        JTextField celularField = new JTextField();
        panel.add(celularField);
        panel.add(new JLabel("Email (opcional):"));
        JTextField emailField = new JTextField();
        panel.add(emailField);
        panel.add(new JLabel("Descrição (opcional):"));
        JTextField descricaoField = new JTextField();
        panel.add(descricaoField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Deletar");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String celular = celularField.getText();
            String email = emailField.getText();
            String descricao = descricaoField.getText();
            Contato contato = new Contato(nome, celular, email, descricao);
            contatos.add(contato);
            listModel.addElement(contato);
            clearFields(nomeField, celularField, emailField, descricaoField);
        });

        updateButton.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex != -1) {
                Contato contato = contatos.get(selectedIndex);
                contato.setNome(nomeField.getText());
                contato.setCelular(celularField.getText());
                contato.setEmail(emailField.getText());
                contato.setDescricao(descricaoField.getText());
                listModel.set(selectedIndex, contato);
                clearFields(nomeField, celularField, emailField, descricaoField);
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex != -1) {
                contatos.remove(selectedIndex);
                listModel.remove(selectedIndex);
            }
        });

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Contato selectedContato = list.getSelectedValue();
                if (selectedContato != null) {
                    nomeField.setText(selectedContato.getNome());
                    celularField.setText(selectedContato.getCelular());
                    emailField.setText(selectedContato.getEmail());
                    descricaoField.setText(selectedContato.getDescricao());
                }
            }
        });

        frame.setVisible(true);
    }

    private void clearFields(JTextField nomeField, JTextField celularField, JTextField emailField, JTextField descricaoField) {
        nomeField.setText("");
        celularField.setText("");
        emailField.setText("");
        descricaoField.setText("");
    }
    public void iniciarConsole(Scanner scanner) {
        while (true) {
            System.out.println("Escolha: (1) Adicionar Contato (2) Listar Contatos (3) Editar Contato (4) Deletar Contato (5) Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarContato(scanner);
                    break;
                case 2:
                    listarContatos();
                    break;
                case 3:
                    atualizarContato(scanner);
                    break;
                case 4:
                    deletarContato(scanner);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Digite uma opcao valida!!");
            }
        }
    }

    private void adicionarContato(Scanner scanner) {
        System.out.println("Nome:");
        String nome = scanner.nextLine();
        System.out.println("Celular:");
        String celular = scanner.nextLine();
        System.out.println("Email (opcional):");
        String email = scanner.nextLine();
        System.out.println("Descrição (opcional):");
        String descricao = scanner.nextLine();

        Contato contato = new Contato(nome, celular, email, descricao);
        contatos.add(contato);
        System.out.println("Contato adicionado.");
    }

    private void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato na lista.");
        } else {
            for (int i = 0; i < contatos.size(); i++) {
                System.out.println((i + 1) + ". " + contatos.get(i));
            }
        }
    }

    private void atualizarContato(Scanner scanner) {
        listarContatos();
        System.out.println("Escolha o número do contato para atualizar:");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < contatos.size()) {
            Contato contato = contatos.get(index);
            System.out.println("Novo Nome:");
            contato.setNome(scanner.nextLine());
            System.out.println("Novo Celular:");
            contato.setCelular(scanner.nextLine());
            System.out.println("Novo Email :");
            contato.setEmail(scanner.nextLine());
            System.out.println("Nova Descrição :");
            contato.setDescricao(scanner.nextLine());
            System.out.println("Contato atualizado.");
        } else {
            System.out.println("Contato inválido.");
        }
    }

    private void deletarContato(Scanner scanner) {
        listarContatos();
        System.out.println("Escolha o número do contato para excluir:");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < contatos.size()) {
            contatos.remove(index);
            System.out.println("Contato deletado.");
        } else {
            System.out.println("Contato inválido.");
        }
    }
}
