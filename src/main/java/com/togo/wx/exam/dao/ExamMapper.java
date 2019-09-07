package com.togo.wx.exam.dao;

import com.togo.wx.exam.entity.ExamContentEntity;
import org.springframework.stereotype.Repository;

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
 * @date Created in 2019年08月18日 10:37
 * @since 1.0
 */
@Repository
public interface ExamMapper {

    List<ExamContentEntity> getAllExamContent();
}