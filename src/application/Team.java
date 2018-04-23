package application;

public class Team {
	
	private String name;
	private int rank;
	
	public Team(){
	}
	
	public Team(String name, int rank){
		this.name = name;
		this.rank = rank;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getRank(){
		return this.rank;
	}

}
