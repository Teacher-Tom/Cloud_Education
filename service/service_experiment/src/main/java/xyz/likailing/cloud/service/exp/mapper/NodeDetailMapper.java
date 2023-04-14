package xyz.likailing.cloud.service.exp.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.likailing.cloud.service.exp.entity.NodeDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 12042
* @description 针对表【exp_node_detail】的数据库操作Mapper
* @createDate 2023-03-28 15:09:48
* @Entity xyz.likailing.cloud.service.exp.entity.NodeDetail
*/
public interface NodeDetailMapper extends BaseMapper<NodeDetail> {
    int updateDifficultyByNodeIdAndStudentId(@Param("difficulty") Integer difficulty, @Param("nodeId") String nodeId, @Param("studentId") String studentId);

    List<NodeDetail> getByNodeIdAndStudentId(@Param("nodeId") String nodeId, @Param("studentId") String studentId);

    int countByHasFinish(@Param("hasFinish") Boolean hasFinish);

    int countByHasFinishAndNodeId(@Param("hasFinish") Boolean hasFinish, @Param("nodeId") String nodeId);

    int countByNodeId(@Param("nodeId") String nodeId);

    int countByDifficultyIsNotNullAndNodeIdAndDifficultyGreaterThan(@Param("nodeId") String nodeId, @Param("difficulty") Integer difficulty);

    Float selectDifficultyByNodeIdAndDifficultyGreaterThan(@Param("nodeId") String nodeId, @Param("difficulty") Integer difficulty);

    List<String> selectStudentIdByCourseId(@Param("courseId") String courseId);
}




