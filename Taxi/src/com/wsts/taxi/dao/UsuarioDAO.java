package com.wsts.taxi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wsts.taxi.interfaces.UsuarioInterfaceDAO;
import com.wsts.taxi.pojo.UsuarioVO;
import com.wsts.taxi.sql.UsuarioSQL;
import com.wsts.taxi.util.ConnectionFactory;

public class UsuarioDAO implements UsuarioInterfaceDAO{

	public List<UsuarioVO> usuarioList(String sql, UsuarioVO usuarioVO)
			throws Exception {
		List<UsuarioVO> list = new ArrayList<UsuarioVO>();

		try {
			Connection conn = ConnectionFactory.getConnection();
			CallableStatement cs = null;
			cs = conn.prepareCall(sql);
			cs.setString(1, usuarioVO.getMatricula());
			ResultSet rs = cs.executeQuery(UsuarioSQL.listaUsuario);

			while (rs.next()) {
				usuarioVO = new UsuarioVO();

				usuarioVO.setId(rs.getInt("ISS_ID"));
				usuarioVO.setMatricula(rs.getString(""));

				list.add(usuarioVO);
			}

			conn.close();

		} catch (Exception ex) {
			throw new Exception("Erro na criação do banco de dados<br />"
					+ ex.getMessage());
		}

		return list;
	}
}
