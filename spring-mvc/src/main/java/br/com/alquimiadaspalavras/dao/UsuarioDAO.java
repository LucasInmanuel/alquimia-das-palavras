package br.com.alquimiadaspalavras.dao;

import br.com.alquimiadaspalavras.connection.ConnectionFactory;
import br.com.alquimiadaspalavras.dao.interfaces.IUsuarioDAO;
import br.com.alquimiadaspalavras.model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {
    Connection conn = null;
    PreparedStatement pstm = null;

    public void insert(Usuario usuario) {

        String sql = "INSERT INTO usuarios(nome,apelido,data_cadastro,email,senha,nacionalidade,genero,data_nascimento)" +
                " VALUES(?,?,?,?,?,?,?,?)";

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getApelido());
            pstm.setDate(3, new Date(usuario.getData_cadastro().getTime()));
            pstm.setString(4, usuario.getEmail());
            pstm.setString(5, usuario.getSenha());
            pstm.setString(6, usuario.getNacionalidade());
            pstm.setString(7, usuario.getGenero());
            pstm.setDate(8, new Date(usuario.getData_nascimento().getTime()));

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    public boolean deleteById(int id) {

        String sql = "DELETE FROM usuarios WHERE id = ?";
        boolean result = false;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            pstm.execute();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void updateById(Usuario usuario){

        String sql = "UPDATE usuarios SET nome = ?,apelido = ?,data_cadastro = ?,email = ?,senha = ?," +
                "nacionalidade = ?,genero = ?,data_nascimento = ? WHERE id = ?";

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getApelido());
            pstm.setDate(3, new Date(usuario.getData_cadastro().getTime()));
            pstm.setString(4, usuario.getEmail());
            pstm.setString(5, usuario.getSenha());
            pstm.setString(6, usuario.getNacionalidade());
            pstm.setString(7, usuario.getGenero());
            pstm.setDate(8, new Date(usuario.getData_nascimento().getTime()));
            pstm.setInt(9,usuario.getId());

            // Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    public List<Usuario> getUsuarios() {

        String sql = "SELECT * FROM usuarios";
        ResultSet rset = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setApelido(rset.getString("apelido"));
                usuario.setData_cadastro(rset.getDate("data_cadastro"));
                usuario.setEmail(rset.getString("email"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setNacionalidade(rset.getString("nacionalidade"));
                usuario.setGenero(rset.getString("genero"));
                usuario.setData_nascimento(rset.getDate("data_nascimento"));
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fecha as conexões
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return usuarios;
    }

    public Usuario getUsuarioById(int id) {

        String sql = "SELECT * FROM usuarios WHERE id = ?";
        ResultSet rset = null;
        Usuario usuario = new Usuario();
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setApelido(rset.getString("apelido"));
                usuario.setData_cadastro(rset.getDate("data_cadastro"));
                usuario.setEmail(rset.getString("email"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setNacionalidade(rset.getString("nacionalidade"));
                usuario.setGenero(rset.getString("genero"));
                usuario.setData_nascimento(rset.getDate("data_nascimento"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fecha as conexões
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return usuario;
    }

}
