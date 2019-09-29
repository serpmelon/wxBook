package com.togo.wx.system;

import com.togo.wx.common.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @date Created in 2019年09月29日 09:16
 * @since 1.0
 */
@RestController("/sys")
public class SystemController {

    @GetMapping(value = "ping")
    public Result ping() {

        return new Result();
    }
}