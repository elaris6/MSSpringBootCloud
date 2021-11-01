package es.local.ms.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import es.local.ms.model.Empleado;

@Service
public class EmpleadosServiceImpl implements EmpleadosService {

	private List<Empleado> listaEmpleados = Arrays.asList(
			new Empleado("Empleado Uno",1,"Contabilidad"),
			new Empleado("Empleado Dos",2,"Comercial"),
			new Empleado("Empleado Tres",3,"Coordinador"),
			new Empleado("Empleado Cuatro",4,"Desarrollador"),
			new Empleado("Empleado Cinco",5,"Desarrollador"),
			new Empleado("Empleado Seis",6,"Desarrollador"));
	
	
	@Override
	public List<Empleado> empleados() {

		return listaEmpleados;
	}

}
