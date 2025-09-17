package dao;

import exceptions.DAOExceptions;
import model.Produto;

import java.util.List;

public interface ProdutoDAO {
    void inserir(Produto p) throws DAOExceptions;
    List<Produto> listar() throws DAOExceptions;
    void deletar(int id) throws DAOExceptions;

}
