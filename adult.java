package rmit.edu.au;

public class adult {
	
	private String adultname;
	private String gender;
	private int age;
	private String status;
	
	
	
	public adult(String adultname,String gender,int age,String status) {
		this.adultname=adultname;
		this.gender=gender;
		this.age=age;
		this.status=status;
	}
	public String getadultname() {
		return adultname;
	}
	public void setadultname(String name) {
		adultname=name;
	}
	public String getgender() {
		return gender;
	}
	public void setgender(String gender) {
		this.gender=gender;
	}
	public int getage() {
		return age;
	}
	public void setage(int age) {
		this.age=age;
	}
	public String getstatus() {
		return status;
	}
	public void setstatus(String status) {
		this.status=status;
	}
	//**important
	public String toString() {
        return adultname + "\t"  + "\t" + gender + "\t" + age + "\t" + status + "\t" ;
    }

}
