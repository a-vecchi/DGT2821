package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {

    private ArrayList<PessoaJuridica> listaPessoas = new ArrayList<>();

    public void inserir(PessoaJuridica pessoa) {
        listaPessoas.add(pessoa);
    }

    public void alterar(PessoaJuridica pessoaAlterada) {
        for (int i = 0; i < listaPessoas.size(); i++) {
            if (listaPessoas.get(i).getId() == pessoaAlterada.getId()) {
                listaPessoas.set(i, pessoaAlterada);
                return;
            }
        }
    }

    public void excluir(int id) {
        listaPessoas.removeIf(p -> p.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica p : listaPessoas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return listaPessoas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(listaPessoas);
            System.out.println("Dados de Pessoa Juridica Armazenados."); 
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaPessoas = (ArrayList<PessoaJuridica>) in.readObject();
            System.out.println("Dados de Pessoa Juridica Recuperados.");
        }
    }
}