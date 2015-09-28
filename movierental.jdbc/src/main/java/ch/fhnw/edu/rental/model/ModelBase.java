package ch.fhnw.edu.rental.model;

public abstract class ModelBase {
	private boolean deleted = false;

	public boolean isDeleted(){
		return deleted;
	}

	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}
}
