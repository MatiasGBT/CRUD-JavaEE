package crud.datos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import crud.domain.Persona;

@Stateless //Esta clase debe de ser un EJB para que pueda inyectarse el EntityManager de JPA
public class PersonaDaoImpl implements PersonaDao{
    
    @PersistenceContext(unitName="SgaPU")
    EntityManager em;

    @Override
    public List<Persona> findAllPersonas() {
        return em.createNamedQuery("Persona.findAll").getResultList();
    }

    @Override
    public Persona findPersonaById(Persona persona) {
        return (Persona) em.createNamedQuery("Persona.findByIdPersona").getSingleResult();
    }

    @Override
    public Persona findPersonaByEmail(Persona persona) {
        return (Persona) em.createNamedQuery("Persona.findByEmail").getSingleResult();
    }

    @Override
    public void insertPersona(Persona persona) {
        em.persist(persona);
    }

    @Override
    public void updatePersona(Persona persona) {
        em.merge(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
        em.remove(em.merge(persona)); //Primero se tiene que actualizar el estado del objeto antes de borrarlo
    }
}
