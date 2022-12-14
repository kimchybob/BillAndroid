package com.backend.Domain;

import java.io.Serializable;
import java.util.Date;

public class Subject implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.SID
     *
     * @mbggenerated
     */
    private Integer sid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.subjName
     *
     * @mbggenerated
     */
    private String subjname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.subjCode
     *
     * @mbggenerated
     */
    private String subjcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.priSID
     *
     * @mbggenerated
     */
    private String prisid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.course
     *
     * @mbggenerated
     */
    private String course;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.practiScore
     *
     * @mbggenerated
     */
    private Float practiscore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.theoryScore
     *
     * @mbggenerated
     */
    private Float theoryscore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.diffiScore
     *
     * @mbggenerated
     */
    private Float diffiscore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.lastTime
     *
     * @mbggenerated
     */
    private Date lasttime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.descrip
     *
     * @mbggenerated
     */
    private String descrip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table subject
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.SID
     *
     * @return the value of subject.SID
     *
     * @mbggenerated
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.SID
     *
     * @param sid the value for subject.SID
     *
     * @mbggenerated
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.subjName
     *
     * @return the value of subject.subjName
     *
     * @mbggenerated
     */
    public String getSubjname() {
        return subjname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.subjName
     *
     * @param subjname the value for subject.subjName
     *
     * @mbggenerated
     */
    public void setSubjname(String subjname) {
        this.subjname = subjname == null ? null : subjname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.subjCode
     *
     * @return the value of subject.subjCode
     *
     * @mbggenerated
     */
    public String getSubjcode() {
        return subjcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.subjCode
     *
     * @param subjcode the value for subject.subjCode
     *
     * @mbggenerated
     */
    public void setSubjcode(String subjcode) {
        this.subjcode = subjcode == null ? null : subjcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.priSID
     *
     * @return the value of subject.priSID
     *
     * @mbggenerated
     */
    public String getPrisid() {
        return prisid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.priSID
     *
     * @param prisid the value for subject.priSID
     *
     * @mbggenerated
     */
    public void setPrisid(String prisid) {
        this.prisid = prisid == null ? null : prisid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.course
     *
     * @return the value of subject.course
     *
     * @mbggenerated
     */
    public String getCourse() {
        return course;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.course
     *
     * @param course the value for subject.course
     *
     * @mbggenerated
     */
    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.practiScore
     *
     * @return the value of subject.practiScore
     *
     * @mbggenerated
     */
    public Float getPractiscore() {
        return practiscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.practiScore
     *
     * @param practiscore the value for subject.practiScore
     *
     * @mbggenerated
     */
    public void setPractiscore(Float practiscore) {
        this.practiscore = practiscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.theoryScore
     *
     * @return the value of subject.theoryScore
     *
     * @mbggenerated
     */
    public Float getTheoryscore() {
        return theoryscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.theoryScore
     *
     * @param theoryscore the value for subject.theoryScore
     *
     * @mbggenerated
     */
    public void setTheoryscore(Float theoryscore) {
        this.theoryscore = theoryscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.diffiScore
     *
     * @return the value of subject.diffiScore
     *
     * @mbggenerated
     */
    public Float getDiffiscore() {
        return diffiscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.diffiScore
     *
     * @param diffiscore the value for subject.diffiScore
     *
     * @mbggenerated
     */
    public void setDiffiscore(Float diffiscore) {
        this.diffiscore = diffiscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.lastTime
     *
     * @return the value of subject.lastTime
     *
     * @mbggenerated
     */
    public Date getLasttime() {
        return lasttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.lastTime
     *
     * @param lasttime the value for subject.lastTime
     *
     * @mbggenerated
     */
    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.descrip
     *
     * @return the value of subject.descrip
     *
     * @mbggenerated
     */
    public String getDescrip() {
        return descrip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.descrip
     *
     * @param descrip the value for subject.descrip
     *
     * @mbggenerated
     */
    public void setDescrip(String descrip) {
        this.descrip = descrip == null ? null : descrip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subject
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Subject other = (Subject) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getSubjname() == null ? other.getSubjname() == null : this.getSubjname().equals(other.getSubjname()))
            && (this.getSubjcode() == null ? other.getSubjcode() == null : this.getSubjcode().equals(other.getSubjcode()))
            && (this.getPrisid() == null ? other.getPrisid() == null : this.getPrisid().equals(other.getPrisid()))
            && (this.getCourse() == null ? other.getCourse() == null : this.getCourse().equals(other.getCourse()))
            && (this.getPractiscore() == null ? other.getPractiscore() == null : this.getPractiscore().equals(other.getPractiscore()))
            && (this.getTheoryscore() == null ? other.getTheoryscore() == null : this.getTheoryscore().equals(other.getTheoryscore()))
            && (this.getDiffiscore() == null ? other.getDiffiscore() == null : this.getDiffiscore().equals(other.getDiffiscore()))
            && (this.getLasttime() == null ? other.getLasttime() == null : this.getLasttime().equals(other.getLasttime()))
            && (this.getDescrip() == null ? other.getDescrip() == null : this.getDescrip().equals(other.getDescrip()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subject
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getSubjname() == null) ? 0 : getSubjname().hashCode());
        result = prime * result + ((getSubjcode() == null) ? 0 : getSubjcode().hashCode());
        result = prime * result + ((getPrisid() == null) ? 0 : getPrisid().hashCode());
        result = prime * result + ((getCourse() == null) ? 0 : getCourse().hashCode());
        result = prime * result + ((getPractiscore() == null) ? 0 : getPractiscore().hashCode());
        result = prime * result + ((getTheoryscore() == null) ? 0 : getTheoryscore().hashCode());
        result = prime * result + ((getDiffiscore() == null) ? 0 : getDiffiscore().hashCode());
        result = prime * result + ((getLasttime() == null) ? 0 : getLasttime().hashCode());
        result = prime * result + ((getDescrip() == null) ? 0 : getDescrip().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subject
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", subjname=").append(subjname);
        sb.append(", subjcode=").append(subjcode);
        sb.append(", prisid=").append(prisid);
        sb.append(", course=").append(course);
        sb.append(", practiscore=").append(practiscore);
        sb.append(", theoryscore=").append(theoryscore);
        sb.append(", diffiscore=").append(diffiscore);
        sb.append(", lasttime=").append(lasttime);
        sb.append(", descrip=").append(descrip);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}