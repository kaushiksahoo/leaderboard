package com.leaderboard;

public class Spends {
	
	private String name;
	private int spends;
	
	public Spends(String name, int spends) {
		super();
		this.name = name;
		this.spends = spends;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSpends() {
		return spends;
	}
	
	public void addSpends(int spends) {
		this.spends += spends;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + spends;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spends other = (Spends) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (spends != other.spends)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Spends [name=" + name + ", spends=" + spends + "]";
	}
}
