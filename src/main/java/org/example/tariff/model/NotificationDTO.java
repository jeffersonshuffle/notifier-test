package org.example.tariff.model;




import java.util.Date;







public class NotificationDTO {

    
    
   
	
    
    private Long tariffId;
    private String tariffName;
    private String nomenclature;
    private Long oldPrice;
    private Long newPrice;
    private Date date;
    private String subject;
    private String body;
    
   public static NotificationDTO getEmty(){
       return new NotificationDTO();
   }
 

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }
   

	public Long getTariffId() {
		return tariffId;
	}

	
	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
	}

	
	public String getNomenclature() {
		return nomenclature;
	}

	
	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}


	public Long getOldPrice() {
		return oldPrice;
	}

	
	public void setOldPrice(Long oldPrice) {
		this.oldPrice = oldPrice;
	}

	
	public Long getNewPrice() {
		return newPrice;
	}

	
	public void setNewPrice(Long newPrice) {
		this.newPrice = newPrice;
	}

	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


}
