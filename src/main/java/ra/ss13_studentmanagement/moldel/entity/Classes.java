package ra.ss13_studentmanagement.moldel.entity;

public class Classes {
    private String classId;
    private String className;
    private Boolean status ;

    public Classes() {}
    public Classes(String classId, String className, Boolean status) {
        this.classId = classId;
        this.className = className;
        this.status = status;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
