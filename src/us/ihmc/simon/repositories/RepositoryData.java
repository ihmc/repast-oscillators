package us.ihmc.simon.repositories;

import java.io.FileReader;
import java.io.Reader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class RepositoryData {

	private ArrayList<Integer> arrivals;
	private ArrayList<Integer> intervals;
	private Frequency frequency;
	DescriptiveStatistics stats;
	Stack<Integer> stack;
	
	private double RADIANS_PER_HOUR = 0.000716782378;
	private double RADIANS_PER_DAY = RADIANS_PER_HOUR * 24;
	
	private Integer DAY_CONVERTER= new Integer(1000*60*60*24);
	
	public RepositoryData(String path){
		System.out.println("constructing:\t" + path);
		arrivals = new ArrayList<Integer>();
		try{
		Reader in = new FileReader(path);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withQuote(null).withHeader().parse(in);
		
		// The creation date column comes from github and is milliseconds since epoch. We convert it to days
		for (CSVRecord record : records) {
			Integer day = millisecondsToDays(new BigInteger(record.get("creation_date")));
			arrivals.add(day);
		}
		
		Collections.sort(arrivals);
		System.out.println("Converting");
		
		// Since we're working with oscillations, we want to know the frequency. We first figure out how long each event takes to arrive
		setup();
		} catch (Exception e){
			System.out.println("Err");
			System.err.println(e.getMessage());
		}
	}
	
	private void setup(){
		stack = new Stack<Integer>();
		intervals = convertToIntervals(arrivals);
	}
	
	public double getFrequencyInRadians(){
		return 356 / getMean() * RADIANS_PER_DAY;
	}
	
	private Integer millisecondsToDays(BigInteger milliseconds){
		int days = (milliseconds.divide(new BigInteger(DAY_CONVERTER.toString()))).intValue();
		return new Integer(days);
	}
	
	private ArrayList<Integer> convertToIntervals(ArrayList<Integer> arrivals){
		ArrayList<Integer> intervals = new ArrayList<Integer>();
		frequency = new Frequency();
		stats = new DescriptiveStatistics();
		System.out.println("created stats");
				
		if(arrivals.size() == 0)
			return intervals;
		if(arrivals.size() == 1){
			intervals.add(new Integer(0));
			return intervals;
		}
			
		Integer prev = arrivals.get(0);
		for (int i = 1; i < arrivals.size(); i++) {
			Integer curr = arrivals.get(i);
			Integer diff = curr - prev;
			if(diff > 1){
				intervals.add(diff);
				stats.addValue(diff);
				stack.push(diff);
			}
			prev = curr;
		}
				
		return intervals;
	}
	
	public boolean hasNext(){
		return !stack.isEmpty();
	}

	public Integer getNext(){
		return stack.pop();
	}
	public Integer peek(){
		return stack.peek();
	}
	
	public double getMean(){
		return stats.getMean();
	}
	
	public double getStandardDeviation(){
		return stats.getStandardDeviation();
	}
	
	public int count(){
		for (Iterator iterator = arrivals.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
		}
		return arrivals.size();
	}
	
	public Integer getFirst(){
		return arrivals.get(0);
	}

	public void truncateToCoupling(BigInteger couplingEnd){
		Integer cutoff = millisecondsToDays(couplingEnd);
		for (Iterator iterator = arrivals.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			if(integer.compareTo(cutoff) > 0 ){
				iterator.remove();
			}
		}
		setup();
	}
	public void trimToCoupling(BigInteger couplingStart){
		Integer cutoff = millisecondsToDays(couplingStart);
		for (Iterator iterator = arrivals.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			if(integer.compareTo(cutoff) < 0 ){
				iterator.remove();
			}
		}
		setup();
	}
}
