package us.ihmc.simon.repositories;

import java.util.Random;

import repast.simphony.engine.watcher.Watch;
import repast.simphony.engine.watcher.WatcherTriggerSchedule;
import repast.simphony.space.continuous.ContinuousSpace;

public class SimulatedRepository extends Repository {
	/**
	 * 
	 */

	private double weight;
	private double mean_weight = 32.3319;
	private double sd = 2.5804;
	private double noise_strength = 0.5;

	private double inv_sf = 2 * Math.PI / 365.0;

	private final double K = 1.89753;

	public SimulatedRepository(ContinuousSpace<Repository> space, String id, RepositoryData data) {
		super(space, id, data);
		Random r = new Random();

		weight = data.getMean();
		// setPhase(Math.random() * Math.PI * 2);

		setX(100 + getRadius() * Math.cos(getPhase()));
		setY(100 + getRadius() * Math.sin(getPhase())); // counter-clockwise
	}

	public void step() {
		kuramoto();
		setX(100 + getRadius() * Math.cos(getPhase()));
		setY(100 + getRadius() * Math.sin(getPhase())); // counter-clockwise
		getSpace().moveTo(this, getX(), getY());
	}

	public double getPsi() {
		return weight;
	}

	private double modulo(double x, double y) {
		return ((x % y) + y) % y;
	}

	private double randn(double mean, double std) {
		return mean + std
				* ((Math.random() + Math.random() + Math.random() + Math.random() + Math.random() + Math.random()) - 3)
				* Math.sqrt(2);
	}

	// @Watch(watcheeClassName =
	// "us.ihmc.simon.repositories.HistoricalRepository",
	// watcheeFieldNames = "moved",
	// whenToTrigger = WatcherTriggerSchedule.IMMEDIATE)
	public void kuramoto() {
		double oldPhase = getPhase();
		int n = getSpace().size();

		double sin = 0;
		double cos = 0;
		Iterable<Repository> iterator = getSpace().getObjects();

		double sum = 0;
		for (Repository repository : iterator) {
			if (repository instanceof SimulatedRepository) {
				sin += Math.sin(repository.getPhase());
				cos += Math.cos(repository.getPhase());
				sum += Math.sin(repository.getPhase() - getPhase());
			}
		}

		sin /= n;
		cos /= n;
		sum /= n;
		double r = Math.sqrt(Math.pow(sin, 2) + Math.pow(cos, 2));
		double psi = modulo(Math.atan2(sin, cos), 2 * Math.PI);
		double dthdt = weight + K * r * Math.sin(psi - getPhase());
		// double dthdt = weight + K * sum;

		double noise = noise_strength * randn(0, Math.sqrt(inv_sf));

		// double newPhase = oldPhase + dthdt * inv_sf + noise;
		// double newPhase = oldPhase + dthdt + noise;
		double newPhase = oldPhase + dthdt * inv_sf;
		// System.out.println("Psi:\t" + psi + "Phase:\t" + newPhase + " r:\t" +
		// r + " Dth/Dt:\t" + dthdt);
		setPhase(newPhase);
	}

}
