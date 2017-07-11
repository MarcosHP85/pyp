package ar.nasa.pyp.rdn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Equipos", schema = "dbo", catalog = "Datos_IFS")
public class Equipo {

	private String objId;
	private String mchCode;
	private Integer contract;
	private String mchName;
	
	@Id
	@Column(name = "OBJID")
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	@Column(name = "MCH_CODE")
	public String getMchCode() {
		return mchCode;
	}
	public void setMchCode(String mchCode) {
		this.mchCode = mchCode;
	}
	
	@Column(name = "CONTRACT")
	public Integer getContract() {
		return contract;
	}
	public void setContract(Integer contract) {
		this.contract = contract;
	}
	
	@Column(name = "MCH_NAME")
	public String getMchName() {
		return mchName;
	}
	public void setMchName(String mchName) {
		this.mchName = mchName;
	}
}
