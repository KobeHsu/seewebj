package org.iii.see.form.datamanagement;

import org.iii.see.domain.CaseExtraData;
import org.iii.see.domain.CaseExtraDefinition;
import org.iii.see.form.BaseFormBean;


public class CaseExtraDataFormBean extends BaseFormBean {
	
	private static final long serialVersionUID = 6662121947266388185L;

	private CaseExtraDefinition definition;
	
	private CaseExtraData data;

	public CaseExtraDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(CaseExtraDefinition definition) {
		this.definition = definition;
	}

	public CaseExtraData getData() {
		return data;
	}

	public void setData(CaseExtraData data) {
		this.data = data;
	}

}
