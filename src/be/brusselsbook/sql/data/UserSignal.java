package be.brusselsbook.sql.data;

public class UserSignal extends Describer {
	
	private Long did;
	private Long signalerUid;
	
	
	public UserSignal(Describer describer) {
		super(describer);
	}

	
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public Long getSignalerUid() {
		return signalerUid;
	}
	public void setSignalerUid(Long signalerUid) {
		this.signalerUid = signalerUid;
	}
	

}
