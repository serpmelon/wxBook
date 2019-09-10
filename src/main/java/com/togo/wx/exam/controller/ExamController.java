package com.togo.wx.exam.controller;

import com.togo.wx.common.entity.Result;
import com.togo.wx.exam.entity.ExamContentEntity;
import com.togo.wx.exam.entity.form.ScoreForm;
import com.togo.wx.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
 * @date Created in 2019年08月21日 13:19
 * @since 1.0
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * <pre>
     * desc : 获取问题内容列表；
     * @author : taiyn
     * date : 2019-08-22 08:37
     * @param : []
     * @return java.util.List<com.togo.wx.exam.entity.ExamContentEntity>
     * </pre>
     */
    @RequestMapping("/content")
    public List<ExamContentEntity> getExamContent() {

        // TODO 2019-08-22 tyntodo 这里需要后续增加获取逻辑，目前就是全部拿出来
        return examService.getExamContentList();
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public Result score(@RequestBody ScoreForm scoreForm) {

        System.out.println(scoreForm);
        examService.score(scoreForm, "first");
        return new Result();
    }
}