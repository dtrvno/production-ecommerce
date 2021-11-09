package com.dima.ecommerce.configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

public class ECommerceConfiguration {

// static parameters
    public static Region region = Region.US_EAST_2;
    public static String hosted_zone="dtrubov.net";
// generated parameters
    public static Ec2Client ec2 = Ec2Client.builder().region(region).build();
}
