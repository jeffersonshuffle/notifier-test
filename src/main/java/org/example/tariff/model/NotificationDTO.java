package org.example.tariff.model;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;






public class NotificationDTO {
	static final List<String> subjectTemplate=new LinkedList<>(); 
			
	
	static final List<String> bodyTemplate= new LinkedList<>();
			
			
	static{
            subjectTemplate.add("Changes in tariff plan ");
            subjectTemplate.add("${tariffName}");
            bodyTemplate.add("Tariff ");
            bodyTemplate.add("${tariffName}");
            bodyTemplate.add(" has the following changes in conditions of service ");
            bodyTemplate.add("${nomenclature}");
            bodyTemplate.add(": old price is ");
            bodyTemplate.add("${oldPrice}");
            bodyTemplate.add("${currency}");
            bodyTemplate.add(" new price is ");
            bodyTemplate.add("${newPrice}");
            bodyTemplate.add("${currency}");
            bodyTemplate.add(", respectivly.");
        }		
	
	private String notificationSubjectTemplate;

    public String getNotificationSubjectTemplate() {
        return notificationSubjectTemplate;
    }

    public void setNotificationSubjectTemplate(String notificationSubjectTemplate) {
        this.notificationSubjectTemplate = notificationSubjectTemplate;
    }

    public String getNotificationBodyTemplate() {
        return notificationBodyTemplate;
    }

    public void setNotificationBodyTemplate(String notificationBodyTemplate) {
        this.notificationBodyTemplate = notificationBodyTemplate;
    }
	private String notificationBodyTemplate;
	private Long tariffId;
    private String tariffName;
    private String nomenclature;
  
    private Long oldPrice;
   
    private Long newPrice;
  
    private Date date;
    private String subject;
    private String body;

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
   
    void generateSubject(){
        StringBuilder sb=new StringBuilder();
        for(String s:bodyTemplate){
            boolean b=true;
            if(s.equals("${tariffName}")){
                sb.append(tariffName);
                b=false;
            }
             if(b){
                sb.append(s);
               
            }
        }
        subject=sb.toString();
        
    }
    void generateBody(){
        StringBuilder sb= new StringBuilder();
        for(String s:bodyTemplate){
            boolean b=true;
            if(s.equals("${tariffName}")){
                sb.append(tariffName);
                b=false;
            }
            if(s.equals("${nomenclature}")){
                sb.append(nomenclature);
                b=false;
            }
            if(s.equals("${oldPrice}")){
                sb.append(oldPrice);
                 b=false;
            }
            if(s.equals("${currency}")){
                sb.append("RuR");
                b=false;
            }
            if(s.equals("${newPrice}")){
                sb.append(newPrice);
                b=false;
            }
            if(b){
                sb.append(s);
               
            }
                    
        }
        body=sb.toString();
    }
    StringBuilder generateSubjectTemplate(){
        StringBuilder sb=new StringBuilder();
          subjectTemplate.stream().forEach(s->sb.append(s));
        notificationSubjectTemplate=sb.toString();
        return sb;
    }
    StringBuilder generateBodyTemplate(){
        StringBuilder sb=new StringBuilder();
        bodyTemplate.stream().forEach(s->sb.append(s));
        notificationBodyTemplate=sb.toString();
        return sb;
    }
    public NotificationDTO generateNotificationTemplate() {
    	generateSubjectTemplate();
        generateBodyTemplate();
        	
        return this;
    }
    public NotificationDTO generateNotification() {
    	generateSubject();
        generateBody();
        	
        return this;
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
