package xyz.likailing.cloud.service.exp.utils;



/**
 * @Author 12042
 * @create 2023/3/28 16:54
 */

public enum NodeType {
    BEGIN(0),
    TASK(1),
    END(2),
    BRANCH(3),
    CONVERGE(4)
    ;

    private final int typeNum;


    NodeType(int typeNum) {
        this.typeNum = typeNum;
    }

    public int getTypeNum() {
        return typeNum;
    }
}
