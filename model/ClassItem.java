package model;

public class ClassItem {
   private int classId;
   private String subject;
   private String targetYear;
   private String lessonMonth;
   private String classTime;
   private String fee;

   public ClassItem(int classId, String subject, String targetYear, String lessonMonth, String classTime, String fee) {
      this.classId = classId;
      this.subject = subject;
      this.targetYear = targetYear;
      this.lessonMonth = lessonMonth;
      this.classTime = classTime;
      this.fee = fee;
   }

   public int getClassId() {
      return this.classId;
   }

   public String getSubject() {
      return this.subject;
   }

   public String getTargetYear() {
      return this.targetYear;
   }

   public String getLessonMonth() {
      return this.lessonMonth;
   }

   public String getClassTime() {
      return this.classTime;
   }

   public String getFee() {
      return this.fee;
   }

   public String toString() {
      return this.lessonMonth + " (" + this.classTime + ") - " + this.fee;
   }
}
