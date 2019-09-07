package com.togo.wx.user.dao;

import com.togo.wx.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

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
public interface UserMapper {

    UserEntity getUserEntity(int uid);

    void insertUserEntity(UserEntity userEntity);
}