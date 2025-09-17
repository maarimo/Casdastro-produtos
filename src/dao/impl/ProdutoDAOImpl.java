package dao.impl;

import dao.ProdutoDAO;
import exceptions.DAOExceptions;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {
    private Connection conn;

    public ProdutoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    //inserir
    @Override
    public void inserir(Produto p) throws DAOExceptions {
        String sql = "INSERT INTO produto (nome, preco, quantidade) VALUES (?, ?, ?) ";

        try(PreparedStatement ps = conn.prepareStatement(sql)){

        ps.setString(1, p.getNome());
        ps.setDouble(2, p.getPreco());
        ps.setInt(3, p.getQuantidade());
        ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOExceptions("ERRO AO INSERIR!", e);
        }
    }

    //listar
    @Override
    public List<Produto> listar() throws DAOExceptions {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                lista.add(produto);

            }

        } catch (SQLException e){
            throw new DAOExceptions("ERRO AO LISTAR", e);
        }

        return lista;
    }

    @Override
    public void deletar(int id) throws DAOExceptions {
        String sql = "DELETE FROM produto WHERE id=?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e){
            throw new DAOExceptions("ERRO AO DELETAR!", e);
        }
    }
}
