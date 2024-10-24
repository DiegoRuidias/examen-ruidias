package com.ruidias.Examen01.service.impl;

import com.ruidias.Examen01.entity.Infracciones;
import com.ruidias.Examen01.exception.GeneralException;
import com.ruidias.Examen01.exception.NoDataFoundException;
import com.ruidias.Examen01.exception.ValidateException;
import com.ruidias.Examen01.repository.InfraccionRepository;
import com.ruidias.Examen01.service.InfraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("infraccionService")
public class InfraccionServiceImpl implements InfraccionService {

    @Autowired
    private InfraccionRepository repository;

    @Autowired
    InfraccionRepository infraccionRepository;
    @Override
    public List<Infracciones> findAll() {
        try {
            return infraccionRepository.findAll();
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    @Transactional
    public Infracciones save(Infracciones infracciones) {
        try {
            if(infracciones.getId() == 0) {
                Infracciones nuevo = repository.save(infracciones);
                return nuevo;
            }

            Infracciones registro = repository.findById(infracciones.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            registro.setInfraccion(infracciones.getInfraccion());
            registro.setDescripcion(infracciones.getDescripcion());
            registro.setPlaca(infracciones.getPlaca());
            registro.setFecha(infracciones.getFecha());
            repository.save(registro);
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Infracciones infracciones = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            repository.delete(infracciones);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Infracciones findById(int id) {
        try {
            Infracciones registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }
}
