package pinbowling.model;

public enum BowlingRules {
	MAX_PINES("10"," max number of pines per line"),
	STRIKE("10"," is a strike"),
	MAX_FRAME("10"," max number of frames"),
	INVALID_PINES_VALUE("10","Is not a valid value for pinsfall"),
	FILE_ERROR("","The value insert is not a valid path file");
	
	private String rule;
	private String description;

	
	private BowlingRules(String rule, String description) {
		this.rule = rule;
		this.description = description;
	}

	public String getRule() {
		return rule;
	}

	public String getDescription() {
		return description;
	}

}
