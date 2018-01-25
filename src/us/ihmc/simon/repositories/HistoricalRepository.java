package us.ihmc.simon.repositories;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.continuous.ContinuousSpace;

public class HistoricalRepository extends Repository {
	/**
	 * 
	 */

	private int timer;
	private int duration;
	private double dailyMovement;
	public boolean moved;
	
	public HistoricalRepository(ContinuousSpace<Repository> space, String id, RepositoryData data) {
		super(space,id,data);
		timer = 0;
		duration = 0;
		dailyMovement=0;
		moved = false;
	}

	public void step() {
		if(timer == 0 ){
			if(hasNext()){
				int gap = getNext().intValue();
				duration = gap;
				dailyMovement = 1.0/gap;
				timer = gap - 1;
				System.out.println("Changed:\t" + moved);
				moved = !moved;
			} else {
				RunEnvironment.getInstance().endRun();
			}
		} else {
			timer--;
		}
		double time = (duration - timer) * dailyMovement * Math.PI * 2;

	    setX(100 + getRadius() * Math.cos(getPhase() + time));
	    setY(100 + getRadius() * -Math.sin(getPhase() + time)); // counter-clockwise
	    getSpace().moveTo(this, getX(), getY());
	}
	
}
