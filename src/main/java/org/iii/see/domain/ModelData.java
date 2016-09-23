package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "MODEL_DATA")
public class ModelData implements Serializable {

  private static final long serialVersionUID = -5645541133591575359L;

  @Id
  @Column(name = "UUID", length = 36)
  private String uuid;

  @Column(name = "TYPE")
  private short type;

  @Column(name = "NAME", length = 64)
  private String name;

  @Column(name = "CONTENT", length = 2147483647)
  private String content;

  @Column(name = "UPDATE_TIME")
  private Timestamp updateTime;

  @Column(name = "ENABLE", length = 1)
  private String enable;

  public String getUuid() {
    return uuid;
  }

  public ModelData() {
    super();
  }

  public ModelData(String uuid, short type, String name, String content, Timestamp updateTime,
      String enable) {
    super();
    this.uuid = uuid;
    this.type = type;
    this.name = name;
    this.content = content;
    this.updateTime = updateTime;
    this.enable = enable;
  }

  public short getType() {
    return type;
  }

  public void setType(short type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public String getEnable() {
    return enable;
  }

  public void setEnable(String enable) {
    this.enable = enable;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

}
