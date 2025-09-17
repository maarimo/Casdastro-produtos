package app;

import config.Conexao;
import dao.ProdutoDAO;
import dao.impl.ProdutoDAOImpl;
import exceptions.DAOExceptions;
import model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String args[]){

        try(Connection conn = Conexao.getConnection()){
            ProdutoDAO dao = new ProdutoDAOImpl(conn);

            //inserir
            Produto p1 = new Produto("Arroz", 5.54, 10);
            dao.inserir(p1);


            //deletar
            dao.deletar(10);

            System.out.println("Usuário deletado com sucesso!");

            //listar
            List<Produto> produto = dao.listar();
            produto.forEach(System.out::println);


        } catch (SQLException | DAOExceptions e){
            e.printStackTrace();
        }
    }
}
