package mainapp;

import java.util.List;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaRepo pessoaFisicaRepo = new PessoaFisicaRepo();
    private static final PessoaJuridicaRepo pessoaJuridicaRepo = new PessoaJuridicaRepo();
    private static final String arquivoPessoaFisica = ".fisica.bin";
    private static final String arquivoPessoaJuridica = ".juridica.bin";

    public static void main(String[] args) {

        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Selecione uma Opcao");

            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    alterar();
                    break;
                case 3:
                    excluir();
                    break;
                case 4:
                    exibirPorId();
                    break;
                case 5:
                    exibirTodos();
                    break;
                case 6:
                    salvar();
                    break;
                case 7:
                    recuperar();
                    break;
                case 0:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("================================");
        System.out.println("1 - Incluir Pessoa");
        System.out.println("2 - Alterar Pessoa");
        System.out.println("3 - Excluir Pessoa");
        System.out.println("4 - Buscar pelo Id");
        System.out.println("5 - Exibir Todos");
        System.out.println("6 - Persistir Dados");
        System.out.println("7 - Recuperar Dados");
        System.out.println("0 - Finalizar Programa");
        System.out.println("================================");
    }

    private static void incluir() {
        String tipo = lerTipo();
        if (tipo.equalsIgnoreCase("F")) {
            pessoaFisicaRepo.inserir(lerPessoaFisica());
        } else {
            pessoaJuridicaRepo.inserir(lerPessoaJuridica());
        }
    }

    private static void alterar() {
        String tipo = lerTipo();
        int id = lerInteiro("ID: ");

        if (tipo.equalsIgnoreCase("F")) {
            PessoaFisica pessoaFisica = pessoaFisicaRepo.obter(id);
            if (pessoaFisica != null) {
                pessoaFisica.exibir();
                pessoaFisicaRepo.alterar(lerPessoaFisicaComId(id));
            } else {
                System.out.println("Pessoa Fisica nao encontrada.");
            }
        } else {
            PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obter(id);
            if (pessoaJuridica != null) {
                pessoaJuridica.exibir();
                pessoaJuridicaRepo.alterar(lerPessoaJuridicaComId(id));
            } else {
                System.out.println("Pessoa Juridica nao encontrada.");
            }
        }
    }

    private static void excluir() {
        String tipo = lerTipo();
        int id = lerInteiro("ID: ");

        if (tipo.equalsIgnoreCase("F")) {
            pessoaFisicaRepo.excluir(id);
        } else {
            pessoaJuridicaRepo.excluir(id);
        }
    }

    private static void exibirPorId() {
        String tipo = lerTipo();
        int id = lerInteiro("Digita o id da Pessoa:");

        if (tipo.equalsIgnoreCase("F")) {
            PessoaFisica pessoaFisica = pessoaFisicaRepo.obter(id);
            if (pessoaFisica != null) {
                pessoaFisica.exibir();
            } else {
                System.out.println("Pessoa Fisica nao encontrada.");
            }
        } else {
            PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obter(id);
            if (pessoaJuridica != null) {
                pessoaJuridica.exibir();
            } else {
                System.out.println("Pessoa Juridica nao encontrada.");
            }
        }
    }

    private static void exibirTodos() {
        String tipo = lerTipo();

        if (tipo.equalsIgnoreCase("F")) {
            List<PessoaFisica> lista = pessoaFisicaRepo.obterTodos();

            if (lista == null || lista.isEmpty()) {
                System.out.println("Lista de Pessoas Fisicas Vazia!");
            } else {
                for (PessoaFisica pessoaFisica : lista) {
                    if (pessoaFisica != null) {
                        pessoaFisica.exibir();
                        System.out.println();
                    }
                }
            }
        } else {
            List<PessoaJuridica> lista = pessoaJuridicaRepo.obterTodos();

            if (lista == null || lista.isEmpty()) {
                System.out.println("Lista de Pessoas Juridicas Vazia!");
            } else {
                for (PessoaJuridica pessoaJuridica : lista) {
                    if (pessoaJuridica != null) {
                        pessoaJuridica.exibir();
                        System.out.println();
                    }
                }
            }
        }
    }

    private static void salvar() {
        System.out.println("Prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        try {
            pessoaFisicaRepo.persistir(prefixo + arquivoPessoaFisica);
            pessoaJuridicaRepo.persistir(prefixo + arquivoPessoaJuridica);
            System.out.println("Dados salvos com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private static void recuperar() {
        System.out.println("Prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        try {
            pessoaFisicaRepo.recuperar(prefixo + arquivoPessoaFisica);
            pessoaJuridicaRepo.recuperar(prefixo + arquivoPessoaJuridica);
            System.out.println("Dados recuperados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }

    private static PessoaFisica lerPessoaFisica() {
        int id = lerInteiro("Digite o id da Pessoa: ");
        return lerPessoaFisicaComId(id);
    }

    private static PessoaFisica lerPessoaFisicaComId(int id) {
        System.out.println("Insira os dados...");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();
        int idade = lerInteiro("Idade: ");

        return new PessoaFisica(id, nome, cpf, idade);
    }

    private static PessoaJuridica lerPessoaJuridica() {
        int id = lerInteiro("Digite o id da Pessoa: ");
        return lerPessoaJuridicaComId(id);
    }

    private static PessoaJuridica lerPessoaJuridicaComId(int id) {
        System.out.println("Insira os dados...");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("CNPJ: ");
        String cnpj = scanner.nextLine();

        return new PessoaJuridica(id, nome, cnpj);
    }

    private static String lerTipo() {
        String tipo;
        do {
            System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
            tipo = scanner.nextLine();
        } while (!tipo.equalsIgnoreCase("F") && !tipo.equalsIgnoreCase("J"));

        return tipo;
    }

    private static int lerInteiro(String mensagem) {
        System.out.println(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}
