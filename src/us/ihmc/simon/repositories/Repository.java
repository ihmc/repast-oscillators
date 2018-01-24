package us.ihmc.simon.repositories;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;

import java.util.Iterator;

import repast.simphony.data2.AggregateDataSource;

public class Repository implements AggregateDataSource {
	/**
	 * 
	 */
	private ContinuousSpace<Repository> space;
	private double radius = 40;
	private RunEnvironment state;
	private double dy;
	private double dx;
	private double phase;
	String id;
	RepositoryData data;

	public Repository(ContinuousSpace<Repository> space, String id, RepositoryData data) {
		this.space = space;
		state = RunEnvironment.getInstance();
		this.id = id;
		dx =  100 + radius * Math.cos(0);
	    dy = 100 + radius * -Math.sin(0);
		this.data = data;
		this.phase = 0;
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step(){
		
	}
	
	@Override
	public String getId(){
		return id;
	}

	public void setY(double y){
		this.dy = y;
	}
	public double getY(){
		return dy;
	}
	
	public void setX(double x){
		this.dx = x;
	}

	public double getX(){
		return dx;
	}

	public double getRadius(){
		return radius;
	}
	
	public ContinuousSpace<Repository> getSpace(){
		return space;
	}

	public RunEnvironment getState(){
		state.setScheduleTickDelay(4);
		return state;
	}
	
	public void setPhase(double phase){
		this.phase = phase;
	}
	
	public double getPhase(){
		return phase;
	}
	
	public boolean hasNext(){
		return data.hasNext();
	}
	
	public Integer getNext(){
		return data.getNext();
	}
	

	  /*
	   * (non-Javadoc)
	   * 
	   * @see repast.simphony.data2.DataSource#getDataType()
	   */
	  @Override
	  public Class<Double> getDataType() {
	    return Double.class;
	  }
	  /*
	   * (non-Javadoc)
	   * 
	   * @see repast.simphony.data2.AggregateDataSource#get(java.lang.Iterable, int)
	   */
	  @Override
	  public Double get(Iterable<?> objs, int size) {
		  double cos = 0;
		  double sin = 0;
		  for (Iterator iterator = objs.iterator(); iterator.hasNext();) {
			Repository repo = (Repository) iterator.next();
			cos += Math.cos(repo.getPhase());
			sin += Math.sin(repo.getPhase());
		}
		  
		return new Double(Math.sqrt(Math.pow(sin/size, 2) + Math.pow(cos/size, 2)));
	  }	  


	  /*
	   * (non-Javadoc)
	   * 
	   * @see repast.simphony.data2.AggregateDataSource#reset()
	   */
	  @Override
	  public void reset() {
	  }

	  /* (non-Javadoc)
	   * @see repast.simphony.data2.DataSource#getSourceType()
	   */
	  @Override
	  public Class<?> getSourceType() {
	    return Repository.class;
	  }
	  
}
