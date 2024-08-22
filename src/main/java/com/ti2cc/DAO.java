package com.ti2cc;


import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "Reinos_Medievais";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirReino(Reinos reino) {
	    boolean status = false;
	    try {  
	        Statement st = conexao.createStatement();
	        st.executeUpdate("INSERT INTO Reinos (codigo, nome, pais, ano) "
	                       + "VALUES (" + reino.getCodigo() + ", '" + reino.getNome() + "', '"  
	                       + reino.getPais() + "', " + reino.getAno() + ");");
	        st.close();
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}

	public boolean atualizarReino(Reinos reino) {
	    boolean status = false;
	    try {  
	        Statement st = conexao.createStatement();
	        String sql = "UPDATE Reinos SET nome = '" + reino.getNome() + "', pais = '"  
	                   + reino.getPais() + "', ano = " + reino.getAno()
	                   + " WHERE codigo = " + reino.getCodigo();
	        st.executeUpdate(sql);
	        st.close();
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}

	public boolean excluirReino(int codigo) {
	    boolean status = false;
	    try {  
	        Statement st = conexao.createStatement();
	        st.executeUpdate("DELETE FROM Reinos WHERE codigo = " + codigo);
	        st.close();
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}

	public Reinos[] getReinos() {
	    Reinos[] reinos = null;
	    
	    try {
	        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = st.executeQuery("SELECT * FROM Reinos");		
	        if(rs.next()) {
	            rs.last();
	            reinos = new Reinos[rs.getRow()];
	            rs.beforeFirst();

	            for(int i = 0; rs.next(); i++) {
	                reinos[i] = new Reinos(rs.getInt("codigo"), rs.getString("nome"), 
	                                       rs.getString("pais"), rs.getInt("ano"));
	            }
	        }
	        st.close();
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }
	    return reinos;
	}

}
	
	