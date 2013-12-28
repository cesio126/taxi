package com.wsts.taxi.interfaces;

import java.util.List;

import com.wsts.taxi.pojo.UsuarioVO;

public interface UsuarioInterfaceDAO {
	public List<UsuarioVO> usuarioList(String sql, UsuarioVO usuarioVO)	throws Exception;
}
