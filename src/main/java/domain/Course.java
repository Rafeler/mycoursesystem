package domain;
import java.sql.Date;

public class Course extends BaseEntity {
    private String name;
    private String description;
    private int hours;
    private Date begindate;
    private Date enddate;
    private CourseType courseType;


    public Course(Long id, String name, String description, int hours, Date begindate, Date enddate, CourseType courseType) throws InvalidValueException {
        super(id);
        this.setName(name);
        this.setDescription(description);
        this.setHours(hours);
        this.setBegindate(begindate);
        this.setEnddate(enddate);
        this.setCourseType(courseType);
    }

    public Course(String name, String description, int hours, Date begindate, Date enddate, CourseType courseType) throws InvalidValueException {
        super(null);
        this.setName(name);
        this.setDescription(description);
        this.setHours(hours);
        this.setBegindate(begindate);
        this.setEnddate(enddate);
        this.setCourseType(courseType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidValueException {
        if(name!=null && name.length()>1) {
            this.name = name;
        }else{
            throw new InvalidValueException("Name muss mindestens 2 Zeichen haben");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidValueException {
        if(description!=null && description.length()>10) {
            this.description = description;
        }else{
            throw new InvalidValueException("Kursbeschreibung muss mindestens 10 Zeichen lang sein");
        }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) throws InvalidValueException {
        if(hours>0 && hours<10) {
            this.hours = hours;
        }else{
            throw new InvalidValueException("Anzahl der Kursstunden pro Kurs darf nur zwischen 1 und 10 liegen");
        }
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) throws InvalidValueException {
        if(begindate==null) {
            if (this.enddate!=null){
                if(this.begindate.before(this.enddate)) {
                    this.begindate = this.begindate;
                }else{
                    throw new InvalidValueException("Kursbegin muss vor Kursende sein");
                }
            }else{
                this.begindate = this.begindate;
            }
        }else{
            throw new InvalidValueException("Startdatum darf nicht 0 sein");
        }
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) throws InvalidValueException {
        if(enddate==null) {
            if (this.begindate!=null){
                if(this.enddate.after(this.begindate)) {
                    this.enddate = this.enddate;
                }else{
                    throw new InvalidValueException("Kursende muss nach Kursbegin sein");
                }
            }else{
                this.enddate = this.enddate;
            }
        }else{
            throw new InvalidValueException("Enddatum darf nicht 0 sein");
        }
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) throws InvalidValueException {
        if (courseType==null) {
            this.courseType = courseType;
        }else{
            throw new InvalidValueException("Kurstype darf nicht 0 / leer sein");
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hours=" + hours +
                ", begindate=" + begindate +
                ", enddate=" + enddate +
                ", courseType=" + courseType +
                '}';
    }
}
