package cn.fengylb.mycommunity.mycommunity.exception;

public class CustomizeException extends RuntimeException {
    public CustomizeException(String message){
        super(message);
    }
    public CustomizeException(ICustomizeErrorCode customizeErrorCode){
        super(customizeErrorCode.getMessage());
    }
}
