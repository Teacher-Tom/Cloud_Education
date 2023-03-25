package xyz.likailing.cloud.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.entity.UcenterMember;
import xyz.likailing.cloud.service.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(UcenterMember member);

    UcenterMember login1(UcenterMember member);
    //注册的方法
    void register(RegisterVo registerVo);

    public void createCourse(String courseId,String courseName,String coverUrl);
//    boolean updateMember(UcenterMember ucenterMember);
}
