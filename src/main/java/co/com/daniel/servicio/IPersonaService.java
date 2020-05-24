package co.com.daniel.servicio;

import co.com.daniel.domain.Persona;

import java.util.List;

public interface IPersonaService {
    List<Persona> listarPersonas();
    void guardar(Persona persona);
    void eliminar(Persona persona);
    Persona encontrarPersona(Persona persona);
}
