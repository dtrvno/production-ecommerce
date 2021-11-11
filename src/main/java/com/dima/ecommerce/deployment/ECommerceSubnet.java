package com.dima.ecommerce.deployment;
//parameters VPC,CIDR

import com.dima.ecommerce.framework.ETask;

public class ECommerceSubnet extends ETask {
    //aws ec2 create-subnet --vpc-id vpc-016f7f2119b9e2764 --cidr-block 10.0.1.0/24
    //aws ec2 create-subnet --vpc-id vpc-016f7f2119b9e2764 --cidr-block 10.0.2.0/24
    //aws ec2 create-subnet --vpc-id vpc-016f7f2119b9e2764 --cidr-block 10.0.3.0/24
    private final String taskName="subnets";

    public String getTaskName() {
        return taskName;
    }
}
