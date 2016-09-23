package org.iii.see.form;

import java.sql.Timestamp;

import org.iii.see.domain.ModelData;

public class ModelDataFormBean extends BaseFormBean {

  private static final long serialVersionUID = 4672536190138083912L;

  private ModelData modelData;

  public ModelData getModelData() {
    return modelData;
  }

  public void setModelData(ModelData modelData) {
    this.modelData = modelData;
  }

  public ModelDataFormBean() {
    super();
    this.modelData = new ModelData();
  }

  public ModelDataFormBean(ModelData modelData) {
    super();
    this.modelData = modelData;
  }

  public String getUuid() {
    return modelData.getUuid();
  }

  public short getType() {
    return modelData.getType();
  }

  public void setType(short type) {
    modelData.setType(type);
  }

  public String getName() {
    return modelData.getName();
  }

  public void setName(String name) {
    modelData.setName(name);
  }

  public String getContent() {
    return modelData.getContent();
  }

  public void setContent(String content) {
    modelData.setContent(content);
  }

  public Timestamp getUpdateTime() {
    return modelData.getUpdateTime();
  }

  public void setUpdateTime(Timestamp updateTime) {
    modelData.setUpdateTime(updateTime);
  }

  public String getEnable() {
    return modelData.getEnable();
  }

  public void setEnable(String enable) {
    modelData.setEnable(enable);
  }

  public void setUuid(String uuid) {
    modelData.setUuid(uuid);
  }

  public String toString() {
    return modelData.toString();
  }

  // public int hashCode() {
  // return modelData.hashCode();
  // }
  //
  // public boolean equals(Object obj) {
  // return modelData.equals(obj);
  // }

}
