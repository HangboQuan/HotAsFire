package chapter02;

/**
 * @author quanhangbo
 * @date 2022/10/25 22:39
 */
public class Chapter02_D0 {
	
	private String username;
	private String password;
	
	class Chapter02_D0_01 {
		private String age;
		private String address;
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		
		public String getAddress() {
			return address;
		}
		
		public void setAddress(String address) {
			this.address = address;
		}
		
		public void printPublicProperty() {
			System.out.println(username + " " + password);
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}

class Chapter02_D0_02 {
	
	public static void main(String[] args) {
		Chapter02_D0 chapter02_d0 = new Chapter02_D0();
		chapter02_d0.setUsername("usernameValue");
		chapter02_d0.setPassword("passwordValue");
		
		System.out.println("Chapter02_D0.getUsername()" + " " + chapter02_d0.getPassword());
		
		Chapter02_D0.Chapter02_D0_01 chapter02_d0_01 = chapter02_d0.new Chapter02_D0_01();
		chapter02_d0_01.setAge("ageValue");
		chapter02_d0_01.setAddress("addressValue");
		
		System.out.println(chapter02_d0_01.getAge() + " " + chapter02_d0_01.getAddress());
	}
}
