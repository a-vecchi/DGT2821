package mainapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

public class MainApp {

    public static void main(String[] args) {

        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();

        repo1.inserir(new PessoaFisica(1, "Ana", "11111111111", 25));
        repo1.inserir(new PessoaFisica(2, "Carlos", "22222222222", 52));

        try {
            repo1.persistir("PessoaFisica.dat");
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        
        try {
            repo2.recuperar("PessoaFisica.dat");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (PessoaFisica pf : repo2.obterTodos()) {
            pf.exibir();
        }

        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();

        repo3.inserir(new PessoaJuridica(3, "XPTO Sales", "33333333333333"));
        repo3.inserir(new PessoaJuridica(4, "XPTO Solutions", "44444444444444"));

        try {
            repo3.persistir("PessoaJuridica.dat");
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();

        try {
            repo4.recuperar("PessoaJuridica.dat");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (PessoaJuridica pj : repo4.obterTodos()) {
            pj.exibir();

        }
    }
}
