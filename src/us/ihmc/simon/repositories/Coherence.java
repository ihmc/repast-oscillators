package us.ihmc.simon.repositories;

import java.util.Iterator;

import repast.simphony.data2.AggregateDataSource;

public class Coherence  implements AggregateDataSource {
	  private String id;
	  private Class<?> sourceType;

	  /**
	   * @param id
	   */
	  public Coherence() {
	    this.id = "Coherence";
	    this.sourceType = Repository.class;
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

	@Override
	public String getId() {
		return id;
	}
}
