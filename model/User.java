package model;

public class User {
   private int id;
   private String firstName;
   private String lastName;
   private String nic;
   private String targetYear;
   private String mobile;
   private String email;
   private String institute;
   private String photoPath;
   private String city;
   private String address;
   private String school;
   private String role;

   public User(int id, String firstName, String lastName, String nic, String targetYear, String mobile, String email, String institute, String photoPath, String city, String address, String school, String role) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.nic = nic;
      this.targetYear = targetYear;
      this.mobile = mobile;
      this.email = email;
      this.institute = institute;
      this.photoPath = photoPath;
      this.city = city;
      this.address = address;
      this.school = school;
      this.role = role;
   }

   public User(String firstName, String lastName, String nic, String targetYear, String mobile, String email, String institute, String photoPath) {
      this(0, firstName, lastName, nic, targetYear, mobile, email, institute, photoPath, "Badulla", "", "", "STUDENT");
   }

   public int getId() {
      return this.id;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getNic() {
      return this.nic;
   }

   public String getTargetYear() {
      return this.targetYear;
   }

   public void setTargetYear(String targetYear) {
      this.targetYear = targetYear;
   }

   public String getMobile() {
      return this.mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getEmail() {
      return this.email;
   }

   public String getInstitute() {
      return this.institute;
   }

   public String getPhotoPath() {
      return this.photoPath;
   }

   public void setPhotoPath(String photoPath) {
      this.photoPath = photoPath;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getSchool() {
      return this.school;
   }

   public void setSchool(String school) {
      this.school = school;
   }

   public String getRole() {
      return this.role;
   }
}
