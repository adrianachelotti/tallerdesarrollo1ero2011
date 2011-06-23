package hibernate.domain.vehiculos;



import hibernate.AdministradorConductores;
import hibernate.domain.conductores.Conductor;

public class ScoringVehicular {

	private Conductor conductor;
	
	
	public void actualizarDatos(String[] celdas){
		this.conductor = AdministradorConductores.obtenerConductor(celdas[0],Integer.parseInt(celdas[1]));
		if (conductor!=null){
			conductor.setSaldoScoring(Integer.parseInt(celdas[4]));
			conductor.setCantidadCerosEnScoring(Integer.parseInt(celdas[5]));
		}
		System.out.println("conductor inexistente");
	}
	public void actualizar(){
		if (this.conductor!=null){
			AdministradorConductores.agregarConductor(this.conductor);
		}
	}

}

