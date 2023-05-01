package Q1;

import java.util.HashMap;
import java.util.Map;

public class MarkingSystem {
  private final Map<Student, StudentRecord> allStudentMarks;
  private static MarkingSystem instance = new MarkingSystem();

  //Eager Initialization: make constructor private and a private attribute of this class and get instance
  private MarkingSystem(){
    allStudentMarks= new HashMap<>();
  }
/*
  public MarkingSystem getInstance(){
    return instance;
  }
*/

  //Lazy Initialization:

  private static MarkingSystem instance1;
  public static synchronized MarkingSystem getInstance1(){
    if(instance1 == null){
      instance1 = new MarkingSystem();
    }
    return instance1;
  }

  public void registerMark(ExamResult examResult, double scaleFactor) {

    Student student = examResult.getStudent();

    StudentRecord studentRecord;

    if (!allStudentMarks.containsKey(student)) {
      studentRecord = new StudentRecord(student);
      allStudentMarks.put(student, studentRecord);
    } else {
      studentRecord = allStudentMarks.get(student);
    }

    studentRecord.enter(examResult.getCode(), examResult.calculateTotal(scaleFactor));
  }

  public int averageAcrossAllExams(Student student) {

    if (!allStudentMarks.containsKey(student)) {
      throw new RuntimeException("No marks for student: " + student);
    }
    return allStudentMarks.get(student).calculateExamAverage();
  }
}
