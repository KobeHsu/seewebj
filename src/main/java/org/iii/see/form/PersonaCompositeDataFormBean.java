package org.iii.see.form;


public class PersonaCompositeDataFormBean extends BaseFormBean {

	private static final long serialVersionUID = -7567769152031167459L;

	private String uuid;

	private String projectUuid;

	private String personaNo;
	
	private String realname;

	private String category;

	private String background;

	private String behavior;

	private String target;
	
	private String portraitFileUuid;
	
	private String portraitFileName;	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public String getPersonaNo() {
		return personaNo;
	}

	public void setPersonaNo(String personaNo) {
		this.personaNo = personaNo;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getPortraitFileUuid() {
		return portraitFileUuid;
	}

	public void setPortraitFileUuid(String portraitFileUuid) {
		this.portraitFileUuid = portraitFileUuid;
	}

	public String getPortraitFileName() {
		return portraitFileName;
	}

	public void setPortraitFileName(String portraitFileName) {
		this.portraitFileName = portraitFileName;
	}

}
