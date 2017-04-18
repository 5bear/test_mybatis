package com.jufan.model;

import com.jufan.core.common.utils.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@SuppressWarnings("serial")
public class TiUserInf extends BaseModel {

	/** 主键ID */
	private String id;
	/** 登录名 */
	private String loginName;
	/** 登录密码 */
	private String loginPassword;
	/** 姓名 */
	private String userName;
	/** 英文名 */
	private String nickName;
	/** 身份证号 */
	private String idNo;
	/** 工号 */
	private String cardNo;
	/** 护照号 */
	private String passportNo;
	/** 邮件 */
	private String email;
	/** 手机号 */
	private String mobile;
	/** 所属机构ID */
	private String belongOrgId;
	/** 座机 */
	private String telNo;
	/** 内线 */
	private String innerTelNo;
	/** 帐号状态 */
	private String initStatus;
	/** 上次修改密码时间 */
	private String lastedModify;
	/** 记录创建时间 */
	private Date createDatetime;
	/** 记录创建人 */
	private String createOperatorId;
	/** 最后修改时间 */
	private Date lastUpdateDatetime;
	/** 最后修改者ID */
	private String lastUpdateOperatorId;
	/** 数据删除状态 */
	private Boolean dataStatus;

	public String getId(){
		return id;
  	} 
  	
  	public void setId(String id){
  		this.id=id;
  	}
  	
	public String getLoginName(){
		return loginName;
  	} 
  	
  	public void setLoginName(String loginName){
  		this.loginName=loginName;
  	}
  	
	public String getLoginPassword(){
		return loginPassword;
  	} 
  	
  	public void setLoginPassword(String loginPassword){
  		this.loginPassword=loginPassword;
  	}
  	
	public String getUserName(){
		return userName;
  	} 
  	
  	public void setUserName(String userName){
  		this.userName=userName;
  	}
  	
	public String getNickName(){
		return nickName;
  	} 
  	
  	public void setNickName(String nickName){
  		this.nickName=nickName;
  	}
  	
	public String getIdNo(){
		return idNo;
  	} 
  	
  	public void setIdNo(String idNo){
  		this.idNo=idNo;
  	}
  	
	public String getCardNo(){
		return cardNo;
  	} 
  	
  	public void setCardNo(String cardNo){
  		this.cardNo=cardNo;
  	}
  	
	public String getPassportNo(){
		return passportNo;
  	} 
  	
  	public void setPassportNo(String passportNo){
  		this.passportNo=passportNo;
  	}
  	
	public String getEmail(){
		return email;
  	} 
  	
  	public void setEmail(String email){
  		this.email=email;
  	}
  	
	public String getMobile(){
		return mobile;
  	} 
  	
  	public void setMobile(String mobile){
  		this.mobile=mobile;
  	}
  	
	public String getBelongOrgId(){
		return belongOrgId;
  	} 
  	
  	public void setBelongOrgId(String belongOrgId){
  		this.belongOrgId=belongOrgId;
  	}
  	
	public String getTelNo(){
		return telNo;
  	} 
  	
  	public void setTelNo(String telNo){
  		this.telNo=telNo;
  	}
  	
	public String getInnerTelNo(){
		return innerTelNo;
  	} 
  	
  	public void setInnerTelNo(String innerTelNo){
  		this.innerTelNo=innerTelNo;
  	}
  	
	public String getInitStatus(){
		return initStatus;
  	} 
  	
  	public void setInitStatus(String initStatus){
  		this.initStatus=initStatus;
  	}
  	
	public String getLastedModify(){
		return lastedModify;
  	} 
  	
  	public void setLastedModify(String lastedModify){
  		this.lastedModify=lastedModify;
  	}
  	
	@JsonSerialize(using=JsonDeserialize.class)
	public Date getCreateDatetime(){
		return createDatetime;
  	} 
  	
  	public void setCreateDatetime(Date createDatetime){
  		this.createDatetime=createDatetime;
  	}
  	
	public String getCreateOperatorId(){
		return createOperatorId;
  	} 
  	
  	public void setCreateOperatorId(String createOperatorId){
  		this.createOperatorId=createOperatorId;
  	}
  	
	@JsonSerialize(using=JsonDeserialize.class)
	public Date getLastUpdateDatetime(){
		return lastUpdateDatetime;
  	} 
  	
  	public void setLastUpdateDatetime(Date lastUpdateDatetime){
  		this.lastUpdateDatetime=lastUpdateDatetime;
  	}
  	
	public String getLastUpdateOperatorId(){
		return lastUpdateOperatorId;
  	} 
  	
  	public void setLastUpdateOperatorId(String lastUpdateOperatorId){
  		this.lastUpdateOperatorId=lastUpdateOperatorId;
  	}
  	
	public Boolean getDataStatus(){
		return dataStatus;
  	} 
  	
  	public void setDataStatus(Boolean dataStatus){
  		this.dataStatus=dataStatus;
  	}
  	
  
}
