package co.com.daniel.dao;


import co.com.daniel.domain.Persona;
import org.springframework.data.repository.CrudRepository;

public interface IPersonaDAO extends CrudRepository <Persona, Integer> {

}
