package com.togo.wx.exam.entity;

import java.util.Date;
import java.util.List;

/**
 * <p>问题实体，包括问题，答案，问题标签，问题分值</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年08月18日 09:49
 * @since 1.0
 */
public class ExamContentEntity {

    private int id;
    private String question;
    private int score;
    private List<ExamAnswerContent> answerList;
    private List<ExamTag> tagList;
    private int isDel;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<ExamAnswerContent> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<ExamAnswerContent> answerList) {
        this.answerList = answerList;
    }

    public List<ExamTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<ExamTag> tagList) {
        this.tagList = tagList;
    }
}