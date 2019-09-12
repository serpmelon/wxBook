package com.togo.wx.exam.service;

import com.togo.wx.exam.dao.ExamMapper;
import com.togo.wx.exam.entity.ExamAnswerContent;
import com.togo.wx.exam.entity.ExamContentEntity;
import com.togo.wx.exam.entity.Q2A;
import com.togo.wx.exam.entity.form.ScoreForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年08月18日 10:59
 * @since 1.0
 */
@Service
public class ExamService {

    private List<ExamContentEntity> list = null;
    private Map<Integer, List<ExamAnswerContent>> map = null;

    private Map<String, List<Q2A>> answerList = new HashMap<>();

    @Autowired
    private ExamMapper examMapper;

    public List<ExamContentEntity> getExamContentList() {

        if (list == null) {

            initList();
        }

        return list;
    }

    public boolean score(ScoreForm form, String key){

        boolean correct = isCorrect(form);

        String mapKey = form.getOpenId() + "_" + key;
        List<Q2A> list = answerList.get(mapKey);
        if (list == null)
            list = new ArrayList<>();

        Q2A qa = new Q2A(form.getQuestionId(), form.getAnswerId(), correct ? 1 : 0);
        list.add(qa);
        qa.setAppid("123");
        examMapper.insertExamRecord(qa);

        answerList.putIfAbsent(mapKey, list);

        return correct;
    }

    public boolean isCorrect(ScoreForm form) {

        if (map == null)
            initMap();

        List<ExamAnswerContent> answerContentList = map.get(form.getQuestionId());
        for (ExamAnswerContent answerContent : answerContentList) {
            if (answerContent.getId() == form.getAnswerId()) {
                return answerContent.getCorrect() == 1;
            }
        }

        return false;
    }

    synchronized public void initList() {

        if (list == null)
            list = examMapper.getAllExamContent();
    }

    synchronized public void initMap() {

        if (map == null) {

            map = new ConcurrentHashMap<>();
            for (ExamContentEntity entity : list) {
                map.put(entity.getId(), entity.getAnswerList());
            }
        }
    }
}