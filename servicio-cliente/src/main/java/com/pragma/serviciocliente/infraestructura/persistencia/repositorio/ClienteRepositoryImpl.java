package com.pragma.serviciocliente.infraestructura.persistencia.repositorio;

import com.pragma.serviciocliente.dominio.repositorio.ClienteRepositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.dao.ClienteDaoInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepositorioInterfaz {

    @Autowired
    private ClienteDaoInterfaz clienteDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteEntidad>> obtenerPorEdadMayorIgual(Integer edad) {
        return clienteDao.findByEdadGreaterThanEqual(edad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteEntidad> obtenerTodo() {
        return (List<ClienteEntidad>) clienteDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(ClienteEntidad clienteEntidad) {
        clienteDao.save(clienteEntidad);
    }

    @Override
    @Transactional
    public void eliminar(ClienteEntidad clienteEntidad) {
        clienteDao.delete(clienteEntidad);
    }
}
