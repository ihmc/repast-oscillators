package us.ihmc.simon.repositories;

import java.util.Random;

import repast.simphony.engine.watcher.Watch;
import repast.simphony.engine.watcher.WatcherTriggerSchedule;
import repast.simphony.space.continuous.ContinuousSpace;

public class NaturalRepository extends Repository {
	/**
	 * 
	 */

	private double weight;
	private double mean_weight = 32.3319;
	private double sd = 2.5804;
	private double noise_strength = 0.5;

	private double inv_sf = 2 * Math.PI / 365.0;

	private final double K = 1.89753;

	public NaturalRepository(ContinuousSpace<Repository> space, String id, RepositoryData data) {
		super(space, id, data);
		Random r = new Random();
//		weight = r.nextGaussian() * sd / 4 + mean_weight; // stddev and mean from
														// https://ruthcfong.github.io/kuramoto/
		weight = data.getMean();
//		setPhase(Math.random() * Math.PI * 2);
		
		setX(100 + getRadius() * Math.cos(getPhase()));
		setY(100 + getRadius() * Math.sin(getPhase())); // counter-clockwise
	}

	public void step() {
		move();
		setX(100 + getRadius() * Math.cos(getPhase()));
		setY(100 + getRadius() * Math.sin(getPhase())); 
		getSpace().moveTo(this, getX(), getY());
	}

	public double getPsi() {
		return weight;
	}

	private double modulo(double x, double y) {
//		while(x > y){
//			x -= y;
//		}
//		return x;
		return ((x % y) + y) % y;
	}

	private double randn(double mean, double std)
	{
		return mean + std
				* ((Math.random() + Math.random() + Math.random() + Math.random() + Math.random() + Math.random()) - 3)
				* Math.sqrt(2);
	}

	public void move() {
		double oldPhase = getPhase();
		double dthdt = weight;

		double noise = noise_strength * randn(0, Math.sqrt(inv_sf));

//		double newPhase = oldPhase + dthdt * inv_sf + noise;
//		double newPhase = oldPhase + dthdt + noise;
		double newPhase = oldPhase + dthdt  * inv_sf;
//		System.out.println("Psi:\t" + psi + "Phase:\t" + newPhase + " r:\t" + r + " Dth/Dt:\t" + dthdt);
		setPhase(newPhase);
	}

}
