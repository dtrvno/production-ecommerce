package com.dima.ecommerce.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ECommerceCommand {
    public static void main(String args[]) {
        ECommerceCommand cmd = new ECommerceCommand();
        ECommandReturn s = cmd.execute("ls -al");
        if (s.status == ECommandReturn.ECommandStatus.OK) {
            System.out.println("good:" + s.output);
        }
        s = cmd.execute("lad -al");
        if (s.status == ECommandReturn.ECommandStatus.OK) {
            System.out.println(s.output);
        } else {
            System.out.println("error:" + s.output);
        }
        s=cmd.execute("aws ec2 create-route --route-table-id rtb-0d909642f2698e1bd --nat-gateway-id nat-0601ac1d7bc4e9285  --destination-cidr-block 0.0.0.0/0");
        if (s.status == ECommandReturn.ECommandStatus.OK) {
            System.out.println(s.output);
        } else {
            System.out.println("error:" + s.error_output);
        }
    }

    public ECommerceCommand() {
    }

    public ECommandReturn execute(String command) {
        final String[] output = {""};
        ECommandReturn ret = new ECommandReturn();
        try {
            Thread thr = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Process p = Runtime.getRuntime().exec(command);
                        p.waitFor();
                        BufferedReader input;
                        if (p.exitValue()==0)
                            input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
                        else
                            input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String line = null;

                        try {
                            while ((line = input.readLine()) != null) {
                                output[0] += line;
                                output[0] +="\n";
                            }
                            if (p.exitValue()==0) {
                                ret.status= ECommandReturn.ECommandStatus.OK;
                                ret.output=output[0];
                            }
                            else {
                                ret.status= ECommandReturn.ECommandStatus.SCRIPT_ERROR;
                                ret.error_output=output[0];
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    catch (IOException e) {
                        ret.status= ECommandReturn.ECommandStatus.EXECUTION_ERROR;
                        ret.output=e.getMessage();
                    }
                    catch (InterruptedException e ) {
                        ret.status= ECommandReturn.ECommandStatus.EXECUTION_ERROR;
                        ret.output=e.getMessage();
                    }
                }
            });
            thr.start();
            thr.join();
            return ret;
        }
        catch(InterruptedException e ) {
            ret.status=ECommandReturn.ECommandStatus.EXECUTION_ERROR;
            ret.error_output=e.getMessage();
        }
        finally {
            return ret;
        }
    }
}
