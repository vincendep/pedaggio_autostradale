package business.manager;

import business.model.Casello;
import business.model.Normativa;
import business.model.Pedaggio;
import business.model.Veicolo;

public class PedaggioMgr {

	private PedaggioMgr() {}
	
	public static PedaggioMgr getInstance() {
		return new PedaggioMgr();
	}
	
	public Pedaggio calcolaPedaggio(Casello e, Casello u, Veicolo v) throws ManagerException {
		
		if (e.equals(u) || ! (e.getAutostrada().equals(u.getAutostrada()))) throw new ManagerException();
		
		Normativa normativa = e.getAutostrada().getNormativaVigente();
		return normativa.calcolaPedaggio(v, e, u);
	}
}
