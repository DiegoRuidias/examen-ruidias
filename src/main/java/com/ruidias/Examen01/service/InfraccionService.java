package com.ruidias.Examen01.service;

import com.ruidias.Examen01.entity.Infracciones;


import java.util.List;

public interface InfraccionService {
    public List<Infracciones> findAll();
    public Infracciones save(Infracciones infracciones);
    public void delete(int id);
    public Infracciones findById(int id);
}
