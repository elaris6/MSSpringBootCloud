package com.local.contactos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.local.contactos.model.Contacto;


/* De forma similar a la clase "CrudRepository<E,T>", la clase "JpaRepository<E,T>"
permite que Spring implemente de manera automática los métodos CRUD estándar, para
que solo se deban implementar los más específicos que requiera la aplicación.
La clase "JpaRepository<E,T>" a su vez implementa la "CrudRepository<E,T>" y añade
mejoras.
https://www.baeldung.com/spring-data-repositories */
public interface ContactoJpaSpring extends JpaRepository<Contacto, Integer>{

	/* Aunque la herencia de "JpaRepository<E,T>" incluye todos los métodos CRUD básicos,
    es posible incluir los métodos personalizados que sean necesarios de forma adicional. */
    
    /* Ejemplo de método de búsqueda por un campo, usando las conveciones de Spring JPA.
    Hay bastantes convenciones ya creadas que permiten realizar queries sin necesidad de
    escribir el código JPQL. */
	
	/* Búsqueda por un campo concreto. El nombre del campo en el método deb ser exactamente
    igual que en la clase entidad.*/
	Contacto findByEmail(String email);
	/* Búsqueda combinada por dos campos. */
    public Contacto findByEmailAndTelefono(String email, String telefono);
    /* Búsqueda por un campo que contenga un substring ignorando además las mayúsc/minusc. */
    public List<Contacto> findEmailContainingIgnoreCase(String cadena);
	
	/* Definición de método con JPQL personalizado para realizar misma búsuqeda que 
	 * ejemplo previo con convenciones SringJPA. */
    @Query("SELECT c FROM Contacto c WHERE c.email LIKE %:cadena%")
    public List<Contacto> buscarContactoPorEmail(@Param("cadena") String cadena);
    /* Misma consulta con parámetros ordenados. */
    //@Query("SELECT c FROM Contacto c WHERE c.email LIKE ?1%")
    //public List<Contacto> buscarContactoPorEmail(String cadena);
    
    /* Definición de método transaccional personalizado.
    Este método debe anotarse con "Transactional" and "Modifying", para que la operación
    se realice de manera consistente al ser una modificación de la BBDD. */
    @Transactional
    @Modifying
    @Query("DELETE FROM Contacto c WHERE c.email=?1%")
    void eliminarPorEmail(String email);
	
	
}
