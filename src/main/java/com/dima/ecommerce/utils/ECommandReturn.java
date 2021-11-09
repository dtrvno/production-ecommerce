package com.dima.ecommerce.utils;

public class ECommandReturn {
    public String output;
    public enum ECommandStatus { OK,EXECUTION_ERROR,SCRIPT_ERROR} ;
    public ECommandStatus status;
    public String error_output;
}