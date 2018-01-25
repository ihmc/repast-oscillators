package us.ihmc.simon.repositories;


import repast.simphony.engine.watcher.Watch;
import repast.simphony.engine.watcher.WatcherTriggerSchedule;
import repast.simphony.space.continuous.ContinuousSpace;

public class SimulatedRepository extends Repository {
	/**
	 * 
	 */
	
	private final double KURAMOTO_CONSTANT = 1.89753;
	private double psi;

	public SimulatedRepository(ContinuousSpace<Repository> space, String id, RepositoryData data) {
		super(space,id,data);
		psi = data.getMean();
		
	}
	
	public void step() {
		double time = getState().getCurrentSchedule().getTickCount() / psi * Math.PI * 2;
	    setX(100 + getRadius() * Math.cos(getPhase() + time));
	    setY(100 + getRadius() * -Math.sin(getPhase() + time)); // counter-clockwise
	    getSpace().moveTo(this, getX(), getY()); 
	}
	
	public double getPsi(){
		return psi;
	}
	

	@Watch(watcheeClassName = "us.ihmc.simon.repositories.HistoricalRepository",
			watcheeFieldNames = "moved",
			whenToTrigger = WatcherTriggerSchedule.IMMEDIATE)
	public void kuramoto(){
		double oldPhase = getPhase();
		System.out.println("Kuramoto:\t");
		int n = getSpace().size();
		Iterable<Repository> iterator = getSpace().getObjects();
		
		double sum = 0;
		for (Repository repository : iterator) {
			sum += Math.sin(repository.getPhase() - getPhase());
		}
		
		double newPhase = oldPhase + psi + KURAMOTO_CONSTANT/n  * sum;
		setPhase(newPhase);	
	}
	
}
