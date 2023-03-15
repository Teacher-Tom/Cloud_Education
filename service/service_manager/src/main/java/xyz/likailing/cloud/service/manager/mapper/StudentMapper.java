package xyz.likailing.cloud.service.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.likailing.cloud.service.manager.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.likailing.cloud.service.manager.entity.vo.StudentVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author likailing
 * @since 2023-03-11
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    List<StudentVO> selectAll();
}
