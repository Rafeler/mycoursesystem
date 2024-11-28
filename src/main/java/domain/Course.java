package domain;
import java.sql.Date;

public class Course {
    private String name;
    private String description;
    private int hours;
    private Date begindate;
    private Date enddate;
    private CourseType courseType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null && name.length()>1) {
            this.name = name;
        }else{
            throw new InvalidValueException("Name muss mindestens 2 Zeichen haben");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
