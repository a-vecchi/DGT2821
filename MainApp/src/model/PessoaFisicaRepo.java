package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    
    private ArrayList<PessoaFisica> listaPessoas = new ArrayList<>();

    public void inserir(PessoaFisica pessoa) {
        listaPessoas.add(pessoa);
    }

    public void alterar(PessoaFisica pessoaAlterada) {
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

    public PessoaFisica obter(int id) {
        for (PessoaFisica p : listaPessoas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return listaPessoas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(listaPessoas);
            System.out.println("Dados de Pessoa Fisica Armazenados."); 
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaPessoas = (ArrayList<PessoaFisica>) in.readObject();
            System.out.println("Dados de Pessoa Fisica Recuperados."); 
        }
    }
}