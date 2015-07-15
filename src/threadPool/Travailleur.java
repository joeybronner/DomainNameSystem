package threadPool;

public class Travailleur extends Thread{

	private Integer idTravailleur;
	
	private Pool pool;
	
	public Travailleur(Pool pool, Integer idTravailleur) {
		this.pool = pool;
		this.idTravailleur = idTravailleur;
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println("Le Job commence !");
			pool.prochaineTache().run();
			System.out.println(idTravailleur + " ends job");
		}
	}
}
