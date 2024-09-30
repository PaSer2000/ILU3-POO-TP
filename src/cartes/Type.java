package cartes;

public enum Type {
	FEU("FeuRouge", "FeuVert", "VehiculePrioritaire"),
    ESSENCE("1","2","3"),
    CREVAISON("1","2","3"),
    ACCIDENT("1","2","3");
	
    private String attaque;
    private String parade;
    private String botte;
    
    Type(String attaque, String parade, String botte) {
        this.attaque = attaque;
        this.parade = parade;
        this.botte = botte;
    }

	public String getAttaque() {
		return attaque;
	}
		
	public String getParade() {
	    return parade;
	}

	public String getBotte() {
	    return botte;
	}	
}
